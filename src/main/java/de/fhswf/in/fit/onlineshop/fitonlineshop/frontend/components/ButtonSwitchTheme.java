package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * Ein spezieller Button für das Wechseln zwischen dunklem
 * und hellem Design.
 *
 * @author Ramon Günther
 */

@CssImport("/themes/onlineshop/components/button-switch-theme.css")
public class ButtonSwitchTheme extends HorizontalLayout {

    private final Icon sun;
    private final Icon moon;
    private final Button button;
    private final Label label;

    /**
     * Der Konstruktor setzt die Attribute des Buttons und
     * ruft das Event aus.
     */
    public ButtonSwitchTheme(){
        button = new Button();
        label = new Label();
        label.setId("button-switch-theme-label");

        sun = new Icon(VaadinIcon.SUN_O);
        sun.setClassName("iconSunMoon");
        moon = new Icon(VaadinIcon.MOON);
        moon.setClassName("iconSunMoon");
        label.setText("Darkmode");
        button.setIcon(moon);
        button.setClassName("themeButtonPosition");
        button.setId("buttonLightMode");
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        addClickListener(e -> themeEvent());

        add(button, label);
    }

    /**
     * Die Methode themeEvent löst das Event für das Wechseln des
     * Designs aus.
     */
    void themeEvent(){
        ThemeList themeList = UI.getCurrent().getElement().getThemeList();
        if (themeList.contains(Lumo.DARK)) {
            button.setIcon(moon);
            label.setText("Darkmode");
            button.setId("buttonLightMode");
            themeList.remove(Lumo.DARK);
        } else {
            button.setIcon(sun);
            label.setText("Lightmode");
            button.setId("buttonDarkMode");
            themeList.add(Lumo.DARK);
        }
    }
}
