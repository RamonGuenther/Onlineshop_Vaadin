package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Category;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.ProductService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.ProductFormlayout;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Blatt 6
 *      - F050: Benutzer kann Kategorien eines Produkts einsehen.
 *      - F060: Benutzer kann Produkte einer Kategorie auflisten lassen.
 *      - F020: Benutzer kann Bilder zu einem Produkt einsehen.
 *
 *
 * F010: Benutzer kann Produkte einsehen. done
 * F030: Benutzer kann Preise zu einem Produkt einsehen.done
 * F040: Benutzer sieht, ob ein Produkt verfügbar ist. done
 * F070: Benutzer kann nach einer Produktnummer ein Produkt suchen. done
 * F080: Benutzer kann nach einem Produktnamen ein Produkt suchen. done
 *
 *
 *
 * TODO: Blatt 7
 *
 * F010: Benutzer kann verfügbare Produkte in seinen Warenkorb ablegen.
 * F020: Benutzer kann Produkte aus seinem Warenkorb entfernen.
 * F030: Benutzer kann gewünschte Anzahl eines zu bestellenden Produkts wählen.
 * F040: Benutzer kann sich einloggen.
 * F050: Eingeloggte Benutzer können Adressen hinzufügen
 * F060: Eingeloggter Benutzer kann seinen Warenkorb mit gewählter Adresse
 * bestellen.
 * F070: Eingeloggter Benutzer kann seine Bestellungen einsehen.
 * F080: Eingeloggter Benutzer kann seine Adressen einsehen.
 * F090: Eingeloggter Benutzer kann seine Adressen entfernen.
 * F100: Eingeloggte Benutzer können sich ausloggen.
 *
 */
@Route(value = "produkte", layout = MainLayout.class)
@PageTitle("Projektverwaltung | Projektübersicht")
public class ProductView extends VerticalLayout {

    public ProductView(ProductService productService) {

        //TODO: Kategorie Tabs einbauen wie Badge mechanik und anhand dessen die Cards generieren, Filter im repository mit Spring statt query umändern!

        List<Product> productList = productService.findAllProducts();

//        Tabs tabs = new Tabs();
//
//
//        List<Category> categoryList = new ArrayList<>();
//
//        for(Product product : productList){
//            categoryList.addAll(product.getCategories());
//        }
//
//        for(Category category : categoryList) {
//            tabs.add(new Tab(category.getCategoryType().label));
//        }
//
//
//        add(tabs);
//
//        tabs.setWidth("100%");

        TextField filterText = new TextField();
        filterText.setId("filterText");
        filterText.setValueChangeMode(ValueChangeMode.EAGER);

        filterText.setPlaceholder("Suche nach Karten..");
        filterText.setClearButtonVisible(true);

        ProductFormlayout productFormlayout = new ProductFormlayout();
        add(filterText);
        VerticalLayout verticalLayout = productFormlayout.createProductCards(productList);
        add(verticalLayout);

        //samsung galaxy ultra 21

        filterText.addValueChangeListener(e -> {
            verticalLayout.removeAll();
            verticalLayout.add(productFormlayout.createProductCards(productService.productFilter(e.getValue())));
        });



//        Grid<Product> productGrid = new Grid<>();

//        productGrid.addColumn(Product::getId).setHeader("Produkt Id");
//        productGrid.addColumn(Product::getName).setHeader("Produktname");
//        productGrid.addColumn(Product::getPrice).setHeader("Preis");
//        productGrid.addColumn(Product::getMainCategory).setHeader("Hauptkategorie");
//        productGrid.addColumn(Product::getDescription).setHeader("Produktbeschreibung");
//        productGrid.setItems(productList);
//        productGrid.getColumns().forEach(col -> col.setAutoWidth(true));
//        productGrid.getColumns().get(0).setFooter("Anzahl: " + productList.size());
//        add(productGrid);

    }
}
