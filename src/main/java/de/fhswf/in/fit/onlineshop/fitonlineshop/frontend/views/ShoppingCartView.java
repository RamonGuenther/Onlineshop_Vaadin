package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.OrderedProduct;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.OrderedProductService;

@Route(value = "warenkorb", layout = MainLayout.class)
@PageTitle("R & I | Warenkorb")
@CssImport("/themes/onlineshop/views/shopping-cart-view.css")
public class ShoppingCartView extends VerticalLayout {
    public ShoppingCartView(OrderedProductService orderedProductService){

        //Am anfang formlayout mit Rechnungsadresse etc. aus orders

        //Grid und wenn man auf details klickt kommt man wieder auf die productdetails mit der Id ausm grid
        //Footer mit anzahl der items und dem gesamt wert der bestellung
        /*
         *  Im Formlayout oder als Grid auch die Summe angeben die für das jeweilige Produkt ist theoretisch kann ich beim Grid value des preise mal die angeklickte Menge rechnen
         *  Warenkorb als Grid oder formlayouts wieder und unten die Summe angeben die insgesamt gezahlt werden soll und lieferadresse einstellen
         */

        Grid<OrderedProduct> test = new Grid<>();
        test.setItems(orderedProductService.);

    }
}
