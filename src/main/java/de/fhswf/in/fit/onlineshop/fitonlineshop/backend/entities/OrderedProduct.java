package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.primaryKeys.OrderedProductKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Die Klasse OrderedProduct speichert ein Produkt und dessen bestellte Anzahl
 * zu einer Bestellung.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@Entity
public class OrderedProduct  {

    @EmbeddedId
    private OrderedProductKey id;

    private Integer amount;

    public OrderedProduct(OrderedProductKey id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }

    public OrderedProduct() {
    }

    public OrderedProductKey getId() {
        return id;
    }

    public void setId(OrderedProductKey id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
