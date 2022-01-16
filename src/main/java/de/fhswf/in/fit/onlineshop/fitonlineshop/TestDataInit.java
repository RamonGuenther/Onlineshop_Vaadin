package de.fhswf.in.fit.onlineshop.fitonlineshop;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.*;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.CategoryType;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.ImageType;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.enums.OrderState;
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
    private void initTestData() {

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


        Product smartphone1 = new Product("Samsung Galaxy S21 Ultra 5G", 1299.00, 10, "Krasses Handy");
        smartphone1.addCategory(categorySmartphone);
        smartphone1.addCategory(categoryTechnik);
        ProductImage imageSmartphone1_1 = new ProductImage("01_s21ultra.jpeg", ImageType.JPEG);
        imageSmartphone1_1.setData("01_s21ultra.jpeg");
        imageService.saveImage(imageSmartphone1_1);
        smartphone1.addImage(imageSmartphone1_1);
        ProductImage imageSmartphone1_2 = new ProductImage("02_s21ultra.jpeg", ImageType.JPEG);
        imageSmartphone1_2.setData("02_s21ultra.jpeg");
        imageService.saveImage(imageSmartphone1_2);
        smartphone1.addImage(imageSmartphone1_2);
        productService.saveProduct(smartphone1);

        Product smartphone2 = new Product("Iphone 13 ProMax", 1829.00, 8, "1TB purer Spaß");
        smartphone2.addCategory(categorySmartphone);
        smartphone2.addCategory(categoryTechnik);
        ProductImage imageSmartphone2_1 = new ProductImage("03_iphone13promax.png", ImageType.JPEG);
        imageSmartphone2_1.setData("03_iphone13promax.png");
        imageService.saveImage(imageSmartphone2_1);
        smartphone2.addImage(imageSmartphone2_1);
        ProductImage imageSmartphone2_2 = new ProductImage("04_iphone13promax.png", ImageType.JPEG);
        imageSmartphone2_2.setData("04_iphone13promax.png");
        imageService.saveImage(imageSmartphone2_2);
        smartphone2.addImage(imageSmartphone2_2);
        productService.saveProduct(smartphone2);

        Product playstation = new Product("Playstation 5", 879.00, 1, "Standard Edition, 825 GB interner Speicher");
        playstation.addCategory(categoryKonsolen);
        playstation.addCategory(categoryTechnik);
        ProductImage imagePlaystation_1 = new ProductImage("05_playstation5.jpeg", ImageType.JPEG);
        imagePlaystation_1.setData("05_playstation5.jpeg");
        imageService.saveImage(imagePlaystation_1);
        playstation.addImage(imagePlaystation_1);
        ProductImage imagePlaystation_2 = new ProductImage("06_playstation5.jpeg", ImageType.JPEG);
        imagePlaystation_2.setData("06_playstation5.jpeg");
        imageService.saveImage(imagePlaystation_2);
        playstation.addImage(imagePlaystation_2);
        productService.saveProduct(playstation);

        Product game1 = new Product("Resident Evil Village", 49.99, 25, "Für Playstation 5");
        game1.addCategory(categoryGames);
        game1.addCategory(categoryTechnik);
        ProductImage imageGame1 = new ProductImage("07_residentevil.jpeg", ImageType.JPEG);
        imageGame1.setData("07_residentevil.jpeg");
        imageService.saveImage(imageGame1);
        game1.addImage(imageGame1);
        productService.saveProduct(game1);

        Product game2 = new Product("Crusader Kings III", 18.99, 12, "Für PC");
        game2.addCategory(categoryGames);
        game2.addCategory(categoryTechnik);
        ProductImage imageGame2 = new ProductImage("08_crusaderkings.jpeg", ImageType.JPEG);
        imageGame2.setData("08_crusaderkings.jpeg");
        imageService.saveImage(imageGame2);
        game2.addImage(imageGame2);
        productService.saveProduct(game2);

        Product game3 = new Product("Pokemon: Strahlender Diamant", 49.99, 9, "Für Nintendo-Switch");
        game3.addCategory(categoryGames);
        game3.addCategory(categoryTechnik);
        ProductImage imageGame3 = new ProductImage("09_pokemon.jpeg", ImageType.JPEG);
        imageGame3.setData("09_pokemon.jpeg");
        imageService.saveImage(imageGame3);
        game3.addImage(imageGame3);
        productService.saveProduct(game3);

        Product pc1 = new Product("ASUS Zenbook Pro 15,6\"", 1105.00, 5, "ASUS Zenbook Pro 15,6\" FHD i7-10870H 16GB/512GB SSD GTX1650 Win10 UX535LH-BN150T");
        pc1.addCategory(categoryComputer);
        pc1.addCategory(categoryTechnik);
        ProductImage imagePc1_1 = new ProductImage("10_asuszenbook.jpeg", ImageType.JPEG);
        imagePc1_1.setData("10_asuszenbook.jpeg");
        imageService.saveImage(imagePc1_1);
        pc1.addImage(imagePc1_1);
        ProductImage imagePc1_2 = new ProductImage("11_asuszenbook.jpeg", ImageType.JPEG);
        imagePc1_2.setData("11_asuszenbook.jpeg");
        imageService.saveImage(imagePc1_2);
        pc1.addImage(imagePc1_2);
        ProductImage imagePc1_3 = new ProductImage("12_asuszenbook.jpeg", ImageType.JPEG);
        imagePc1_3.setData("12_asuszenbook.jpeg");
        imageService.saveImage(imagePc1_3);
        pc1.addImage(imagePc1_3);
        ProductImage imagePc1_4 = new ProductImage("13_asuszenbook.jpeg", ImageType.JPEG);
        imagePc1_4.setData("13_asuszenbook.jpeg");
        imageService.saveImage(imagePc1_4);
        pc1.addImage(imagePc1_4);
        productService.saveProduct(pc1);

        Product pc2 = new Product("Surface Pro 8", 1538.00, 3, "Microsoft Surface Pro 8, 13 Zoll 2-in-1 Tablet (Intel Core i5, 8GB RAM, 128GB SSD, Win 11 Home) Platin Grau");
        pc2.addCategory(categoryComputer);
        pc2.addCategory(categoryTechnik);
        ProductImage imagePc2_1 = new ProductImage("14_surface8.jpeg", ImageType.JPEG);
        imagePc2_1.setData("14_surface8.jpeg");
        imageService.saveImage(imagePc2_1);
        pc2.addImage(imagePc2_1);
        ProductImage imagePc2_2 = new ProductImage("15_surface8.jpeg", ImageType.JPEG);
        imagePc2_2.setData("15_surface8.jpeg");
        imageService.saveImage(imagePc2_2);
        pc2.addImage(imagePc2_2);
        ProductImage imagePc2_3 = new ProductImage("16_surface8.jpeg", ImageType.JPEG);
        imagePc2_3.setData("16_surface8.jpeg");
        imageService.saveImage(imagePc2_3);
        pc2.addImage(imagePc2_3);
        ProductImage imagePc2_4 = new ProductImage("17_surface8.jpeg", ImageType.JPEG);
        imagePc2_4.setData("17_surface8.jpeg");
        imageService.saveImage(imagePc2_4);
        pc2.addImage(imagePc2_4);
        productService.saveProduct(pc2);

        Product pc3 = new Product("HP Chromebook x2", 499.00, 7, "Mit seinem ultraflachen Format ist das HP Chromebook x2 der ideale Begleiter, wohin es dich auch zieht. Mit Qualcomm Snapdragon 7c 4 GB RAM, 64 GB Speicher");
        pc3.addCategory(categoryComputer);
        pc3.addCategory(categoryTechnik);
        ProductImage imagePc3_1 = new ProductImage("18_chromebook.jpeg", ImageType.JPEG);
        imagePc3_1.setData("18_chromebook.jpeg");
        imageService.saveImage(imagePc3_1);
        pc3.addImage(imagePc3_1);
        ProductImage imagePc3_2 = new ProductImage("19_chromebook.jpeg", ImageType.JPEG);
        imagePc3_2.setData("19_chromebook.jpeg");
        imageService.saveImage(imagePc3_2);
        pc3.addImage(imagePc3_2);
        ProductImage imagePc3_3 = new ProductImage("20_chromebook.jpeg", ImageType.JPEG);
        imagePc3_3.setData("20_chromebook.jpeg");
        imageService.saveImage(imagePc3_3);
        pc3.addImage(imagePc3_3);
        productService.saveProduct(pc3);

        Product tv = new Product("LG signature OLED88Z19LA OLED TV", 29999.00, 1, "LG signature OLED88Z19LA OLED TV (Flat, 88 Zoll / 222 cm, UHD 8K, SMART TV, webOS 6.0 mit LG ThinQ)");
        tv.addCategory(categoryFernseherHeimkino);
        tv.addCategory(categoryTechnik);
        ProductImage imageTv_1 = new ProductImage("21_lgfernseher.png", ImageType.JPEG);
        imageTv_1.setData("21_lgfernseher.png");
        imageService.saveImage(imageTv_1);
        tv.addImage(imageTv_1);
        ProductImage imageTv_2 = new ProductImage("22_lgfernseher.png", ImageType.JPEG);
        imageTv_2.setData("22_lgfernseher.png");
        imageService.saveImage(imageTv_2);
        tv.addImage(imageTv_2);
        productService.saveProduct(tv);

        System.out.println("Produkte hinzugefügt und in der Datenbank gespeichert");


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

        User user2 = new User("user2");
        userService.saveUser(user2);

        //Ein leerer "Warenkorb" für User2
        Orders order3 = new Orders();
        ordersService.saveOrder(order3);
        user2.addOrder(order3);
        userService.saveUser(user2);


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

        //Damit es als Bestellung gekennzeichnet wird
        order1.setOrderState(OrderState.BESTELLT);

        //Ein leerer "Warenkorb"
        Orders order2 = new Orders();
        ordersService.saveOrder(order1);
        ordersService.saveOrder(order2);

        System.out.println("Beispielbestellung angelegt");

        user.addOrder(order1);
        user.addOrder(order2);
        userService.saveUser(user);

        System.out.println("Testuser Bestellung zugeordnet und User aktualisiert");

    }
}
