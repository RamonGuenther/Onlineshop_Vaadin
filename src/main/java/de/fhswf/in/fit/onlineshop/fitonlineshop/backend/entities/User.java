package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities;

import javax.persistence.*;
import java.util.HashSet;
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Address> adresses;

    @OneToMany
    private Set<Orders> orders;

    public User(String username, Set<Address> adresses, Set<Orders> orderNumbers) {
        this.username = username;
        this.adresses = adresses;
        this.orders = orderNumbers;
    }

    public User() {
        this.adresses = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public User(String username){
        this.username = username;
        this.adresses = new HashSet<>();
        this.orders = new HashSet<>();
    };

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

    public Set<Address> getAdresses() {
        return adresses;
    }

    public void setAdresses(Set<Address> adresses) {
        this.adresses = adresses;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orderNumbers) {
        this.orders = orderNumbers;
    }

    public void addAddress(Address address){
        this.adresses.add(address);
    }

    public void deleteAddress(Address address){
        this.adresses.remove(address);
    }

    public void addOrder(Orders order){
        this.orders.add(order);
    }

}
