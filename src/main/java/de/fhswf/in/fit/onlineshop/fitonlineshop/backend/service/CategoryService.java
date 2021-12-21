package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Category;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.CategoryType;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Der CategoryService implementiert die Business-Logic für die Kategorien.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void saveCategory(Category category){
        categoryRepository.save(category);
    }

    public void saveCategory(CategoryType categoryType, String description){
        categoryRepository.save(new Category(categoryType, description));
    }

    public Category getCategorieById(Long id){
        return categoryRepository.getById(id);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

}
