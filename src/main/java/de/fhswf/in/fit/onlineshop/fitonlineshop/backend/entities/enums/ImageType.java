package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums;


/**
 * Das Enum ImageType enthält die möglichen Datentypen für die
 * Bilder zu einem Produkt.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public enum ImageType {
    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png"),
    GIF("gif");


    public final String label;

    ImageType(String label) {
        this.label = label;
    }

}