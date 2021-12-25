package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.CategoryType;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.ProductService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.ProductCard;

import java.util.List;

/**
 * TODO: Blatt 6
 *      - F050: Benutzer kann Kategorien eines Produkts einsehen. done
 *      - F060: Benutzer kann Produkte einer Kategorie auflisten lassen. done
 *      - F020: Benutzer kann Bilder zu einem Produkt einsehen. done
 *
 * F010: Benutzer kann Produkte einsehen. done
 * F030: Benutzer kann Preise zu einem Produkt einsehen.done
 * F040: Benutzer sieht, ob ein Produkt verfügbar ist. done
 * F070: Benutzer kann nach einer Produktnummer ein Produkt suchen. done
 * F080: Benutzer kann nach einem Produktnamen ein Produkt suchen. done
*
 * TODO: Blatt 7
 * <p>
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
 */
@Route(value = "produkte", layout = MainLayout.class)
@PageTitle("Projektverwaltung | Projektübersicht")
public class ProductView extends VerticalLayout {

    private final VerticalLayout productCardsLayout;
    private final ProductCard productCard;
    private final ProductService productService;
    private List<Product> productList;

    public ProductView(ProductService productService) {

        this.productService = productService;

        productList = productService.findAllProducts();
        Tabs tabs = new Tabs();

        tabs.setWidth("100%");
        List<CategoryType> categoryList = List.of(CategoryType.values());
        for (CategoryType category : categoryList) {
            tabs.add(new Tab(category.label));
        }
        add(tabs);


        TextField filterText = new TextField();
        filterText.setId("filterText");
        filterText.setValueChangeMode(ValueChangeMode.EAGER);


        productCard = new ProductCard();
        productCardsLayout = productCard.createProductCards(productList);

        filterText.addValueChangeListener(e -> {
            tabs.setSelectedIndex(0);
            productCardsLayout.removeAll();
            productCardsLayout.add(productCard.createProductCards(productService.productFilter(e.getValue())));
        });
        filterText.setPlaceholder("Produktnamen oder Id...");
        filterText.setClearButtonVisible(true);

        tabs.setSelectedIndex(0);
        tabs.addSelectedChangeListener(event ->
                setContent(event.getSelectedTab().getLabel())
        );

        add(filterText);
        add(productCardsLayout);
    }

    public void setContent(String value) {
        System.out.println("Aufruf");

        CategoryType categoryType;

        switch (value) {
            case "Alle Kategorien" -> categoryType = CategoryType.ALLE_KATEGORIEN;
            case "Technik" -> categoryType = CategoryType.TECHNIK;
            case "Computer" -> categoryType = CategoryType.COMPUTER;
            case "Smartphones" -> categoryType = CategoryType.SMARTPHONES;
            case "Konsolen" -> categoryType = CategoryType.KONSOLEN;
            case "Games" -> categoryType = CategoryType.GAMES;
            case "Haushaltsgeräte" -> categoryType = CategoryType.HAUSHALTSGERAETE;
            case "Musikanlagen" -> categoryType = CategoryType.MUSIKANLAGEN;
            case "CD's und Vynil" -> categoryType = CategoryType.CDS_UND_VINYL;
            case "Lautsprecher" -> categoryType = CategoryType.LAUTSPRECHER;
            case "DVD und BlueRay" -> categoryType = CategoryType.DVD_UND_BLUERAY;
            case "Fernseher und Heimkino" -> categoryType = CategoryType.FERNSEHER_UND_HEIMKINO;
            case "Speichermedien" -> categoryType = CategoryType.SPEICHERMEDIEN;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }

        productList.clear();
        productList = productService.findAllByCategory(categoryType);
        System.out.println(productList.size());
        for(Product product : productList){
            System.out.println(product.getName());
        }
        productCardsLayout.removeAll();
        productCardsLayout.add(productCard.createProductCards(productList));

//        switch (value){
//            case "Alle Kategorien":
//                System.out.println("Aufruf");
//                filterText.addValueChangeListener(e -> {
//                    System.out.println("Aufruf");
//                    verticalLayout.removeAll();
//                    verticalLayout.add(productCard.createProductCards(productService.productFilter(e.getValue())));
//                });
//                break;
//            case "Smartphones": //TODO Geht net
//                filterText.addValueChangeListener(e -> {
//                    verticalLayout.removeAll();
//                    verticalLayout.add(productCard.createProductCards(productService.productFilterTest(e.getValue(), CategoryType.SMARTPHONES)));
//                });
//                break;
//
//
//
//        }
    }



}
