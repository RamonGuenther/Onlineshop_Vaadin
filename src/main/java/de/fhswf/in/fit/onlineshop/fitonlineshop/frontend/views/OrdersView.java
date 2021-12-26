package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.OrderedProduct;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Orders;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.User;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.OrderedProductService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.OrdersService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 *         Diese Seite ist eher die Bestellhistory
 */
@Route(value = "bestellungen", layout = MainLayout.class)
@PageTitle("R & I | Bestellungen")
@CssImport("/themes/onlineshop/views/orders-view.css")
public class OrdersView extends VerticalLayout {

    private final Grid<OrderedProduct> orderedProductGrid;
    private final Label orderNumber;
    private final Label billingAddress;
    private final Label deliveryAddress;
    private final Label orderComment;
    private final FormLayout formLayout;
    private List<OrderedProduct> orderedProducts;
    private double gesamtbetrag;

    public OrdersView(OrdersService ordersService, UserService userService){

        H1 ordersTitle = new H1("Bestellungen");
        ordersTitle.setId("orders-view-orders_title");

        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        List<Orders> orders = ordersService.findOrdersByUser(user);

        List<String> ordersId = new ArrayList<>();

        orders.forEach(e -> ordersId.add(e.getId().toString()));

        Select<String> orderNumberSelect = new Select<>();
        orderNumberSelect.setItems(ordersId);
        orderNumberSelect.setLabel("Bestellungsnummer:");
        orderNumberSelect.setId("orders-view-order_number_select");





        formLayout = new FormLayout();
        formLayout.setClassName("orders-view-formlayout");

        H4 orderNumberTitle = new H4("Bestellungsnummer");
        orderNumber = new Label();
        orderNumber.setId("orders-view-order_number");

        H4 billingAddressTitle = new H4("Rechnungsadresse");
        billingAddress = new Label();
        billingAddress.setId("orders-view-billing_address");

        H4 deliveryAddressTitle = new H4("LieferAdresse");
        deliveryAddress = new Label();
        deliveryAddress.setId("orders-view-delivery_address");

        H4 orderCommentTitle = new H4("Bestellkommentar:");
        orderComment = new Label();
        orderComment.setId("orders-view-order_comment");

        formLayout.add(
                ordersTitle,
                orderNumberSelect, new Label(),
                orderNumberTitle, orderCommentTitle,
                orderNumber, orderComment,
                billingAddressTitle, deliveryAddressTitle,
                billingAddress, deliveryAddress);

        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("500px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.setColspan(ordersTitle, 2);

        orderedProductGrid = new Grid<>();
        orderedProductGrid.setId("orders-view-grid");

        orderedProductGrid.addColumn(orders1 -> orders1.getId().getProduct().getName()).setHeader("Produktname");
        orderedProductGrid.addColumn(OrderedProduct::getAmount).setHeader("Menge");
        orderedProductGrid.addColumn(price ->  price.getId().getProduct().getPrice() + " €").setHeader("Einzelpreis") ;

        orderedProductGrid.addColumn(price ->  price.getId().getProduct().getPrice()* price.getAmount() + " €").setHeader("Gesamtpreis");



        add(formLayout,orderedProductGrid);


        orderNumberSelect.addValueChangeListener(e -> {
            Orders order = ordersService.getOrderById(Long.parseLong(e.getValue()));
            System.out.println(order.getOrderComment());
            orderNumber.setText(order.getId().toString());
            billingAddress.setText(order.getBillingAddress().getStreet() + ", " + order.getBillingAddress().getPostalCode() + ", " + order.getBillingAddress().getCountry());
            deliveryAddress.setText(order.getDeliveryAddress().getStreet() + ", " + order.getDeliveryAddress().getPostalCode() + ", " + order.getDeliveryAddress().getCountry());
            orderComment.setText(order.getOrderComment());

            orderedProducts = order.getOrderedProducts();

            gesamtbetrag = 0;

            for(OrderedProduct orderedProduct : orderedProducts){
                gesamtbetrag = orderedProduct.getId().getProduct().getPrice() * orderedProduct.getAmount() + gesamtbetrag;
            }

            orderedProductGrid.setItems(orderedProducts);

            orderedProductGrid.getColumns().get(0).setFooter("Artikel: " + orderedProducts.size() + "          |       Gesamtbetrag: " + gesamtbetrag + "€");

        });

    }
}

