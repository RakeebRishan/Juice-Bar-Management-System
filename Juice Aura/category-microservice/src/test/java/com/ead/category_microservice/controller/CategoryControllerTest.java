package com.ead.category_microservice.controller;


import com.ead.category_microservice.model.Category;
import com.ead.category_microservice.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void testGetAllCategories() throws Exception {

        Category category1 = new Category(1L, "Fresh juice", "Orange juice", 379.00);
        when(categoryService.getAllCategories()).thenReturn(List.of(category1));

        // Act & Assert
        mockMvc.perform(get("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Fresh juice"));

    }

    @Test
    void testGetCategoryById() throws Exception {
        // Arrange
        Category category = new Category(1L, "Fresh juice", "Orange juice", 379.00);
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(category));

        // Act & Assert
        mockMvc.perform(get("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fresh juice"))
                .andExpect(jsonPath("$.description").value("Orange juice"));
    }

    @Test
    void testGetCategoryById_NotFound() throws Exception {

        when(categoryService.getCategoryById(1L)).thenReturn(Optional.empty());


        mockMvc.perform(get("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCategory() throws Exception {
        // Arrange
        Category category = new Category(1L, "Fresh juice", "Orange juice", 379.00);
        when(categoryService.createCategory(any(Category.class))).thenReturn(category);

        // Act & Assert
        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Fresh juice\",\"description\":\"Orange juice\",\"price\":379.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Drinks"));
    }

    @Test
    void testUpdateCategory() throws Exception {

        Category existingCategory = new Category(1L, "Fresh juice", "Orange juice", 379.00);

        Category updatedCategory = null;
        when(categoryService.updateCategory(eq(1L), any(Category.class))).thenReturn(updatedCategory);

    }

    @Test
    void testUpdateCategory_NotFound() throws Exception {

        when(categoryService.updateCategory(eq(1L), any(Category.class)))
                .thenThrow(new RuntimeException("Category not found"));

    }

    @Test
    void testDeleteCategory() throws Exception {

        mockMvc.perform(delete("/api/categories/1"))
                .andExpect(status().isNoContent());


        verify(categoryService, times(1)).deleteCategory(1L);
    }
}
