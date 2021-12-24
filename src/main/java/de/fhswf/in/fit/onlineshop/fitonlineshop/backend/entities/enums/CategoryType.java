package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Das Enum CategoryType enthält alle möglichen Kategorien, denen die
 * Produkte des Onlineshops angehören können.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public enum CategoryType {
    TECHNIK ("Technik"),
    COMPUTER ("Computer"),
    SMARTPHONES ("Smartphones"),
    KONSOLEN ("Konsolen"),
    GAMES ("Games"),
    HAUSHALTSGERAETE ("Haushaltsgeräte"),
    MUSIKANLAGEN ("Musikanlagen"),
    CDS_UND_VINYL ("CD's und Vynil"),
    LAUTSPRECHER ("Lautsprecher"),
    DVD_UND_BLUERAY ("DVD und BlueRay"),
    FERNSEHER_UND_HEIMKINO ("Fernseher und Heimkino"),
    SPEICHERMEDIEN ("Speichermedien");

    public final String label;

    CategoryType(String label) {
        this.label = label;
    }

}