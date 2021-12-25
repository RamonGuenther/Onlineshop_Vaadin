package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.PWA;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.ButtonSwitchTheme;
import org.springframework.security.core.context.SecurityContextHolder;

//PWA (Progressive Web-App - z.B. über Chrome)
@PWA(name = "R & I - Onlineshop", shortName = "R & I", enableInstallPrompt = false)
@CssImport("/themes/onlineshop/components/navbar/main-layout.css")
@CssImport(value = "/themes/onlineshop/components/navbar/menu-bar-button.css", themeFor = "vaadin-menu-bar-button")
public class MainLayout extends AppLayout {

    //Spaß Admin seite machen
    /*
    , offlineResources = {
        "./styles/offline.css",
        "./images/offline.png"}
     */

    private String name;

    /**
     * Die Konstruktor erstellt eine Menüleiste für die Applikation.
     */
    public MainLayout(){
        name = SecurityContextHolder.getContext().getAuthentication().getName();
        createMenuBar();
    }

    /**
     * Die Methode createMenuBar() baut die Menüleiste der Applikation
     * zusammen. Sie besteht aus einem Titel, einer Begrüßung, dem Logout-
     * Button und der Möglichkeit in einen Darkmode zu wechseln.
     */

    private void createMenuBar(){

        H1 title = new H1("R & I");
        title.addClassName("main-layout-title");
        title.addClickListener(e -> UI.getCurrent().navigate(ProductView.class));


        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.MATERIAL_OUTLINED);
        menuBar.setOpenOnHover(true);


        MenuItem menuItem = menuBar.addItem(name);

        SubMenu subMenu = menuItem.getSubMenu();

        HorizontalLayout profileMenuItemLayout = new HorizontalLayout(VaadinIcon.USER.create(), new Label("Profil"));
        MenuItem profile = subMenu.addItem(profileMenuItemLayout);


        MenuItem test = subMenu.addItem(new ButtonSwitchTheme());

        subMenu.add(new Hr());

        HorizontalLayout logoutMenuItemLayout = new HorizontalLayout();
        logoutMenuItemLayout.add(VaadinIcon.SIGN_OUT.create(), new Label("Abmelden"));

        MenuItem logout = subMenu.addItem(logoutMenuItemLayout);
        logout.addClickListener(e -> UI.getCurrent().getPage().setLocation("/logout"));

        Button cartButton = new Button("Warenkorb");
        cartButton.addClickListener(e -> UI.getCurrent().navigate(ShoppingCartView.class));
        cartButton.setIcon(VaadinIcon.CART_O.create());
        cartButton.setId("main-layout-cart_button");


        Label welcomeLabel = new Label("Willkommen " + name +  "!");
        welcomeLabel.setId("main-layout-welcome_label");

        HorizontalLayout leftNavbar = new HorizontalLayout(title, welcomeLabel);
        leftNavbar.setClassName("main-layout-left_navbar");


        HorizontalLayout rightNavbar = new HorizontalLayout(menuBar, cartButton);
        rightNavbar.setClassName("main-layout-right_navbar");

        HorizontalLayout header = new HorizontalLayout(leftNavbar, rightNavbar);
        header.setClassName("main-layout-navbar");

        addToNavbar(header);
    }
}
