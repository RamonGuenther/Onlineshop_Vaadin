package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Category;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Das ProductRepository bietet eine Reihe von Standardimplementierungen für
 * den Datenbankzugriff auf die Product-Tabelle. Diese sind hier nicht
 * explizit aufgeführt (z.B. save, getById...). Außerdem können eigene
 * Methoden hinzugefügt werden (wichtig: auf Syntax/Aufbau achten), aus
 * denen SpringData automatisch eine Implementierung generiert.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoriesIsContaining(Category category);

    @Query("select p from Product p where lower(p.name) like lower(concat('%', :searchTerm, '%')) or  lower(p.id) like lower(concat('%', :searchTerm, '%')) ")
    List<Product> findAllByName (String searchTerm);
}
