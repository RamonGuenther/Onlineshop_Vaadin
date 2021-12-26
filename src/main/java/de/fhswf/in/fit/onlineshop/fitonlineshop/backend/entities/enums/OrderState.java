package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums;


/**
 * Das Enum OrderState enthält die möglichen Zustände für die
 * Bestellungen (Orders).
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public enum OrderState {

    WARENKORB("Warenkorb"),
    BESTELLT("Bestellt");

    public final String label;

    OrderState(String label) {
        this.label = label;
    }
}
