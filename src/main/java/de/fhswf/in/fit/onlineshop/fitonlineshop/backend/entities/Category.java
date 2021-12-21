package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.CategoryType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse Category speichert die Details zu einer Produktkategorie,
 * sowie alle Produkte, die dieser Kategorie angehören.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private CategoryType categoryType;
    private String description;

    @ManyToMany
    private Set<Product> products;

    public Category(CategoryType categoryType, String description, Set<Product> products) {
        this.categoryType = categoryType;
        this.description = description;
        this.products = products;
    }

    public Category(CategoryType categoryType, String description){
        this.categoryType = categoryType;
        this.description = description;
        this.products = new HashSet<>();
    }

    public Category() {
        this.products = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        products.add(product);
    }
}
