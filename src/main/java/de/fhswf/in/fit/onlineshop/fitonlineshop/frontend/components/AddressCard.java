package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Address;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.User;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.AddressService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Die Klasse AddressCard ist für das Erstellen der einzelnen "Adresskarten"
 * zuständig.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@CssImport("/themes/onlineshop/components/address-card.css")
public class AddressCard {

    private final AddressService addressService;
    private final UserService userService;

    private final User user;

    public AddressCard(AddressService addressService, UserService userService){
        this.addressService = addressService;
        this.userService = userService;
        this.user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /**
     * Erstellt anhand aller gespeicherten Adressen des Nutzers in der Datenbank und der
     * Methode createProductCard, einzelne Karten, die dann zurückgegeben werden.
     *
     * @return Formlayout mit allen Produktkarten
     */
    public FormLayout createAddressCards(){
        FormLayout addressCardLayout = new FormLayout();
        addressCardLayout.setId("address-card-address_card_layout");

        if (user.getAdresses().isEmpty()) {
            addressCardLayout.add(new Label("Es wurden bisher keine Adressen angelegt!"));
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

    /**
     * Erstellt eine Adresskarte.
     *
     * @param address Adresse des Nutzers
     * @return VerticalLayout mit der Adresskarte
     */
    private VerticalLayout createAddressCard(Address address){
        VerticalLayout addressCard = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setId("address-card-buttonbox");

        Label name = new Label(address.getFirstName() + " " + address.getLastName());
        name.setId("address-card-label_name");
        Label street = new Label(address.getStreet());
        street.setClassName("address-card-labels");
        Label placeAndPostal = new Label(address.getPostalCode() + " " + address.getPlace());
        placeAndPostal.setClassName("address-card-labels");
        Label country = new Label(address.getCountry());
        country.setClassName("address-card-labels");
        Label phoneNumber = new Label(address.getPhoneNumber());
        phoneNumber.setClassName("address-card-labels");
        phoneNumber.setId("address-card-phone_number");
        Label mail = new Label(address.getMail());
        mail.setClassName("address-card-labels");

        Button edit = new Button("Bearbeiten");
        edit.setClassName("address-card-buttons");
        edit.addClickListener(event -> {
            ChangeAddressDialog changeAddressDialog = new ChangeAddressDialog(addressService, userService, address);
            changeAddressDialog.open();
        });

        Button delete = new Button("Entfernen");
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
