package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Category;
import org.nng.swdoc.library.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category newCategory) {
        Category category = categoryRepository.save(newCategory);
        logger.info("CREATE category: {}", category);
        return category;
    }

    public Category findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        logger.info("GET category: {}", category);
        return category;
    }

    public Category findByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + name));
        logger.info("GET category: {}", category);
        return category;
    }

    public List<Category> findAll() {
        List<Category> categories = categoryRepository.findAll();
        logger.info("GET categories: {}", categories.size());
        return categories;
    }
}

