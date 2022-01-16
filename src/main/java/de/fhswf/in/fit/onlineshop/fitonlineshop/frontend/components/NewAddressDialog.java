package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Address;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.User;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.AddressService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Die Klasse NewAddressDialog erstellt einen Dialog zum hinzufügen einer
 * neuen Adresse.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@CssImport("/themes/onlineshop/components/new-address-dialog.css")
public class NewAddressDialog extends Dialog {

    private final AddressService addressService;
    private final UserService userService;

    public NewAddressDialog(AddressService addressService, UserService userService){
        this.addressService = addressService;
        this.userService = userService;
        createDialog();
    }

    private void createDialog(){
        setWidth("500px");
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        H1 title= new H1("Adresse hinzufügen");
        title.setId("new-address-dialog-title");

        AddressForm newAddressForm = new AddressForm();

        HorizontalLayout buttonBox = new HorizontalLayout();
        buttonBox.setId("new-address-dialog-buttonbox");

        Button buttonChoose = new Button("Speichern");
        buttonChoose.setClassName("new-address-dialog-buttons");
        buttonChoose.addClickListener(event -> {

            if(newAddressForm.getLastName().isEmpty() || newAddressForm.getFirstName().isEmpty() ||
                    newAddressForm.getStreet().isEmpty() || newAddressForm.getPlace().isEmpty() ||
                    newAddressForm.getPostal().isEmpty() || newAddressForm.getCountry().isEmpty() ||
                    newAddressForm.getPhone().isEmpty() || newAddressForm.getMail().isEmpty()){
                NotificationError.show("Bitte alle Felder ausfüllen");
                return;
            }
            Address newAddress = new Address(
                    newAddressForm.getLastName().getValue(),
                    newAddressForm.getFirstName().getValue(),
                    newAddressForm.getStreet().getValue(),
                    newAddressForm.getPlace().getValue(),
                    newAddressForm.getPostal().getValue(),
                    newAddressForm.getCountry().getValue(),
                    newAddressForm.getPhone().getValue(),
                    newAddressForm.getMail().getValue());

            addressService.saveAddress(newAddress);
            User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            user.addAddress(newAddress);
            userService.saveUser(user);

            this.close();
            UI.getCurrent().getPage().reload();
        });

        Button buttonCancel = new Button("Abbrechen");
        buttonCancel.setClassName("new-address-dialog-buttons");
        buttonCancel.addClickListener(dialogCloseEvent -> this.close());

        buttonBox.add(buttonChoose, buttonCancel);

        Div div = new Div(title, newAddressForm, buttonBox);
        div.setId("new-address-dialog");
        add(div);
    }
}
