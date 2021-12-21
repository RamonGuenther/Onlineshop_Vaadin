package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.primaryKeys;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Orders;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * Die Klasse OrderedProductKey enthält den Primärschlüssel für die Klasse bzw.
 * Tabelle OrderedProducts. Der Key wird die Tabelle OrderedProducts eingebettet.
 *
 * Wichtig für zusammengesetzte Primärschlüssel ist:
 * - Der Key muss das Interface Serializable implementieren
 * - Der Key muss equals und hashCode Methoden definieren
 * - Der Key muss in der Klasse, für die er der Schlüssel ist, mit einer @IdClass
 *   Annotation hinzugefügt werden, oder wie in diesem Beispiel mit @Embeddable bzw.
 *   @EmbeddedId
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@Embeddable
public class OrderedProductKey implements Serializable {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Orders orders;

    public OrderedProductKey(Product product, Orders orders) {
        this.product = product;
        this.orders = orders;
    }

    public OrderedProductKey(){

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderedProductKey)) return false;
        OrderedProductKey that = (OrderedProductKey) o;
        return Objects.equals(product, that.product) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, orders);
    }
}
