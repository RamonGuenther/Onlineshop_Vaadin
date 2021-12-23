package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * Ein spezieller Button für das Wechseln zwischen dunklem
 * und hellem Design.
 *
 * @author Ramon Günther
 */

@CssImport("/themes/onlineshop/components/button-switch-theme.css")
public class ButtonSwitchTheme extends Button {

    private final Icon sun;
    private final Icon moon;

    /**
     * Der Konstruktor setzt die Attribute des Buttons und
     * ruft das Event aus.
     */
    public ButtonSwitchTheme(){
        sun = new Icon(VaadinIcon.SUN_O);
        sun.setClassName("iconSunMoon");
        moon = new Icon(VaadinIcon.MOON);
        moon.setClassName("iconSunMoon");
        setText("Darkmode");
        setIcon(moon);
        setClassName("themeButtonPosition");
        setId("buttonLightMode");
        addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        addClickListener(e -> themeEvent());
    }

    /**
     * Die Methode themeEvent löst das Event für das Wechseln des
     * Designs aus.
     */
    void themeEvent(){
        ThemeList themeList = UI.getCurrent().getElement().getThemeList();
        if (themeList.contains(Lumo.DARK)) {
            setIcon(moon);
            setText("Darkmode");
            setId("buttonLightMode");
            themeList.remove(Lumo.DARK);
        } else {
            setIcon(sun);
            setText("Lightmode");
            setId("buttonDarkMode");
            themeList.add(Lumo.DARK);
        }
    }
}
