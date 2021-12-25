package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Category;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.CategoryType;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories.CategoryRepository;
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

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

    public List<Product> findAllByCategory(CategoryType categoryType){
        if(categoryType == CategoryType.ALLE_KATEGORIEN){
            return productRepository.findAll();
        }
        Category category = categoryRepository.getCategoryByCategoryType(categoryType);
        return productRepository.findAllByCategoriesIsContaining(category);
    }

    public List<Product> productFilter(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return productRepository.findAll(); //wenn filterText leer zeige Alles an
        } else {
            return productRepository.findAllByNameOrId(filterText); //ansonsten suche nach dem Datensatz
        }
    }

    //TODO: Siehe Repository letzter Befehl
//    public List<Product> productFilterTest(String filterText , CategoryType categoryType) {
//        Category category = categoryRepository.getCategoryByCategoryType(categoryType);
//
//        if (filterText == null || filterText.isEmpty()) {
//            return productRepository.findAllByCategories(category); //wenn filterText leer zeige Alles an
//        } else {
//            return productRepository.findAllByNameOrIdAndCategories(filterText,filterText, category); //ansonsten suche nach dem Datensatz
//        }
//    }

}
