package com.Spring.SpringeCommerce.service;

import com.Spring.SpringeCommerce.exceptions.AlreadyExistsException;
import com.Spring.SpringeCommerce.exceptions.ResourceNotFoundException;
import com.Spring.SpringeCommerce.interfaces.ICategoryService;
import com.Spring.SpringeCommerce.model.Category;
import com.Spring.SpringeCommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Category not found!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category addCategory(Category newCategory) {
        return Optional.of(newCategory).filter(category -> !categoryRepository.existsByName(category.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistsException(newCategory.getName() + " already exists"));
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        return Optional.ofNullable(getCategoryById(categoryId))
                .map(oldCategory -> {
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                }).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete, () -> {
                    throw new ResourceNotFoundException("Category not found!");
                        }
                );
    }
}
