package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Orders;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.User;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.OrderState;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Der OrdersService implementiert die Business-Logic für die Bestellungen.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;


    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public void saveOrder(Orders order){
        ordersRepository.save(order);
    }

    public Orders getOrderById(Long id){
        return ordersRepository.getById(id);
    }

    public List<Orders> findAllOrders(){
        return ordersRepository.findAll();
    }

    public List<Orders> findOrdersByUser(User user){
        return user.getOrders();
    }

}
