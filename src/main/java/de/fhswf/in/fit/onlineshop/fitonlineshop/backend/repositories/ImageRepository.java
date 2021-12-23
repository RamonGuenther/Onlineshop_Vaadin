package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Das ImageRepository bietet eine Reihe von Standardimplementierungen für
 * den Datenbankzugriff auf die Image-Tabelle. Diese sind hier nicht
 * explizit aufgeführt (z.B. save, getById...). Außerdem können eigene
 * Methoden hinzugefügt werden (wichtig: auf Syntax/Aufbau achten), aus
 * denen SpringData automatisch eine Implementierung generiert.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public interface ImageRepository extends JpaRepository<ProductImage, Long> {
}
