package org.nng.swdoc.library.controller;

import org.nng.swdoc.library.domain.Image;
import org.nng.swdoc.library.dto.ImageDto;
import org.nng.swdoc.library.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    private static final long MAX_IMAGE_SIZE_BYTES = 10 * 1024 * 1024;

    @RequestMapping("/upload/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createImage.html");
        return modelAndView;
    }
    @RequestMapping("/upload/view")
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewImage.html");
        return modelAndView;
    }

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            // Check if the uploaded file is a PNG image
            if (!image.getContentType().equals("image/png")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only PNG images are allowed.");
            }

            // Check if the image size exceeds the maximum allowed size
            if (image.getSize() > MAX_IMAGE_SIZE_BYTES) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Image size exceeds the maximum allowed size of 10MB.");
            }

            // Save the image data to the database
            ImageDto i = imageService.createImage(new ImageDto(image.getBytes()));
            System.out.println("id:" + i.getId());

            return ResponseEntity.ok("Image uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        ImageDto imageDto = imageService.getImageById(id);
        return ResponseEntity.ok(imageDto.getImageData());
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> getAllImages() {
        List<ImageDto> listDto = imageService.getAllImages();
        return ResponseEntity.ok(listDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        try {
            // Check if the uploaded file is a PNG image
            if (!image.getContentType().equals("image/png")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only PNG images are allowed.");
            }

            // Check if the image size exceeds the maximum allowed size
            if (image.getSize() > MAX_IMAGE_SIZE_BYTES) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Image size exceeds the maximum allowed size of 10MB.");
            }

            // Save the image data to the database
            imageService.updateImage(id, new ImageDto(image.getBytes()));

            return ResponseEntity.ok("Image uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
