package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Category;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Das CategoryRepository bietet eine Reihe von Standardimplementierungen für
 * den Datenbankzugriff auf die Category-Tabelle. Diese sind hier nicht
 * explizit aufgeführt (z.B. save, getById...). Außerdem können eigene
 * Methoden hinzugefügt werden (wichtig: auf Syntax/Aufbau achten), aus
 * denen SpringData automatisch eine Implementierung generiert.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getCategoryByCategoryType(CategoryType categoryType);
}
