package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.CategoryType;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.ProductService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.ProductCard;

import java.util.List;

/**
 * Die Klasse ProductView listet alle eingestellten Produkte für den Nutzer
 * auf.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@Route(value = "produkte", layout = MainLayout.class)
@PageTitle("R & I | Produktübersicht")
@CssImport("/themes/onlineshop/views/product-view.css")
public class ProductView extends VerticalLayout {

    private final VerticalLayout productCardsLayout;
    private final ProductCard productCard;
    private final ProductService productService;
    private List<Product> productList;

    public ProductView(ProductService productService) {

        H1 productViewTitle = new H1("Produktübersicht");
        productViewTitle.setId("product-view-title");

        this.productService = productService;

        productList = productService.findAllProducts();
        Tabs tabs = new Tabs();
        tabs.setId("product-view-tabs");

        List<CategoryType> categoryList = List.of(CategoryType.values());
        for (CategoryType category : categoryList) {
            tabs.add(new Tab(category.label));
        }

        TextField filterText = new TextField();
        filterText.setId("product-view-filter_text");
        filterText.setValueChangeMode(ValueChangeMode.EAGER);


        productCard = new ProductCard();
        productCardsLayout = productCard.createProductCards(productList);

        filterText.addValueChangeListener(e -> {
            tabs.setSelectedIndex(0);
            productCardsLayout.removeAll();
            productCardsLayout.add(productCard.createProductCards(productService.productFilter(e.getValue())));
        });
        filterText.setPlaceholder("Name oder Artikelnummer...");
        filterText.setPrefixComponent(VaadinIcon.SEARCH.create());
        filterText.setClearButtonVisible(true);

        tabs.setSelectedIndex(0);
        tabs.addSelectedChangeListener(event ->
                setContent(event.getSelectedTab().getLabel())
        );

        add(tabs,productViewTitle,filterText,productCardsLayout);
    }

    /**
     * Beim Klick auf einen Tab werden nur noch die Artikel, bei denen die Kategorie
     * zutrifft angezeigt.
     *
     * @param value Kategoriename
     */
    public void setContent(String value) {
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
        productCardsLayout.removeAll();
        productCardsLayout.add(productCard.createProductCards(productList));

    }

}
