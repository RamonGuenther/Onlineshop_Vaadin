package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
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

import java.util.List;

/**
 * TODO: Warenkorb = OrderedProducts also muss man für den Bestllung abschicken eine new Order erstellen wo man Adressen auswählen kann
 *          leeres Orders objekt anlegen für später wenn man billing address und so im Warenkorb aussuchen muss und auf bestellen klickt damit der Warenkorb dann wieder leer ist
 *          - ImageList lösung finden 
 */
@Route(value = "warenkorb", layout = MainLayout.class)
@PageTitle("R & I | Warenkorb")
@CssImport("/themes/onlineshop/views/shopping-cart-view.css")
public class ShoppingCartView extends VerticalLayout {

    private final Grid<OrderedProduct> orderedProductGrid;
    private final Label orderNumber;
    private final Label billingAddress;
    private final Label deliveryAddress;
    private final Label orderComment;
    private final FormLayout formLayout;
    private List<OrderedProduct> orderedProducts;
    private double gesamtbetrag;

    public ShoppingCartView(OrderedProductService orderedProductService, OrdersService ordersService, UserService userService){

        //Am anfang formlayout mit Rechnungsadresse etc. aus orders

        //Grid und wenn man auf details klickt kommt man wieder auf die productdetails mit der Id ausm grid
        //Footer mit anzahl der items und dem gesamt wert der bestellung
        /*
         *  Im Formlayout oder als Grid auch die Summe angeben die für das jeweilige Produkt ist theoretisch kann ich beim Grid value des preise mal die angeklickte Menge rechnen
         *  Warenkorb als Grid oder formlayouts wieder und unten die Summe angeben die insgesamt gezahlt werden soll und lieferadresse einstellen
         */

        H1 ordersTitle = new H1("Bestellungen");

        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        List<Orders> orders = ordersService.findOrdersByUser(user);

        Select<String> select = new Select<>();
        select.setLabel("Bestellungsnummer:");

        orders.stream().map(order -> order.getId().toString()).forEach(select::setItems);



        formLayout = new FormLayout();

        orderNumber = new Label("Bestellungsnummer: " );

        billingAddress = new Label("Rechnungsadresse: ");

        deliveryAddress = new Label("Lieferadresse: ");

        orderComment = new Label("Bestellkommentar: ");

        formLayout.add(orderNumber, billingAddress, deliveryAddress, orderComment);


//
//        //Grid kleiner machen und Menge bearbeitbar in Tabelle oder Dialog?
        orderedProductGrid = new Grid<>();

        orderedProductGrid.addColumn(orders1 -> orders1.getId().getProduct().getName()).setHeader("Produktname");
        orderedProductGrid.addColumn(OrderedProduct::getAmount).setHeader("Menge");
        orderedProductGrid.addColumn(price ->  price.getId().getProduct().getPrice() + " €").setHeader("Einzelpreis") ;

        orderedProductGrid.addColumn(price ->  price.getId().getProduct().getPrice()* price.getAmount() + " €").setHeader("preis");



        add(ordersTitle,select, formLayout,orderedProductGrid);


        select.addValueChangeListener(e -> {
            Orders order = ordersService.getOrderById(Long.parseLong(e.getValue()));
            System.out.println(order.getOrderComment());
            orderNumber.setText("Bestellungsnummer: " + order.getId());
            billingAddress.setText("Rechnungsadresse: " + order.getBillingAddress().getStreet() + ", " + order.getBillingAddress().getPostalCode() + ", " + order.getBillingAddress().getCountry());
            deliveryAddress.setText("Lieferadresse: " + order.getDeliveryAddress().getStreet() + ", " + order.getDeliveryAddress().getPostalCode() + ", " + order.getDeliveryAddress().getCountry());
            orderComment.setText("Bestellkommentar: " + order.getOrderComment());

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
