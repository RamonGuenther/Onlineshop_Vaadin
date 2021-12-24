package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Category;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Der ProductService implementiert die Business-Logic für die Produkte.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public void saveProduct(String name, Double price, int inStock, String description){
        productRepository.save(new Product(name, price, inStock, description));
    }

    public Product getProductById(Long id){
        return productRepository.getById(id);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> findByCategory(Category category){
        return productRepository.findAllByCategoriesIsContaining(category);
    }

    public List<Product> productFilter(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return productRepository.findAll(); //wenn filterText leer zeige Alles an
        } else {
            return productRepository.findAllByName(filterText); //ansonsten suche nach dem Datensatz
        }
    }

}
