package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Category;
import org.nng.swdoc.library.dto.CategoryDto;
import org.nng.swdoc.library.mapper.CategoryMapper;
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

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.save(categoryMapper.toEntity(categoryDto));
        logger.info("CREATE category: {}", category);
        return categoryMapper.toDto(category);
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        logger.info("GET category: {}", category);
        return categoryMapper.toDto(category);
    }

    public CategoryDto getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + name));
        logger.info("GET category: {}", category);
        return categoryMapper.toDto(category);
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        logger.info("GET categories: {}", categories.size());
        return categoryMapper.toDtoList(categories);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        categoryMapper.updateCategoryFromDto(categoryDto, category);
        category = categoryRepository.save(category);
        logger.info("UPDATE category: {}", category);
        return categoryMapper.toDto(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        logger.info("DELETE category: {}", id);
    }
}

