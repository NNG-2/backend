package org.nng.swdoc.library.controller;

import org.nng.swdoc.library.domain.Image;
import org.nng.swdoc.library.domain.User;
import org.nng.swdoc.library.dto.*;
import org.nng.swdoc.library.exception.RentExistsException;
import org.nng.swdoc.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class WebController {
    @Autowired
    private UserService userService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @Autowired
    private RentService rentService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private ImageService imageService;

    private User prepareModelAndGetUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        var user = userService.findByEmail(authentication.getName());

        model.addAttribute("name", user.getName());
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("isAdmin", user.getCategory().getName().equals("ADMIN"));

        return user;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        var libraries = libraryService.findAll();
        var categories = categoryService.findAll()
                .stream().filter(c -> !c.getName().equals("ADMIN")).toList();

        model.addAttribute("libraries", libraries);
        model.addAttribute("categories", categories);
        model.addAttribute("user", new UserDto());

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Validation failed.");
            return "register";
        }

        try {
            userService.createUser(user.toEntity(categoryService, libraryService));
            return "redirect:/login";
        } catch (Exception e) {
            var libraries = libraryService.findAll();
            var categories = categoryService.findAll()
                    .stream().filter(c -> !c.getName().equals("ADMIN")).toList();

            model.addAttribute("libraries", libraries);
            model.addAttribute("categories", categories);
            model.addAttribute("error", "User already exists.");

            return "register";
        }
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "search", required = false) String search, Model model) {
        prepareModelAndGetUser(model);

        model.addAttribute("search", search);
        model.addAttribute("books", bookService.findAll()
                .stream()
                .filter((book) -> book.getTitle().toLowerCase().contains(search == null ? "" : search.toLowerCase()))
                .toList()
        );

        return "home";
    }

    @GetMapping("/add_author")
    public String addAuthor(Model model) {
        prepareModelAndGetUser(model);

        model.addAttribute("author", new AuthorDto());

        return "add_author";
    }

    @PostMapping("/add_author")
    public String createAuthor(@ModelAttribute AuthorDto author, BindingResult bindingResult, Model model) {
        prepareModelAndGetUser(model);

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Validation failed.");
            return "redirect:/add_author";
        }

        try {
            authorService.createAuthor(author.toEntity());
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating author.");
            return "redirect:/home";
        }
    }

    @GetMapping("/add_book")
    public String addBook(Model model) {
        prepareModelAndGetUser(model);

        model.addAttribute("book", new BookDto());

        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("libraries", libraryService.findAll());
        model.addAttribute("genres", genreService.findAll());

        return "add_book";
    }

    @PostMapping("/add_book")
    public String createBook(@ModelAttribute BookDto book, @RequestParam("image") MultipartFile image, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        prepareModelAndGetUser(model);

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Validation failed.");
            return "redirect:/add_book";
        }

        try {
            var savedImage = imageService.createImage(Image.builder().imageData(image.getBytes()).build());
            var bookEntity = book.toEntity(authorService, genreService, libraryService);
            bookEntity.setImage(savedImage);
            bookService.createBook(bookEntity);
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", "Error creating book.");
            return "redirect:/home";
        }
    }

    @GetMapping("/add_library")
    public String addLibrary(Model model) {
        prepareModelAndGetUser(model);

        model.addAttribute("library", new LibraryDto());

        return "add_library";
    }

    @PostMapping("/add_library")
    public String createLibrary(@ModelAttribute LibraryDto library, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        prepareModelAndGetUser(model);

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Validation failed.");
            return "redirect:/add_library";
        }

        try {
            libraryService.createLibrary(library.toEntity());
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", "Error creating library.");
            return "redirect:/home";
        }
    }

    @GetMapping("/rented_books")
    public String rentedBooks(@RequestParam(value = "search", required = false) String search, Model model) {
        var user = prepareModelAndGetUser(model);

        model.addAttribute("search", search);
        model.addAttribute("rents", rentService.findByUserId(user.getId())
                .stream()
                .filter((rent) -> rent.getBook().getTitle().toLowerCase().contains(search == null ? "" : search.toLowerCase()))
                .toList()
        );

        return "rented_books";
    }

    @PostMapping("/rent/{bookId}")
    public String rentBook(@PathVariable(value = "bookId") Long bookId, Model model, RedirectAttributes redirectAttributes) {
        var user = prepareModelAndGetUser(model);

        try {
            rentService.createRent(
                    RentDto.builder()
                            .bookId(bookId)
                            .userId(user.getId())
                            .issueDate(LocalDateTime.now().toLocalDate())
                            .expiredDate(LocalDateTime.now().plusDays(14).toLocalDate())
                            .build()
                            .toEntity(bookService, userService)
            );
        } catch (RentExistsException e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/home";
        }

        return "redirect:/home";
    }

    @PostMapping("/return_book/{bookId}")
    public String returnBook(@PathVariable(value = "bookId") Long bookId, Model model) {
        var user = prepareModelAndGetUser(model);

        rentService.returnBook(rentService.getRentByBookIdAndUserId(bookId, user.getId()));

        return "redirect:/rented_books";
    }
}
