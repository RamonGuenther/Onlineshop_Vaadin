package de.fhswf.in.fit.onlineshop.fitonlineshop;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.*;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.CategoryType;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.primaryKeys.OrderedProductKey;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Die Klasse TestDataInit erstellt die Beispieldaten für die Anwendung
 * und speichert diese in der Datenbank.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */
@Component
public class TestDataInit {

    @Autowired
    private AddressService addressService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private OrderedProductService orderedProductService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @PostConstruct
    private void initTestData(){
        Category categoryTechnik = new Category(CategoryType.TECHNIK, "In der Kategorie Technik ist alles mögliche an Elektrogeräten zu finden.");
        Category categoryComputer = new Category(CategoryType.COMPUTER, "Stand-Pc, Laptops, Notebooks, Chromebooks und vieles mehr. Außerdem alles an Teilen, was du brauchst um die deinen eigenen PC zu bauen :)");
        Category categorySmartphone = new Category(CategoryType.SMARTPHONES, "Smartphones verschiedenster Marken.");
        Category categoryKonsolen = new Category(CategoryType.KONSOLEN, "Playstation, XBox, Nintendo etc.");
        Category categoryGames = new Category(CategoryType.GAMES, "Spiele für alle Plattformen");
        Category categoryHaushaltsgeraete = new Category(CategoryType.HAUSHALTSGERAETE, "Alle möglichen Arten von Haushaltsgeräten, wie diverse Küchengeräte, Staubsauger etc.");
        Category categoryMusikanlagen = new Category(CategoryType.MUSIKANLAGEN, "CD-Spieler, Plattenspieler, Radios etc.");
        Category categoryCDsUndVinyl = new Category(CategoryType.CDS_UND_VINYL, "Alles an Musik für dich :)");
        Category categoryLautsprecher = new Category(CategoryType.LAUTSPRECHER, "Irgendwo muss die geile Mukke ja rauskommen ;D");
        Category categoryDVDUndBlueRay = new Category(CategoryType.DVD_UND_BLUERAY, "Filme so weit das Auge reicht");
        Category categoryFernseherHeimkino = new Category(CategoryType.FERNSEHER_UND_HEIMKINO, "Filme gucken wie im Kino...und das zuhause");
        Category categorySpeichermedien = new Category(CategoryType.SPEICHERMEDIEN, "Sichere deine Daten zuverlässig!");

        System.out.println("Kategorien wurden angelegt");

        Product smartphone1 = new Product("Samsung Galaxy S21 Ultra 5G", 1299.00, 10, "Krasses Handy");
        smartphone1.addCategory(categorySmartphone);
        smartphone1.addCategory(categoryTechnik);
        productService.saveProduct(smartphone1);
        categorySmartphone.addProduct(smartphone1);

        Product smartphone2 = new Product("Iphone 13 ProMax", 1829.00, 8, "1TB purer Spaß");
        smartphone2.addCategory(categorySmartphone);
        smartphone2.addCategory(categoryTechnik);
        productService.saveProduct(smartphone2);
        categorySmartphone.addProduct(smartphone2);

        Product playstation = new Product("Playstation 5", 879.00, 1, "Standard Edition, 825 GB interner Speicher");
        playstation.addCategory(categoryKonsolen);
        playstation.addCategory(categoryTechnik);
        productService.saveProduct(playstation);
        categoryKonsolen.addProduct(playstation);

        Product game1 = new Product("Resident Evil Village", 49.99, 25, "Für Playstation 5");
        game1.addCategory(categoryGames);
        game1.addCategory(categoryTechnik);
        productService.saveProduct(game1);
        categoryKonsolen.addProduct(game1);

        Product game2 = new Product("Crusader Kings III", 18.99, 12, "Für PC");
        game2.addCategory(categoryGames);
        game2.addCategory(categoryTechnik);
        productService.saveProduct(game2);
        categoryKonsolen.addProduct(game2);

        Product game3 = new Product("Pokemon: Strahlender Diamant", 49.99, 9, "Für Nintendo-Switch");
        game3.addCategory(categoryGames);
        game3.addCategory(categoryTechnik);
        productService.saveProduct(game3);
        categoryKonsolen.addProduct(game3);

        Product pc1 = new Product("ASUS Zenbook Pro 15,6\"", 1105.00 , 5, "ASUS Zenbook Pro 15,6\" FHD i7-10870H 16GB/512GB SSD GTX1650 Win10 UX535LH-BN150T");
        pc1.addCategory(categoryComputer);
        pc1.addCategory(categoryTechnik);
        productService.saveProduct(pc1);
        categoryKonsolen.addProduct(pc1);

        Product pc2 = new Product("Surface Pro 8", 1538.00 , 3, "Microsoft Surface Pro 8, 13 Zoll 2-in-1 Tablet (Intel Core i5, 8GB RAM, 128GB SSD, Win 11 Home) Platin Grau");
        pc2.addCategory(categoryComputer);
        pc2.addCategory(categoryTechnik);
        productService.saveProduct(pc2);
        categoryKonsolen.addProduct(pc2);

        Product pc3 = new Product("HP Chromebook x2", 499.00 , 7, "Mit seinem ultraflachen Format ist das HP Chromebook x2 der ideale Begleiter, wohin es dich auch zieht. Mit Qualcomm Snapdragon 7c 4 GB RAM, 64 GB Speicher");
        pc3.addCategory(categoryComputer);
        pc3.addCategory(categoryTechnik);
        productService.saveProduct(pc3);
        categoryKonsolen.addProduct(pc3);

        Product tv = new Product("LG signature OLED88Z19LA OLED TV", 29999.00 , 1, "LG signature OLED88Z19LA OLED TV (Flat, 88 Zoll / 222 cm, UHD 8K, SMART TV, webOS 6.0 mit LG ThinQ)");
        tv.addCategory(categoryFernseherHeimkino);
        tv.addCategory(categoryTechnik);
        productService.saveProduct(tv);
        categoryKonsolen.addProduct(tv);

        System.out.println("Produkte hinzugefügt und in der Datenbank gespeichert");

        categoryService.saveCategory(categoryTechnik);
        categoryService.saveCategory(categoryComputer);
        categoryService.saveCategory(categorySmartphone);
        categoryService.saveCategory(categoryKonsolen);
        categoryService.saveCategory(categoryGames);
        categoryService.saveCategory(categoryHaushaltsgeraete);
        categoryService.saveCategory(categoryMusikanlagen);
        categoryService.saveCategory(categoryCDsUndVinyl);
        categoryService.saveCategory(categoryLautsprecher);
        categoryService.saveCategory(categoryDVDUndBlueRay);
        categoryService.saveCategory(categoryFernseherHeimkino);
        categoryService.saveCategory(categorySpeichermedien);

        System.out.println("Kategorien wurden in der Datenbank gespeichert");

        User user = new User("user");
        Address address1 = new Address(
                "Mustermann",
                "Max",
                "Apfelstraße 12",
                "Birnenhausen",
                "12345",
                "Deutschland",
                "01234 123456789",
                "mustermann.max@mail.com"
        );
        addressService.saveAddress(address1);
        Address address2 = new Address(
                "Mustermann",
                "Matthilda",
                "Birnengasse 33",
                "Birnenhausen",
                "12345",
                "Deutschland",
                "01234 987654321",
                "mustermann.matthilda@nachrichten.de"
        );
        addressService.saveAddress(address2);
        user.addAddress(address1);
        user.addAddress(address2);
        userService.saveUser(user);

        User admin = new User("admin");
        userService.saveUser(admin);

        System.out.println("Testuser wurden in der Datenbank gespeichert");

        Orders order1 = new Orders(
                userService.findByUsername("user").getAdresses().stream().toList().get(0),
                userService.findByUsername("user").getAdresses().stream().toList().get(1),
                "Erste Bestellung :)");

        ordersService.saveOrder(order1);

        OrderedProduct product1 = new OrderedProduct(new OrderedProductKey(smartphone1, order1), 1);
        orderedProductService.saveOrderedProduct(product1);
        OrderedProduct product2 = new OrderedProduct(new OrderedProductKey(playstation, order1), 1);
        orderedProductService.saveOrderedProduct(product2);
        OrderedProduct product3 = new OrderedProduct(new OrderedProductKey(game3, order1), 1);
        orderedProductService.saveOrderedProduct(product3);

        order1.addOrderedProduct(product1);
        order1.addOrderedProduct(product2);
        order1.addOrderedProduct(product3);

        ordersService.saveOrder(order1);

        System.out.println("Beispielbestellung angelegt");

        user.addOrder(order1);
        userService.saveUser(user);

        System.out.println("Testuser Bestellung zugeordnet und User aktualisiert");
    }
}
