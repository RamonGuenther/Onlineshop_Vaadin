package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Die Klasse Orders speichert eine Bestellung, samt Liefer- und Rechnungsadresse
 * und den bestellten Produkten. Außerdem kann ein Kommentar zu der Bestellung
 * gespeichert werden.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Address billingAddress;

    @ManyToOne
    private Address deliveryAddress;

    private String orderComment;

    @OneToMany
    private List<OrderedProduct> orderedProducts;

    public Orders(Address billingAddress, Address deliveryAddress, String orderComment) {
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
        this.orderComment = orderComment;
        this.orderedProducts = new ArrayList<>();
    }

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public void addOrderedProduct(OrderedProduct orderedProduct){
        this.orderedProducts.add(orderedProduct);
    }
}
