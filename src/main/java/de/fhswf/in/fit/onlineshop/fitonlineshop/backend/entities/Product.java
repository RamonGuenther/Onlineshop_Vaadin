package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;

import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Die Klasse Product speichert alle Daten zu einem Produkt, wie Name, Preis,
 * Kategorien, Bilder, Beschreibung und verfügbare Anzahl.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @ManyToMany
    private Set<ProductImage> productImages;

    @ManyToMany (mappedBy = "products")
    private Set<Category> categories;

    private int inStock;

    private String description;

    public Product(String name,
                   Double price,
                   Set<ProductImage> productImages,
                   Set<Category> categories,
                   int inStock,
                   String description) {
        this.name = name;
        this.price = price;
        this.productImages = productImages;
        this.categories = categories;
        this.inStock = inStock;
        this.description = description;
    }

    public Product(String name, Double price, int inStock, String description) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.description = description;
        this.productImages = new HashSet<>();
        this.categories = new HashSet<>();
    }

    public Product() {
        this.productImages = new HashSet<>();
        this.categories = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<ProductImage> getImages() {
        return productImages;
    }

    public void setImages(Set<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addImage(ProductImage productImage){
        this.productImages.add(productImage);
    }

    public void addCategory(Category category){
        this.categories.add(category);
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Die Methode getVaadinImageList wandelt das Set von ProductImages in eine
     * Liste mit Images von Vaadin um.
     *
     * @return  Liste mit Produkt-Images
     */
    public List<Image> getVaadinImageList(){
        List<Image> list = new ArrayList<>();
        Image image = new Image();
        for(ProductImage productImage: productImages){
            StreamResource resource = new StreamResource(productImage.getName(), () -> new ByteArrayInputStream(productImage.getData()));
            image.setSrc(resource);
            image.setAlt(productImage.getName());
            list.add(image);
        }
        return list;
    }

}
