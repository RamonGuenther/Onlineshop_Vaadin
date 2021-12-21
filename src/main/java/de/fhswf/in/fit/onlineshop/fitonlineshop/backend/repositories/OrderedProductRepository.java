package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.OrderedProduct;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Orders;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.primaryKeys.OrderedProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Das OrderedProductRepository bietet eine Reihe von Standardimplementierungen für
 * den Datenbankzugriff auf die OrderedProduct-Tabelle. Diese sind hier nicht
 * explizit aufgeführt (z.B. save, getById...). Außerdem können eigene
 * Methoden hinzugefügt werden (wichtig: auf Syntax/Aufbau achten), aus
 * denen SpringData automatisch eine Implementierung generiert.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, OrderedProductKey> {

    List<OrderedProduct> findAllById_Orders(Orders orders);
}
