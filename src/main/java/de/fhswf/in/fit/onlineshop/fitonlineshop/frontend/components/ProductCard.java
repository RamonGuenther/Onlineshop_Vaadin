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

@CssImport("/themes/onlineshop/components/product-card.css")
public class ProductCard {

    private HorizontalLayout productCardLayout;
    private Image image;
    private FormLayout formLayout;
    private Label productName;
    private Label productPrice;
    private Label productDescription;
    private HorizontalLayout badgeLayout;
    private Label productAvailability;
    private Label productId;
    private Button detailsButton;
    private VerticalLayout imageLayout;

    public ProductCard() {

    }


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

    public HorizontalLayout createProductCard(Product product) {

        productCardLayout = new HorizontalLayout();
        productCardLayout.addClassName("product-form-layout-product_card");

        imageLayout = new VerticalLayout();
        imageLayout.setClassName("product-form-layout-image_layout");
        image = product.getMainProductImage();
        image.setId("product-form-layout-product_image");
        imageLayout.add(image);

        formLayout = new FormLayout();
        formLayout.setId("product-form-layout-form_layout");

        productName = new Label(product.getName());
        productName.setId("product-form-layout-product_name");

        productPrice = new Label(product.getPrice() + " €");

        productDescription = new Label();


        badgeLayout = new HorizontalLayout();
        badgeLayout.setClassName("product-form-layout-badge_layout");

        product.getCategorieEnums().stream().map(categoryType -> new Badge(categoryType.label)).forEach(badge -> {
            badge.setVariant(Badge.BadgeVariant.SUCCESS);
            badgeLayout.add(badge);
        });


        productAvailability = new Label("Auf Lager: " + product.getInStock());

        productId = new Label("Produkt Id: " + product.getId());

        detailsButton = new Button("Details");
        detailsButton.setId("product-form-layout-details_button");
        detailsButton.setIcon(VaadinIcon.SEARCH.create());

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


    public VerticalLayout getImageLayout() {
        return imageLayout;
    }

    public HorizontalLayout getProductCardLayout() {
        return productCardLayout;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public FormLayout getFormLayout() {
        return formLayout;
    }

    public Label getProductName() {
        return productName;
    }

    public Label getProductPrice() {
        return productPrice;
    }

    public Label getProductDescription() {
        return productDescription;
    }

    public HorizontalLayout getBadgeLayout() {
        return badgeLayout;
    }

    public Label getProductAvailability() {
        return productAvailability;
    }

    public Label getProductId() {
        return productId;
    }

    public Button getDetailsButton() {
        return detailsButton;
    }
}
