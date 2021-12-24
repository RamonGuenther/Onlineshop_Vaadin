package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.CategoryType;
import org.vaadin.addons.badge.Badge;

import java.util.ArrayList;
import java.util.List;

@CssImport("/themes/onlineshop/components/product-form-layout.css")
public class ProductFormlayout {

    public ProductFormlayout() {


    }


    public VerticalLayout createProductCards(List<Product> productList){
        VerticalLayout verticalLayout = new VerticalLayout();

        if(productList.isEmpty()){
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            Label label = new Label("Es konnten leider keine passenden Artikel gefunden werden.");
            label.setId("product-form-layout-label_failure");
            Icon icon = new Icon(VaadinIcon.FROWN_O);
            icon.setId("product-form-layout-icon_failure");
            horizontalLayout.add(label,icon);
            horizontalLayout.setClassName("product-form-layout-failure_layout");
            verticalLayout.add(horizontalLayout);
            return verticalLayout;
        }
        for (Product product : productList) {
           verticalLayout.add(createProductCard(product));
        }
        return verticalLayout;
    }

    private HorizontalLayout createProductCard(Product product){

        HorizontalLayout productCard = new HorizontalLayout();
        productCard.addClassName("product-form-layout-product_card");

        List<Image> imageList = product.getVaadinImageList();
//        System.out.println(imageList.size());
//        for(Image image : imageList) {
//            horizontalLayout.add()
//        }

        Image image = imageList.get(0);
        image.setId("product-form-layout-product_image");

        FormLayout formLayout = new FormLayout();
        formLayout.setId("product-form-layout-form_layout");

        Label productName = new Label(product.getName());
        productName.setId("product-form-layout-product_name");

        Label productPrice= new Label(product.getPrice() + " €");

        HorizontalLayout badgeLayout = new HorizontalLayout();
        badgeLayout.setClassName("product-form-layout-badge_layout");
        for(CategoryType categoryType : product.getCategorieEnums()){
            System.out.println(categoryType.label);
            Badge badge = new Badge(categoryType.label);
            badge.setVariant(Badge.BadgeVariant.SUCCESS);
            badgeLayout.add(badge);
        }

        Label productAvailability = new Label("Auf Lager: " + product.getInStock());

        Label productId = new Label("Produkt Id: " + product.getId());

        Button detailsButton = new Button("Details");
        detailsButton.setId("product-form-layout-details_button");
        detailsButton.setIcon(VaadinIcon.SEARCH.create());

        detailsButton.addClickListener(e ->{
            test test = new test(product);
        });

        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("500px", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.add(productName, productPrice, productAvailability, productId, badgeLayout);

        productCard.add(image, formLayout, detailsButton);

        return productCard;
    }
}
