package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.CategoryType;

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

    //TODO Funktioniert nicht wie gedacht XD
    @TableGenerator(name = "product_gen",initialValue = 10000, allocationSize = 100)
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "product_gen")
    private Long id;

    private String name;

    private Double price;

    @ManyToMany
    private List<ProductImage> productImages;

    @ManyToMany
    private List<Category> categories;

    private int inStock;

    private String description;

    public Product(String name,
                   Double price,
                   List<ProductImage> productImages,
                   List<Category> categories,
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
        this.productImages = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public Product() {
        this.productImages = new ArrayList<>();
        this.categories = new ArrayList<>();
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

    public List<ProductImage> getImages() {
        return productImages;
    }

    public void setImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getMainCategory(){
        return categories.stream().findFirst().get().getCategoryType().label;
    }

    public void setCategories(List<Category> categories) {
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

    public List<CategoryType> getCategorieEnums(){
        List<CategoryType> categoryTypeList = new ArrayList<>();
        for(Category category: categories){
            categoryTypeList.add(category.getCategoryType());
        }
        return categoryTypeList;
    }

    public Image getMainProductImage(){
        return productImages.get(0).getVaadinImage();
    }


}
