package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;


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


    public LoginView() {
        getElement().getStyle().set("background", "hsl(0, 36%, 39%)");

        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        login = new LoginForm();
        login.setAction("login");


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
