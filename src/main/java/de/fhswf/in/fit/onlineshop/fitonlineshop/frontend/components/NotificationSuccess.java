package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

/**
 * Die Klasse NotificationSuccess ist eine Notification für
 * eine Erfolgreiche Aktion.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
public class NotificationSuccess extends Notification {

    public NotificationSuccess(){
        setDuration(3000);
        setPosition(Position.BOTTOM_START);
        addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    public static NotificationSuccess show(String text){

        NotificationSuccess notification = new NotificationSuccess();
        notification.setText(text);
        notification.open();
        return notification;
    }
}
