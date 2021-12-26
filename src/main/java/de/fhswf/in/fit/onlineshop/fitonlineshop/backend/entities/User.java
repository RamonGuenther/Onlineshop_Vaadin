package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Die Klasse User speichert den Benutzernamen, sowie die Adressen und
 * Bestellungen des Benutzers. Für die Praktikumsaufgabe sind die Benutzer
 * für den Login in einer internen Benutzerdatenbank von SpringSecurity
 * angelegt und gespeichert.
 * Die Klasse User dient also der Zuordnung der Bestellungen und Adressen
 * zu den Nutzern in der Nutzerdatenbank.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Address> adresses;

    @OneToMany
    private List<Orders> orders;

    public User(String username, List<Address> adresses, List<Orders> orderNumbers) {
        this.username = username;
        this.adresses = adresses;
        this.orders = orderNumbers;
    }

    public User() {
        this.adresses = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public User(String username) {
        this.username = username;
        this.adresses = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Address> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Address> adresses) {
        this.adresses = adresses;
    }

    public List<Orders> getOrders() {
        List<Orders> ordersList = orders;
        ordersList.remove(orders.size() - 1);
        return ordersList;
    }

    public Orders getShoppingCart() {
        return orders.get(orders.size() - 1);
    }

    public void setOrders(List<Orders> orderNumbers) {
        this.orders = orderNumbers;
    }

    public void addAddress(Address address) {
        this.adresses.add(address);
    }

    public void deleteAddress(Address address) {
        this.adresses.remove(address);
    }

    public void addOrder(Orders order) {
        this.orders.add(order);
    }

}
