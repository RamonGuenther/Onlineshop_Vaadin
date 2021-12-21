package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.OrderedProduct;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Orders;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.primaryKeys.OrderedProductKey;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories.OrderedProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Der OrderedProductService implementiert die Business-Logic für die
 * Produkte zu einer Bestellung.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */

@Service
public class OrderedProductService {

    private final OrderedProductRepository orderedProductRepository;

    public OrderedProductService(OrderedProductRepository orderedProductRepository) {
        this.orderedProductRepository = orderedProductRepository;
    }

    public void saveOrderedProduct(OrderedProduct orderedProduct){
        orderedProductRepository.save(orderedProduct);
    }

    public void saveOrderedProduct(Product product, Orders order, Integer amount){
        OrderedProductKey id = new OrderedProductKey(product, order);
        orderedProductRepository.save(new OrderedProduct(id, amount));
    }

    public List<OrderedProduct> getOrderedProductsByOrder(Orders order){
        return orderedProductRepository.findAllById_Orders(order);
    }

}
