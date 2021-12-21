package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.ImageType;

import javax.persistence.*;
import java.util.Set;

/**
 * Die Klasse Image speichert ein Bild und die Produkte, zu denen dieses
 * Bild gehört.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] data;

    private ImageType type;

    @ManyToMany (mappedBy = "images")
    private Set<Product> product;

    public Image(String name, byte[] data, ImageType type, Set<Product> product) {
        this.name = name;
        this.data = data;
        this.type = type;
        this.product = product;
    }

    public Image() {
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

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

}
