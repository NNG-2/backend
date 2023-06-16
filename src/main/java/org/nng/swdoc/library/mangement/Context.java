package org.nng.swdoc.library.mangement;

import lombok.Data;
import lombok.Builder;
import org.nng.swdoc.library.service.BookService;
import org.nng.swdoc.library.service.LibraryService;
import org.nng.swdoc.library.service.RentService;
import org.nng.swdoc.library.service.UserService;

@Data
@Builder
public class Context {
    private UserService userService;
    private LibraryService libraryService;
    private BookService bookService;
    private RentService rentService;
}
