package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.OrderedProductService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.ProductService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.ProductCard;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views.MainLayout;

import java.util.List;


@Route(value = "produktDetails/productId/:productId", layout = MainLayout.class)
@PageTitle("R & I | Produkt Details")
@CssImport("/themes/onlineshop/views/product-details-view.css")
public class ProductDetailsView extends VerticalLayout implements BeforeEnterObserver, AfterNavigationObserver {

    private Product product;
    private Long id;

    private final ProductService productService;
    private final OrderedProductService orderedProductService;

    private List<Image> imageList;


    public ProductDetailsView(ProductService productService, OrderedProductService orderedProductService) {
        this.productService = productService;
        this.orderedProductService = orderedProductService;
    }

    private void createView() {
        ProductCard productCard = new ProductCard();
        HorizontalLayout horizontalLayout = productCard.createProductCard(product);

        productCard.getProductDescription().setText(product.getDescription());
        productCard.getProductCardLayout().remove(productCard.getDetailsButton());

        Button addToCartButton = new Button("In den Einkaufswagen");
        addToCartButton.setId("product-details-view-addToCart_button");
        addToCartButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        productCard.getProductCardLayout().add(addToCartButton);


        imageList = product.getVaadinImageList();
        HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        horizontalLayout1.setClassName("apfel");
        Button button = new Button(VaadinIcon.ANGLE_LEFT.create());
        Button button1 = new Button(VaadinIcon.ANGLE_RIGHT.create());

        horizontalLayout1.add(button, button1);
        productCard.getImageLayout().add(horizontalLayout1);

        productCard.getProductCardLayout().setClassName("product-details-view-product_card_layout");
        productCard.getFormLayout().setClassName("product-details-view-product_formlayout");

        productCard.getImage().setId("product-details-view-product_image");
        productCard.getProductName().setId("product-details-view-product_name");
        productCard.getProductPrice().setId("product-details-view-product_price");
        productCard.getProductDescription().setId("product-details-view-product_description");
        productCard.getProductAvailability().setId("product-details-view-product_availability");
        productCard.getProductId().setId("product-details-view-product_id");
        productCard.getBadgeLayout().setClassName("product-details-view-product_badge_layout");


        button.addClickListener(e->{

        });

        button1.addClickListener(e->{


        });

        addToCartButton.addClickListener(e->{

        });
        add(horizontalLayout);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getRouteParameters().get("productId").isPresent()) {
            id = Long.parseLong(beforeEnterEvent.getRouteParameters().get("productId").get());
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        product = productService.getProductById(id);
        createView();
    }

    private Image getNext(Image image) {
        int idx = imageList.indexOf(image);
        System.out.println(idx);
        if (idx < 0 || idx+1 == imageList.size()){
            return null;
        }
        return imageList.get(idx + 1);
    }

    private Image getPrevious(Image image) {
        int idx = imageList.indexOf(image);
        if (idx <= 0) {
            return null;
        }
        return imageList.get(idx - 1);
    }
}
