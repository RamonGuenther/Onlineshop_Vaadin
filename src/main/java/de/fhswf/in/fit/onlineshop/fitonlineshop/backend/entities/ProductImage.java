package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.ImageType;
import org.apache.commons.io.IOUtils;

import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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

    @Lob
    private byte[] data;

    private ImageType type;

    @ManyToMany (mappedBy = "productImages")
    private Set<Product> products;

    public ProductImage(String name, byte[] data, ImageType type, Set<Product> product) {
        this.name = name;
        this.data = data;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setData(String imageFile){
        try {
            data = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("images/" + imageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * Die Methode getVaadinImage wandelt das byte-Array in ein
     * Vaadin-Image um.
     *
     * @return Produktbild als Vaadin-Image
     */
    public Image getVaadinImage(){
        StreamResource resource = new StreamResource(name, () -> new ByteArrayInputStream(data));
        return new Image(resource, name);
    }
}
