package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.OrderedProduct;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Orders;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.User;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.primaryKeys.OrderedProductKey;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.OrderedProductService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.OrdersService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.ProductService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.UserService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.NotificationError;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.NotificationSuccess;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.addons.badge.Badge;

import java.util.List;

@Route(value = "produktDetails/productId/:productId", layout = MainLayout.class)
@PageTitle("R & I | Produkt Details")
@CssImport("/themes/onlineshop/views/product-details-view.css")
public class ProductDetailsView extends HorizontalLayout implements BeforeEnterObserver, AfterNavigationObserver {

    private Product product;
    private Long id;

    private Image image;
    private VerticalLayout imageLayout;

    private final ProductService productService;
    private final OrderedProductService orderedProductService;
    private final UserService userService;
    private final OrdersService ordersService;

    private List<Image> imageList;
    private Label productAvailability;

    public ProductDetailsView(ProductService productService, OrderedProductService orderedProductService, UserService userService, OrdersService ordersService) {
        this.productService = productService;
        this.orderedProductService = orderedProductService;
        this.userService = userService;
        this.ordersService = ordersService;
    }

    private void createView() {
        setClassName("product-details-view-product_card_layout");

        H1 productName = new H1(product.getName());
        productName.setId("product-details-view-product_name");

        HorizontalLayout badgeLayout = new HorizontalLayout();
        badgeLayout.setClassName("product-details-view-badge_layout");

        product.getCategorieEnums().stream().map(categoryType -> new Badge(categoryType.label)).forEach(badge -> {
            badge.setVariant(Badge.BadgeVariant.SUCCESS);
            badgeLayout.add(badge);
        });

        H2 projectDescriptionTitle = new H2("Beschreibung");
        projectDescriptionTitle.setClassName("product-details-view-details_title");
        Label productDescription = new Label(product.getDescription());
        productDescription.setClassName("product-details-view-details");

        H2 productPriceTitle = new H2("Preis");
        productPriceTitle.setClassName("product-details-view-details_title");
        Label productPrice = new Label(product.getPrice().toString() + " €");
        productPrice.setClassName("product-details-view-details");

        H2 productAvailabilityTitle = new H2("Auf Lager");
        productAvailabilityTitle.setClassName("product-details-view-details_title");
        productAvailability = new Label(Integer.toString(product.getInStock()));
        productAvailability.setClassName("product-details-view-details");

        H2 productIdTitle = new H2("Bestellnummer");
        productIdTitle.setClassName("product-details-view-details_title");
        Label productId = new Label(product.getId().toString());
        productId.setClassName("product-details-view-details");

        Button addToCartButton = new Button(VaadinIcon.CART_O.create());
        addToCartButton.setId("product-details-view-addToCart_button");
        addToCartButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout productDetailsLayout = new VerticalLayout(
                productName, badgeLayout,
                projectDescriptionTitle, productDescription,
                productPriceTitle, productPrice,
                productAvailabilityTitle, productAvailability,
                productIdTitle, productId);
        productDetailsLayout.setClassName("product-form-layout-product_details_layout");

        imageList = product.getVaadinImageList();
        image = product.getMainProductImage();
        image.setId("product-details-view-product_image");

        HorizontalLayout imageButtonsLayout = new HorizontalLayout();
        imageButtonsLayout.setClassName("product-details-view-image_buttons_layout");
        Button buttonLastPicture = new Button(VaadinIcon.ANGLE_LEFT.create());
        Button buttonNextPicture = new Button(VaadinIcon.ANGLE_RIGHT.create());
        imageButtonsLayout.add(buttonLastPicture, buttonNextPicture);

        imageLayout = new VerticalLayout();
        imageLayout.setClassName("product-details-view-image_layout");
        imageLayout.add(image, imageButtonsLayout);

        buttonLastPicture.addClickListener(e->{

        });

        buttonNextPicture.addClickListener(e->{


        });

        addToCartButton.addClickListener(event->{
            try {

                if(product.getInStock() == 0){
                    NotificationError.show("Leider ist der Artikel " + product.getName() + " nicht mehr vorrätig.");
                    return;
                }

                User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
                Orders shoppingCart = user.getShoppingCart();

                List<OrderedProduct> orderedProduct1 = orderedProductService.getOrderedProductsByOrder(shoppingCart);

                for (OrderedProduct op : orderedProduct1) {
                    if (op.getId().getProduct().getName().equals(product.getName())) {
                        product.setInStock(product.getInStock() - 1);
                        productService.saveProduct(product);
                        op.setAmount(op.getAmount() + 1);
                        orderedProductService.saveOrderedProduct(op);
                        productAvailability.setText("Auf Lager: " + product.getInStock());
                        NotificationSuccess.show("Das Produkt " + product.getName() + " wurde dem Warenkorb hinzugefügt");
                        return;
                    }
                }

                OrderedProduct orderedProduct = new OrderedProduct(new OrderedProductKey(product, shoppingCart), 1);

                orderedProductService.saveOrderedProduct(orderedProduct);

                shoppingCart.addOrderedProduct(orderedProduct);

                ordersService.saveOrder(shoppingCart);

                product.setInStock(product.getInStock() - 1);
                productService.saveProduct(product);
                productAvailability.setText("Auf Lager: " + product.getInStock());

                NotificationSuccess.show("Das Produkt " + product.getName() + " wurde dem Warenkorb hinzugefügt");
            }
            catch(Exception e){
                NotificationError.show(e.getMessage());
            }
        });

        add(imageLayout, productDetailsLayout, addToCartButton);
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
