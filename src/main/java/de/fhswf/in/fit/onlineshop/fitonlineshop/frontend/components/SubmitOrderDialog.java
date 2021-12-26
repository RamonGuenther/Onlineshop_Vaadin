package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Address;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Orders;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.User;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.OrderState;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.AddressService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.OrdersService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;


@CssImport("/themes/onlineshop/components/submit-order-dialog.css")
public class SubmitOrderDialog extends Dialog {

    private final Label billingAddressStreet;
    private final Label deliveryAddressStreet;
    private final TextArea orderComment;
    private final FormLayout formLayout;
    private final Label billingAddressPlace;
    private final Label billingAddressPhoneNumber;
    private final Label billingAddressMail;
    private final Label billingAddressName;
    private final Label deliveryAddressName;
    private final Label deliveryAddressPlace;
    private final Label deliveryAddressPhoneNumber;
    private final Label deliveryAddressMail;
    private final Label billingAddressCountry;
    private final Label deliveryAddressCountry;
    private Address address;
    private Address address1;

    public SubmitOrderDialog(UserService userService, AddressService addressService, OrdersService ordersService, double gesamtbetrag){

        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        Button closeButton = new Button(VaadinIcon.CLOSE_CIRCLE.create());
        closeButton.setId("search-drive-result-view-close_button");
        closeButton.addClickListener(e -> close());
        add(closeButton);

        H2 ordersTitle = new H2("Bestellung abschließen");

        Label totalLabel = new Label("Betrag: " + gesamtbetrag + "€");

        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        List<Address> addressList =  user.getAddresses();

        List<String> streetList = new ArrayList<>();

        addressList.forEach(e -> streetList.add(e.getId().toString()));

        Select<String> selectBillingAddress = new Select<>();
        selectBillingAddress.setLabel("Rechnungsadresse auswählen");
        selectBillingAddress.setItems(streetList);

        Select<String> selectDeliveryAddress = new Select<>();
        selectDeliveryAddress.setLabel("Lieferadresse auswählen");
        selectDeliveryAddress.setItems(streetList);

        formLayout = new FormLayout();
        formLayout.setClassName("orders-view-formlayout");


        H4 billingAddressTitle = new H4("Rechnungsadresse");
        billingAddressName = new Label();
        billingAddressStreet = new Label();
        billingAddressPlace = new Label();
        billingAddressCountry = new Label();
        billingAddressPhoneNumber = new Label();
        billingAddressMail = new Label();


        H4 deliveryAddressTitle = new H4("Lieferadresse");
        deliveryAddressName = new Label();
        deliveryAddressStreet = new Label();
        deliveryAddressPlace = new Label();
        deliveryAddressCountry = new Label();
        deliveryAddressPhoneNumber = new Label();
        deliveryAddressMail = new Label();

        orderComment = new TextArea();
        orderComment.setLabel("Bestellkommentar");
        orderComment.setId("orders-view-order_comment");

        formLayout.setClassName("orders-view-formlayout");
        formLayout.add(
                ordersTitle,
                totalLabel,
                selectDeliveryAddress, selectBillingAddress,
                deliveryAddressTitle, billingAddressTitle,
                deliveryAddressName, billingAddressName,
                deliveryAddressStreet, billingAddressStreet,
                deliveryAddressPlace, billingAddressPlace,
                deliveryAddressCountry, billingAddressCountry,
                deliveryAddressPhoneNumber, billingAddressPhoneNumber,
                deliveryAddressMail, billingAddressMail,
                orderComment
        );

        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("500px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.setColspan(ordersTitle, 4);
        formLayout.setColspan(totalLabel, 4);
        formLayout.setColspan(orderComment, 4);

        selectDeliveryAddress.addValueChangeListener(e -> {
            address = addressService.getAddressById(Long.parseLong(e.getValue()));
            deliveryAddressName.setText(address.getLastName() + ", " + address.getFirstName());
            deliveryAddressStreet.setText(address.getStreet());
            deliveryAddressPlace.setText(address.getPostalCode() + ", " + address.getPlace());
            deliveryAddressCountry.setText(address.getCountry());
            deliveryAddressPhoneNumber.setText(address.getPhoneNumber());
            deliveryAddressMail.setText(address.getMail());

        });


        selectBillingAddress.addValueChangeListener(e ->{
            address1 = addressService.getAddressById(Long.parseLong(e.getValue()));
            billingAddressName.setText(address1.getLastName() + ", " + address1.getFirstName());
            billingAddressStreet.setText(address1.getStreet());
            billingAddressPlace.setText(address1.getPostalCode() + ", " + address1.getPlace());
            billingAddressCountry.setText(address1.getCountry());
            billingAddressPhoneNumber.setText(address1.getPhoneNumber());
            billingAddressMail.setText(address1.getMail());
        });


        selectBillingAddress.setValue(streetList.get(0));
        selectDeliveryAddress.setValue(streetList.get(0));

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button submitButton = new Button("Senden");

        Button cancelButton = new Button("Abbrechen");

        buttonLayout.add(submitButton,cancelButton);


        submitButton.addClickListener(e -> {
            Orders orders = user.getShoppingCart();
            orders.setDeliveryAddress(address);
            orders.setBillingAddress(address1);
            orders.setOrderComment(orderComment.getValue());
            orders.setOrderState(OrderState.BESTELLT);
            ordersService.saveOrder(orders);

            Orders order2 = new Orders();
            ordersService.saveOrder(order2);

            user.addOrder(order2);
            userService.saveUser(user);
        });

        cancelButton.addClickListener(e -> close());

        add(closeButton,formLayout, buttonLayout);

    }
}
