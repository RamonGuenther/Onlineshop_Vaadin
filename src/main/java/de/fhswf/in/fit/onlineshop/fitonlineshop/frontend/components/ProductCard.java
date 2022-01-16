package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views.ProductDetailsView;
import org.vaadin.addons.badge.Badge;

import java.util.List;

/**
 * Die Klasse Productcard ist für das Erstellen der einzelnen "Produktkarten"
 * zuständig.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@CssImport("/themes/onlineshop/components/product-card.css")
public class ProductCard {

    private HorizontalLayout badgeLayout;

    public ProductCard() {

    }


    /**
     * Erstellt mithilfe einer Liste von Produkten und der Methode createProductCard,
     * einzelne Karten, die dann zurückgegeben werden.
     *
     * @param productList Liste der Produkte
     * @return VerticalLayout mit allen Produktkarten
     */
    public VerticalLayout createProductCards(List<Product> productList) {
        VerticalLayout verticalLayout = new VerticalLayout();

        if (productList.isEmpty()) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            Label label = new Label("Es konnten leider keine passenden Artikel gefunden werden.");
            label.setId("product-form-layout-label_failure");
            Icon icon = new Icon(VaadinIcon.FROWN_O);
            icon.setId("product-form-layout-icon_failure");
            horizontalLayout.add(label, icon);
            horizontalLayout.setClassName("product-form-layout-failure_layout");
            verticalLayout.add(horizontalLayout);
            return verticalLayout;
        }
        for (Product product : productList) {
            verticalLayout.add(createProductCard(product));
        }
        return verticalLayout;
    }

    /**
     * Erstellt eine Produktkarte.
     *
     * @param product ausgewähltes Produkt
     * @return HorizontalLayout mit einer Produktkarte
     */
    private HorizontalLayout createProductCard(Product product) {

        HorizontalLayout productCardLayout = new HorizontalLayout();
        productCardLayout.addClassName("product-form-layout-product_card");

        VerticalLayout imageLayout = new VerticalLayout();
        imageLayout.setClassName("product-form-layout-image_layout");
        Image image = product.getMainProductImage();
        image.setId("product-form-layout-product_image");
        imageLayout.add(image);

        FormLayout formLayout = new FormLayout();
        formLayout.setId("product-form-layout-form_layout");

        Label productName = new Label(product.getName());
        productName.setId("product-form-layout-product_name");

        Label productPrice = new Label(product.getPrice() + " €");

        Label productDescription = new Label();


        badgeLayout = new HorizontalLayout();
        badgeLayout.setClassName("product-form-layout-badge_layout");

        product.getCategorieEnums().stream().map(categoryType -> new Badge(categoryType.label)).forEach(badge -> {
            badge.setVariant(Badge.BadgeVariant.SUCCESS);
            badgeLayout.add(badge);
        });


        Label productAvailability = new Label("Auf Lager: " + product.getInStock());

        Label productId = new Label("Produkt Id: " + product.getId());

        Button detailsButton = new Button("Details");
        detailsButton.setId("product-form-layout-details_button");
        detailsButton.setIcon(VaadinIcon.SEARCH.create());

        //Route Parameters
        detailsButton.addClickListener(e -> {
            UI.getCurrent().navigate(ProductDetailsView.class, new RouteParameters(
                    new RouteParam("productId", product.getId().toString())));
        });

        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("500px", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.add(productName, productPrice, productDescription, productAvailability, productId, badgeLayout);

        productCardLayout.add(imageLayout, formLayout, detailsButton);

        return productCardLayout;
    }
}
