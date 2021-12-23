package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;

@CssImport("/themes/onlineshop/components/product-form-layout.css")
public class ProductFormlayout extends FormLayout {

    public ProductFormlayout(Product product) {
        setId("test");

        Label productName = new Label(product.getName());
        Label productDescription = new Label(product.getDescription());


       setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("500px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        add(productName, productDescription);
    }
}
