package com.ead.category_microservice.service;


import com.ead.category_microservice.model.Category;
import com.ead.category_microservice.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCategoryById() {
        Category category = new Category(1L, "Drinks", "Juice", 5.0);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> foundCategory = categoryService.getCategoryById(1L);
        assertTrue(foundCategory.isPresent());
        assertEquals("Drinks", foundCategory.get().getName());
    }

    @Test
    void testCreateCategory() {
        Category category = new Category(1L, "Drinks", "Juice", 5.0);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category createdCategory = categoryService.createCategory(category);
        assertEquals("Drinks", createdCategory.getName());
    }

    @Test
    void testDeleteCategory() {
        categoryService.deleteCategory(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
