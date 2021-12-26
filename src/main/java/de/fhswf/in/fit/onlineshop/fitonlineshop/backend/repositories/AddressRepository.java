package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Das AddressRepository bietet eine Reihe von Standardimplementierungen für
 * den Datenbankzugriff auf die Address-Tabelle. Diese sind hier nicht
 * explizit aufgeführt (z.B. save, getById...). Außerdem können eigene
 * Methoden hinzugefügt werden (wichtig: auf Syntax/Aufbau achten), aus
 * denen SpringData automatisch eine Implementierung generiert.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

}
