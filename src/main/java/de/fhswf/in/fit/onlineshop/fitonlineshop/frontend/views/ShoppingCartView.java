package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.button.Button;
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
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.AddressService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.OrderedProductService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.OrdersService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.UserService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.SubmitOrderDialog;
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

    private List<OrderedProduct> orderedProducts;
    private double gesamtbetrag;

    public ShoppingCartView(OrderedProductService orderedProductService, OrdersService ordersService, UserService userService, AddressService addressService){

        //TODO: Warenkorb menge anpassen oder direkt löschen
        H1 ordersTitle = new H1("Warenkorb");

        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Orders order = user.getShoppingCart();
        List<OrderedProduct> orderedProducts = order.getOrderedProducts();

        Grid<OrderedProduct> orderedProductGrid = new Grid<>();
        orderedProductGrid.setItems(orderedProducts);

        orderedProductGrid.addColumn(orders1 -> orders1.getId().getProduct().getName()).setHeader("Produktname");
        orderedProductGrid.addColumn(OrderedProduct::getAmount).setHeader("Menge");
        orderedProductGrid.addColumn(price -> price.getId().getProduct().getPrice() + " €").setHeader("Einzelpreis");

        orderedProductGrid.addColumn(price -> price.getId().getProduct().getPrice() * price.getAmount() + " €").setHeader("Gesamtpreis");
        gesamtbetrag = 0;

        for (OrderedProduct orderedProduct : orderedProducts) {
            gesamtbetrag = orderedProduct.getId().getProduct().getPrice() * orderedProduct.getAmount() + gesamtbetrag;
        }
        orderedProductGrid.getColumns().get(0).setFooter(
                "Artikel: " + orderedProducts.size() + "       |       Gesamtbetrag: " + gesamtbetrag + "€"
        );

        Button orderButton = new Button("Bestellen");

        add(ordersTitle, orderedProductGrid, orderButton);

        SubmitOrderDialog submitOrderDialog = new SubmitOrderDialog(userService,addressService, ordersService, gesamtbetrag);
        //Warenkorb bestellen dann neue leere order erstellen und über order und user speichern! die bestellte order auf bestellt setzen und übber modal oder ähnliches adressen hinzufügen

        orderButton.addClickListener(e ->submitOrderDialog.open());
    }
}
