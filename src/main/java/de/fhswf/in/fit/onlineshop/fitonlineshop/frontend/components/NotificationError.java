package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

/**
 * Die Klasse NotificationError ist eine Notification zum
 * Anzeigen eines Hinweises zu einer Aktion der Applikation.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public class NotificationError extends Notification {

    public NotificationError(){
        setDuration(5000);
        setPosition(Position.BOTTOM_START);
        addThemeVariants(NotificationVariant.LUMO_ERROR);
    }

    public static NotificationError show(String text){

        NotificationError notification = new NotificationError();
        notification.setText(text);
        notification.open();
        return notification;
    }
}
