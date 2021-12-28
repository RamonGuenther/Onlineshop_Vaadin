package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;


import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "admin", layout = MainLayout.class)
@PageTitle("R & I | Adminseite")
public class AdminView extends VerticalLayout {

    public AdminView(){
        add(new Label("Krasse Adminverwaltung..."));
    }
}
