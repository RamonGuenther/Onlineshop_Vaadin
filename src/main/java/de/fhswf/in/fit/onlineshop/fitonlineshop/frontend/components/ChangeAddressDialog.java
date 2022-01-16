package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Address;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.AddressService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.UserService;

/**
 * Die Klasse ChangeAddressDialog erstellt einen Dialog zum Ändern der Informationen
 * einer Adresse.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public class ChangeAddressDialog extends Dialog {

    private final AddressService addressService;
    private final UserService userService;

    public ChangeAddressDialog(AddressService addressService, UserService userService, Address address){
        this.addressService = addressService;
        this.userService = userService;
        createDialog(address);
    }

    private void createDialog( Address address){
        setWidth("500px");
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        H1 title= new H1("Adresse hinzufügen");
        title.setId("new-address-dialog-title");

        AddressForm newAddressForm = new AddressForm();
        newAddressForm.setFirstName(address.getFirstName());
        newAddressForm.setLastName(address.getLastName());
        newAddressForm.setStreet(address.getStreet());
        newAddressForm.setPostal(address.getPostalCode());
        newAddressForm.setPlace(address.getPlace());
        newAddressForm.setCountry(address.getCountry());
        newAddressForm.setPhone(address.getPhoneNumber());
        newAddressForm.setMail(address.getMail());

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

            Address editAddress = addressService.getAddressById(address.getId());
            editAddress.setFirstName(newAddressForm.getFirstName().getValue());
            editAddress.setLastName(newAddressForm.getLastName().getValue());
            editAddress.setStreet(newAddressForm.getStreet().getValue());
            editAddress.setPlace(newAddressForm.getPlace().getValue());
            editAddress.setPostalCode(newAddressForm.getPostal().getValue());
            editAddress.setCountry(newAddressForm.getCountry().getValue());
            editAddress.setPhoneNumber(newAddressForm.getPhone().getValue());
            editAddress.setMail(newAddressForm.getMail().getValue());

            addressService.saveAddress(editAddress);

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
