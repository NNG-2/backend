package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Image;
import org.nng.swdoc.library.dto.ImageDto;
import org.nng.swdoc.library.mapper.ImageMapper;
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

    @Autowired
    private ImageMapper imageMapper;

    public ImageDto createImage(ImageDto imageDto) {
        Image image = imageRepository.save(imageMapper.toEntity(imageDto));
        logger.info("CREATE Image: {}", image);
        return imageMapper.toDto(image);
    }

    public ImageDto getImageById(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        logger.info("GET Image: {}", image);
        return imageMapper.toDto(image);
    }

    public List<ImageDto> getAllImages() {
        List<Image> images = imageRepository.findAll();
        logger.info("GET Images: {}", images.size());
        return imageMapper.toDtoList(images);
    }

    public ImageDto updateImage(Long id, ImageDto imageDto) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        imageMapper.updateImageFromDto(imageDto, image);
        image = imageRepository.save(image);
        logger.info("UPDATE Image: {}", image);
        return imageMapper.toDto(image);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
        logger.info("DELETE Image: {}", id);
    }
}
