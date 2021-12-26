package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.AddressService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.UserService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.AddressCard;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.NewAddressDialog;

/**
 * Die Klasse AddressView erstellt eine View, in der die Adressen
 * der Benutzer angezeigt werden. Zusätzlich hat der Benutzer die
 * Möglichkeit neue Adressen hinzuzufügen, bestehende Addressen
 * zu ändern oder zu entfernen.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@Route(value = "profil", layout = MainLayout.class)
@PageTitle("R & I | Adressen")
@CssImport("/themes/onlineshop/views/address-view.css")
public class AddressView extends VerticalLayout {

    private final AddressService addressService;
    private final UserService userService;
    private final AddressCard addressCard;

    public AddressView(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
        this.addressCard = new AddressCard(addressService, userService);
        createProfileView();
        this.setId("address-view-layout");
    }

    private void createProfileView() {

        H1 title = new H1("Adressen");
        Button btnAddAddress = new Button();
        Icon icon = new Icon(VaadinIcon.PLUS);
        btnAddAddress.setIcon(icon);
        btnAddAddress.setId("address-view-layout-add");

        btnAddAddress.addClickListener(event -> {
            NewAddressDialog newAddressDialog = new NewAddressDialog(addressService, userService);
            newAddressDialog.open();
        });

        HorizontalLayout pageHeader = new HorizontalLayout(title, btnAddAddress);

        FormLayout addressCards = addressCard.createAddressCards();
        this.add(pageHeader, addressCards);
    }
}

