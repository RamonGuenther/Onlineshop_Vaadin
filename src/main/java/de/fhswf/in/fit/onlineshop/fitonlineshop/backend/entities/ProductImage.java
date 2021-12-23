package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.ImageType;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views.LoginView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse Image speichert ein Bild und die Produkte, zu denen dieses
 * Bild gehört.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Lob
//    private byte[] data;
    @Column(length = 10000)
    private Image image;

    private ImageType type;

    @ManyToMany (mappedBy = "productImages")
    private Set<Product> products;

    public ProductImage(String name, Image image, ImageType type, Set<Product> product) {
        this.name = name;
        this.image = image;
        this.type = type;
        this.products = product;
    }

    public ProductImage(String name, ImageType type, Product product) {
        this.name = name;
        this.type = type;
        this.products = new HashSet<>();
        this.products.add(product);
    }

    public ProductImage() {
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImage(String imageFile){
        StreamResource resource = new StreamResource(imageFile, () -> ProductImage.class.getClassLoader().getResourceAsStream("images/" + imageFile));
        this.image = new Image(resource,"Image from file");
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> product) {
        this.products = product;
    }

    public void addProduct(Product product){
        products.add(product);
    }
}
