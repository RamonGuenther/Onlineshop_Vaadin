package de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Product;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.ProductService;
import de.fhswf.in.fit.onlineshop.fitonlineshop.frontend.components.ProductFormlayout;

import java.util.List;

@Route(value = "produkte", layout = MainLayout.class)
@PageTitle("Projektverwaltung | Projektübersicht")
public class ProductView extends VerticalLayout {

    public ProductView(ProductService productService){

        //TODO: FIltern nicht vergessen bei tollen Ideen, Weniger infos bei Detailansicht

        Grid<Product> productGrid = new Grid<>();
        List<Product> productList = productService.findAllProducts();

//        for(Product product : productList){
//            ProductFormlayout productFormlayout = new ProductFormlayout(product);
//            add(productFormlayout);
//        }
        productGrid.addColumn(Product::getId).setHeader("Produkt Id");
        productGrid.addColumn(Product::getName).setHeader("Produktname");
        productGrid.addColumn(Product::getPrice).setHeader("Preis");
        productGrid.addColumn(Product::getMainCategory).setHeader("Hauptkategorie");
        productGrid.addColumn(Product::getDescription).setHeader("Produktbeschreibung");
        productGrid.setItems(productList);
        productGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        productGrid.getColumns().get(0).setFooter("Anzahl: " + productList.size());
        add(productGrid);

    }
}
