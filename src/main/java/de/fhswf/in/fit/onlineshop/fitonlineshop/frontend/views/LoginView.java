package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.StreamResource;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.ProductService;

import java.io.ByteArrayInputStream;

/**
 * Die Klasse LoginView erstellt den Login der Applikation.
 * Es wird die Standard-Login-View von Vaadin verwendet.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@RouteAlias(value = "")
@Route("login")
@PageTitle("R & I Onlineshop | Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm login;

    private ProductService productService;

    public LoginView(ProductService productService) {
        addClassName("login-view");
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        login = new LoginForm();
        login.setAction("login");

//        Image image = productService.getProductById(3L).getVaadinImageList().get(1);
//        add(image);

        add(login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
