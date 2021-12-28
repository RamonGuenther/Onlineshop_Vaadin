package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.Lumo;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.OrderedProduct;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Orders;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.User;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.*;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.ButtonSwitchTheme;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.NotificationError;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.SubmitOrderDialog;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

/**
 * TODO: Leere adressliste Smiley!
 */
@Route(value = "warenkorb", layout = MainLayout.class)
@PageTitle("R & I | Warenkorb")
@CssImport("/themes/onlineshop/views/shopping-cart-view.css")
public class ShoppingCartView extends VerticalLayout {

    private Button orderButton;
    private OrderedProduct orderedProductEdit;
    private Button deleteOrderedProductButton;
    private int temp;
    private double gesamtbetrag;
    private Button updateAmountButton;
    private boolean checkOperation; //boolean um herauszufinden ob gespeichert oder abgebrochen wurde beim Ändern der menge

    public ShoppingCartView(ProductService productService,
                            OrderedProductService orderedProductService,
                            OrdersService ordersService,
                            UserService userService,
                            AddressService addressService) {

        H1 ordersTitle = new H1("Warenkorb");
        ordersTitle.setId("shopping-cart-view-title");

        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Orders order = user.getShoppingCart();
        List<OrderedProduct> orderedProducts = order.getOrderedProducts();


        Grid<OrderedProduct> orderedProductGrid = new Grid<>();
        orderedProductGrid.setId("shopping-cart-view-grid");

        orderedProductGrid.setItems(orderedProducts);

        orderedProductGrid.addColumn(orders1 -> orders1.getId().getProduct().getName()).setHeader("Produktname");
        orderedProductGrid.addColumn(OrderedProduct::getAmount).setHeader(createAmountCellHeader());
        orderedProductGrid.addColumn(price -> price.getId().getProduct().getPrice() + " €").setHeader("Einzelpreis");
        orderedProductGrid.addColumn(price -> price.getId().getProduct().getPrice() * price.getAmount() + " €").setHeader("Gesamtpreis");

        orderedProductGrid.getColumns().get(0).setWidth("300px");

        gesamtbetrag = 0;

        for (OrderedProduct orderedProduct : orderedProducts) {
            gesamtbetrag += orderedProduct.getId().getProduct().getPrice() * orderedProduct.getAmount();
        }


        orderedProductGrid.getColumns().get(0).setFooter(
                "Artikel: " + orderedProducts.size() + "       |       Gesamtbetrag: " + gesamtbetrag + "€"
        );

        orderedProductGrid.addComponentColumn(item -> {
            deleteOrderedProductButton = new Button(VaadinIcon.TRASH.create());
            deleteOrderedProductButton.setId("shopping-cart-view-delete_orderedProduct_button");
            deleteOrderedProductButton.addClickListener(e -> {
                Product product = item.getId().getProduct();
                product.setInStock(product.getInStock() + item.getAmount());
                productService.saveProduct(product);
                orderedProductService.deleteOrderedProductById(item, order);
//                UI.getCurrent().getPage().reload();
                orderedProductGrid.getDataProvider().refreshAll();
                gesamtbetrag = 0;
                for (OrderedProduct orderedProduct : orderedProducts) {
                    gesamtbetrag = orderedProduct.getId().getProduct().getPrice() * orderedProduct.getAmount() + gesamtbetrag;
                }
                orderedProductGrid.getColumns().get(0).setFooter(
                        "Artikel: " + orderedProducts.size() + "       |       Gesamtbetrag: " + gesamtbetrag + "€"
                );
                if(orderedProducts.isEmpty()){
                    orderButton.setVisible(false);
                }
            });
            return deleteOrderedProductButton;
        });

        orderedProductGrid.addItemDoubleClickListener(e -> {
            UI.getCurrent().navigate(ProductDetailsView.class, new RouteParameters(
                    new RouteParam("productId", e.getItem().getId().getProduct().getId().toString())));
        });

        //Binder spaß

        Editor<OrderedProduct> editor = orderedProductGrid.getEditor();

        Binder<OrderedProduct> binder = new Binder<>(OrderedProduct.class);

        editor.setBinder(binder);

        IntegerField amountIntegerField = new IntegerField();
        amountIntegerField.setMin(1);
        amountIntegerField.setHasControls(true);

        //Binder spaß
        binder.bind(amountIntegerField, "amount");
        orderedProductGrid.getColumns().get(1).setEditorComponent(amountIntegerField);

        //Listener, wenn auf Zeile geklickt wird
        orderedProductGrid.addSelectionListener(event -> {
            if (event.getFirstSelectedItem().isEmpty()) {
                orderedProductEdit = null;
                return;
            }
            orderedProductEdit = event.getFirstSelectedItem().get();
        });

        //Listener für den Bleistift
        updateAmountButton.addClickListener(e -> {
            if (orderedProductEdit == null) {
                NotificationError.show("Bitte eine Zeile auswählen und wieder auf den Stift klicken!");
                return;
            }
            editor.editItem(orderedProductEdit);
        });


        //Listener, wenn der Editor geöffnet wird
        editor.addOpenListener(e -> { //orderedProductGrid.getEditor().addOpenListener() geht auch
            amountIntegerField.setMax(e.getItem().getId().getProduct().getInStock() + e.getItem().getAmount());
            temp = e.getItem().getAmount();
        });

        //Listener, wenn der Editor geschlossen wird egal ob close oder cancel
        editor.addCloseListener(event -> {
            if (!checkOperation) {
                UI.getCurrent().getPage().reload();
                return;
            }
            Product product = event.getItem().getId().getProduct();
            product.setInStock(product.getInStock() + temp - event.getItem().getAmount());
            productService.saveProduct(product);

            orderedProductService.saveOrderedProduct(event.getItem());
            gesamtbetrag = 0;
            for (OrderedProduct orderedProduct : orderedProducts) {
                gesamtbetrag = orderedProduct.getId().getProduct().getPrice() * orderedProduct.getAmount() + gesamtbetrag;
            }
            orderedProductGrid.getColumns().get(0).setFooter(
                    "Artikel: " + orderedProducts.size() + "       |       Gesamtbetrag: " + gesamtbetrag + "€"
            );
        });


        Button saveButton = new Button(VaadinIcon.CHECK.create());
        saveButton.addClickListener(e -> {
            if (amountIntegerField.getValue() < amountIntegerField.getMin()) {
                NotificationError.show("Ungültige Eingabe der Menge!");
                return;
            } else if (amountIntegerField.getValue() > amountIntegerField.getMax()) {
                NotificationError.show("Die maximale Anzahl an verfügbaren Artikeln beträgt: " + amountIntegerField.getMax() + "!");
                return;
            }
            checkOperation = true;
            editor.closeEditor();
        });
        saveButton.setId("shopping-cart-view-save_button");
        saveButton.setClassName("shopping-cart-view-grid_buttons");

        Button cancelButton = new Button(VaadinIcon.CLOSE.create());
        cancelButton.addClickListener(e -> {
            checkOperation = false;
            editor.cancel();
        });
        cancelButton.setId("shopping-cart-view-cancel_button");
        cancelButton.setClassName("shopping-cart-view-grid_buttons");

        Div buttons = new Div(saveButton, cancelButton);

        //Hier wird die Komponente gesetzt die greifen soll, wenn der Editor geöffnet wird
        orderedProductGrid.getColumns().get(4).setEditorComponent(buttons);


        //Alles um den Bestellvorgang abzuschließen

        orderButton = new Button("Bestellen");
        orderButton.setVisible(false);

        if(!orderedProducts.isEmpty()){
            orderButton.setVisible(true);
        }

        orderButton.setId("shopping-cart-view-order_button");

        SubmitOrderDialog submitOrderDialog = new SubmitOrderDialog(userService, addressService, ordersService, gesamtbetrag);

        orderButton.addClickListener(e ->{
            submitOrderDialog.open();
        });

        Div shoppingCartLayout = new Div();
        shoppingCartLayout.setClassName("shopping-cart-view-shopping_cart_layout");
        shoppingCartLayout.add(ordersTitle, orderedProductGrid, orderButton);
        add(shoppingCartLayout);
    }

    private Component createAmountCellHeader() {
        Span span = new Span("Menge");
        updateAmountButton = new Button(VaadinIcon.PENCIL.create());
        updateAmountButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        updateAmountButton.setId("shopping-cart-view-update_amount_button");
        HorizontalLayout layout = new HorizontalLayout(span, updateAmountButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSpacing(false);
        return layout;
    }


}
