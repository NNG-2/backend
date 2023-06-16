package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Image;
import org.nng.swdoc.library.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    private ImageRepository imageRepository;


    public Image createImage(Image newImage) {
        Image image = imageRepository.save(newImage);
        logger.info("CREATE Image: {}", image.getId());
        return image;
    }

    public Image findById(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        logger.info("GET Image: {}", image.getId());
        return image;
    }

    public List<Image> findAll() {
        List<Image> images = imageRepository.findAll();
        logger.info("GET Images: {}", images.size());
        return images;
    }
}
