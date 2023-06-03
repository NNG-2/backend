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

    public List<CategoryDto> getAllCategories() {
        logger.info("Getting all categories");
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categoryMapper.toDtoList(categories);
        logger.info("Retrieved {} categories", categoryDtos.size());
        return categoryDtos;
    }

    public CategoryDto getCategoryById(Long id) {
        logger.info("Getting category with id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        CategoryDto categoryDto = categoryMapper.toDto(category);
        logger.info("Retrieved category: {}", categoryDto);
        return categoryDto;
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        logger.info("Creating category: {}", categoryDto);
        Category category = categoryMapper.toEntity(categoryDto);
        Category createdCategory = categoryRepository.save(category);
        CategoryDto createdCategoryDto = categoryMapper.toDto(createdCategory);
        logger.info("Category created: {}", createdCategoryDto);
        return createdCategoryDto;
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        logger.info("Updating category with id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        categoryMapper.updateCategoryFromDto(categoryDto, category);
        Category updatedCategory = categoryRepository.save(category);
        CategoryDto updatedCategoryDto = categoryMapper.toDto(updatedCategory);
        logger.info("Category updated: {}", updatedCategoryDto);
        return updatedCategoryDto;
    }

    public void deleteCategory(Long id) {
        logger.info("Deleting category with id: {}", id);
        categoryRepository.deleteById(id);
        logger.info("Category deleted");
    }
}

