package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.OrderedProduct;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Orders;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.primaryKeys.OrderedProductKey;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories.OrderedProductRepository;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories.OrdersRepository;
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
    private final OrdersRepository ordersRepository;

    public OrderedProductService(OrderedProductRepository orderedProductRepository, OrdersRepository ordersRepository) {
        this.orderedProductRepository = orderedProductRepository;
        this.ordersRepository = ordersRepository;
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

    public void deleteOrderedProductById(OrderedProduct orderedProduct, Orders order){
        order.getOrderedProducts().remove(orderedProduct);
        ordersRepository.save(order);
        orderedProductRepository.delete(orderedProduct);
    }

}
