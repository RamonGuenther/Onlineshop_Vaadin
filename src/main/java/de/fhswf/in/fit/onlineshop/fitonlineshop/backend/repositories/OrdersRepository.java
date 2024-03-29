package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Orders;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Das OrdersRepository bietet eine Reihe von Standardimplementierungen für
 * den Datenbankzugriff auf die Orders-Tabelle. Diese sind hier nicht
 * explizit aufgeführt (z.B. save, getById...). Außerdem können eigene
 * Methoden hinzugefügt werden (wichtig: auf Syntax/Aufbau achten), aus
 * denen SpringData automatisch eine Implementierung generiert.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
