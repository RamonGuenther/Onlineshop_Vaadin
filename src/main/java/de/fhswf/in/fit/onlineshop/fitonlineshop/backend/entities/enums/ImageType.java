package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Das Enum ImageType enthält die möglichen Datentypen für die
 * Bilder zu einem Produkt.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public enum ImageType {
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif");

    private static final Map<String, ImageType> extMap;
    static {
        extMap = new HashMap<>();
        extMap.put("jpeg", ImageType.JPEG);
        extMap.put("jpg", ImageType.JPEG);
        extMap.put("png", ImageType.PNG);
        extMap.put("gif", ImageType.GIF);
    }

    public final String label;

    ImageType(String label) {
        this.label = label;
    }

//    public String getValue(){
//        return value;
//    }

    public static ImageType extToType(String ext){
        if(extMap.containsKey(ext)){
            return extMap.get(ext);
        } else {
            throw new RuntimeException("Extension not found in valid extensions!");
        }
    }
}