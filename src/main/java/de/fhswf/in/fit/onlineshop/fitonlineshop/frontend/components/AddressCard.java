package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Address;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.User;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.AddressService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;


@CssImport("/themes/onlineshop/components/address-card.css")
public class AddressCard {

    private final AddressService addressService;
    private final UserService userService;

    private VerticalLayout addressCard;
    private HorizontalLayout buttons;
    private FormLayout addressCardLayout;
    private User user;

    private Label name;
    private Label street;
    private Label placeAndPostal;
    private Label country;
    private Label phoneNumber;
    private Label mail;

    private Button edit;
    private Button delete;

    public AddressCard(AddressService addressService, UserService userService){
        this.addressService = addressService;
        this.userService = userService;
        this.user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());;
    }

    public FormLayout createAddressCards(){
        addressCardLayout = new FormLayout();
        addressCardLayout.setId("address-card-address_card_layout");

        if (user.getAdresses().isEmpty()) {
            FormLayout horizontalLayout = new FormLayout();
            Label label = new Label("Es wurden bisher keine Adressen angelegt");
            label.setId("address-card-layout-label_failure");
            Icon icon = new Icon(VaadinIcon.FROWN_O);
            icon.setId("address-card-layout-icon_failure");
            horizontalLayout.add(label, icon);
            horizontalLayout.setClassName("address-card-layout-failure_layout");
            addressCardLayout.add(horizontalLayout);
            return addressCardLayout;
        }
        for (Address address : user.getAdresses()) {
            addressCardLayout.add(createAddressCard(address));
        }
        addressCardLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("500px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("1000px", 3, FormLayout.ResponsiveStep.LabelsPosition.TOP));
        return addressCardLayout;
    }

    private VerticalLayout createAddressCard(Address address){
        addressCard = new VerticalLayout();
        buttons = new HorizontalLayout();

        name = new Label(address.getFirstName() + " " + address.getLastName());
        name.setId("address-card-label_name");
        street = new Label(address.getStreet());
        street.setClassName("address-card-labels");
        placeAndPostal = new Label(address.getPostalCode() + " " + address.getPlace());
        placeAndPostal.setClassName("address-card-labels");
        country = new Label(address.getCountry());
        country.setClassName("address-card-labels");
        phoneNumber = new Label(address.getPhoneNumber());
        phoneNumber.setClassName("address-card-labels");
        phoneNumber.setId("address-card-phone_number");
        mail = new Label(address.getMail());
        mail.setClassName("address-card-labels");

        edit = new Button("Bearbeiten");
        edit.setClassName("address-card-buttons");
        edit.addClickListener(event -> {
            ChangeAddressDialog changeAddressDialog = new ChangeAddressDialog(addressService, userService, address);
            changeAddressDialog.open();
        });

        delete = new Button("Entfernen");
        delete.setClassName("address-card-buttons");
        delete.addClickListener(event -> {
            user.deleteAddress(address);
            userService.saveUser(user);
            UI.getCurrent().getPage().reload();
        });

        buttons. add(edit, delete);

        addressCard.setId("address-card");
        addressCard.add(name, street, placeAndPostal, country, phoneNumber, mail, buttons);

        return addressCard;
    }
}
