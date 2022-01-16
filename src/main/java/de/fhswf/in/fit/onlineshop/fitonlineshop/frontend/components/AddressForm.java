package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

/**
 * Die Klasse AddressForm erstellt das Formular, um eine Adresse hinzuzufügen.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public class AddressForm extends FormLayout {

    private final TextField firstName;
    private final TextField street;
    private final TextField place;
    private final TextField country;
    private final TextField postal;
    private final EmailField mail;
    private final TextField phone;
    private final TextField lastName;

    public AddressForm(){
        firstName = new TextField();
        firstName.setLabel("Vorname");
        lastName = new TextField();
        lastName.setLabel("Nachname");

        street = new TextField();
        street.setLabel("Straße/Hausnummer");

        place = new TextField();
        place.setLabel("Ort");

        country = new TextField();
        country.setLabel("Land");

        postal = new TextField();
        postal.setLabel("Postleitzahl");
        postal.setPattern("\\d*");
        postal.setPreventInvalidInput(true);

        mail = new EmailField();
        mail.setLabel("Email");
        mail.setClearButtonVisible(true);
        mail.setErrorMessage("Bitte eine gültige Mail angeben");

        phone = new TextField();
        phone.setLabel("Telefon");
        phone.setPattern("\\d*");
        phone.setPreventInvalidInput(true);

        add(firstName, lastName, street, postal, place, country, phone, mail);
        setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("500px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));
        setColspan(street, 2);
        setColspan(country, 2);
        setColspan(phone, 2);
        setColspan(mail, 2);
        setId("new-address-dialog-form");
    }

    public TextField getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.setValue(firstName);
    }

    public TextField getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street.setValue(street);
    }

    public TextField getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place.setValue(place);
    }

    public TextField getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country.setValue(country);
    }

    public TextField getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal.setValue(postal);
    }

    public EmailField getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.setValue(mail);
    }

    public TextField getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.setValue(phone);
    }

    public TextField getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.setValue(lastName);
    }
}
