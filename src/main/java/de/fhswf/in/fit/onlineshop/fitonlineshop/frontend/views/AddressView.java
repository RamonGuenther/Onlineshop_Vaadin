package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Address;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.User;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.AddressService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Hier kann man Adressen dann einfügen löschen etc.
 */
@Route(value = "profil", layout = MainLayout.class)
@PageTitle("R & I | Profil")
@CssImport("/themes/onlineshop/views/address-view.css")
public class AddressView extends VerticalLayout {

    private final AddressService addressService;
    private final UserService userService;

    public AddressView(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
        createProfileView();
    }

    private void createProfileView() {

        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        List<Address> addressList = new ArrayList<>(user.getAdresses());
//        System.out.println(user.getAdresses().size());

        //Modal zum hinzufügen einer neuen Adresse
        //Button zum löschen das angezeigten Select


        List<String> addressId = new ArrayList<>();

        addressList.forEach(e -> addressId.add(e.getId().toString()));
        Select<String> selectAddressId = new Select<>(); //neben dem Select ein plus button um eine Neue Adresse anzulegen alle Felder werden geleert und können dann eingetagen werden
        selectAddressId.setId("address-view-select_address_id");
        selectAddressId.setItems(addressId);
//        addressList.stream().map(e -> e.getId().toString()).forEach(selectAddressId::setItems);

//        addressService.

        //Grid mit allen adressen und beim klick wandelt es sich um zum formlayout und dann wie gehabt


        Grid<Address> addressGrid = new Grid<>();

        addressGrid.setItems(addressList);

        addressGrid.addColumn(Address::getId).setHeader("Id");
        add(addressGrid);

        H1 title = new H1("Profil");
        title.setId("address-view-title");


        TextField firstname = new TextField("Vorname");
        firstname.setReadOnly(true);

        TextField lastname = new TextField("Nachname");
        lastname.setReadOnly(true);

        TextField email = new TextField("Email");
        email.setReadOnly(true);

        TextField address = new TextField("Straße/Hausnummer");
        address.setReadOnly(true);

        TextField plz = new TextField("Postleitzahl");
        plz.setReadOnly(true);

        TextField location = new TextField("Wohnort");
        location.setReadOnly(true);

        Button editProfileButton = new Button("Profil bearbeiten");
        editProfileButton.addClassName("buttonsProfile");
        editProfileButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        HorizontalLayout horizontalLayout = new HorizontalLayout(editProfileButton);

        FormLayout formLayout = new FormLayout(title, selectAddressId, firstname, address, lastname, plz, location
                , email, horizontalLayout);
        formLayout.setClassName("address-view-formlayout");

        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 4, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.setColspan(title, 4);
        formLayout.setColspan(selectAddressId, 4);
        formLayout.setColspan(firstname, 2);
        formLayout.setColspan(lastname, 2);
        formLayout.setColspan(email, 2);
        formLayout.setColspan(plz, 1);
        formLayout.setColspan(location, 1);
        formLayout.setColspan(address, 2);
        formLayout.setColspan(horizontalLayout, 4);


        add(formLayout);

    }
}

