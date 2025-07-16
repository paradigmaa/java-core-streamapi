package school.sorokin.javacore.streamapi;

import java.time.LocalDate;
import java.util.Set;

public class Orders {
    Products Products = new Products();
    public  Order order1 = new Order(1L, LocalDate.of(2025, 7, 10), LocalDate.of(2025, 7, 11),
            "Доставлен", Set.of(Products.product1, Products.product5, Products.product10));
    public  Order order2 = new Order(2L, LocalDate.of(2025, 7, 15), LocalDate.of(2025, 7, 16),
            "Доставлен", Set.of(Products.product2, Products.product8, Products.product15));
    public  Order order3 = new Order(3L, LocalDate.of(2025, 7, 20), LocalDate.of(2025, 7, 21),
            "Доставлен", Set.of(Products.product3, Products.product12, Products.product20));
    public Order order4 = new Order(4L, LocalDate.of(2025, 7, 25), null,
            "В обработке", Set.of(Products.product4, Products.product9));
    public Order order5 = new Order(5L, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 2),
            "Доставлен", Set.of(Products.product7, Products.product14, Products.product18));
    public Order order6 = new Order(6L, LocalDate.of(2025, 7, 5), LocalDate.of(2025, 7, 6),
            "Доставлен", Set.of(Products.product3, Products.product6, Products.product11));
    public Order order7 = new Order(7L, LocalDate.of(2025, 7, 12), LocalDate.of(2025, 7, 13),
            "Доставлен", Set.of(Products.product5, Products.product9, Products.product16));
    public Order order8 = new Order(8L, LocalDate.of(2025, 7, 18), null,
            "Отменен", Set.of(Products.product2, Products.product7));
    public Order order9 = new Order(9L, LocalDate.of(2025, 7, 22), LocalDate.of(2025, 7, 23),
            "Доставлен", Set.of(Products.product4, Products.product10, Products.product17));
    public Order order10 = new Order(10L, LocalDate.of(2025, 7, 28), LocalDate.of(2025, 7, 29),
            "Доставлен", Set.of(Products.product1, Products.product8, Products.product13));
    public Order order11 = new Order(11L, LocalDate.of(2025, 8, 2), LocalDate.of(2025, 8, 3),
            "Доставлен", Set.of(Products.product6, Products.product12, Products.product19));
    public Order order12 = new Order(12L, LocalDate.of(2025, 8, 5), null,
            "В пути", Set.of(Products.product3, Products.product14));
    public Order order13 = new Order(13L, LocalDate.of(2025, 8, 8), LocalDate.of(2025, 8, 9),
            "Доставлен", Set.of(Products.product7, Products.product15, Products.product20));
    public Order order14 = new Order(14L, LocalDate.of(2025, 8, 12), null,
            "Ожидает оплаты", Set.of(Products.product1, Products.product5));
    public Order order15 = new Order(15L, LocalDate.of(2025, 8, 15), LocalDate.of(2025, 8, 16),
            "Доставлен", Set.of(Products.product2, Products.product9, Products.product16));
    public Order order16 = new Order(16L, LocalDate.of(2025, 8, 12), LocalDate.of(2025, 8, 2),
            "Доставлен", Set.of(Products.product4, Products.product8, Products.product17));
    public Order order17 = new Order(17L, LocalDate.of(2025, 8, 12), null,
            "Собирается", Set.of(Products.product10, Products.product13));
    public Order order18 = new Order(18L, LocalDate.of(2025, 8, 12), LocalDate.of(2025, 8, 8),
            "Доставлен", Set.of(Products.product5, Products.product11, Products.product18));
    public Order order19 = new Order(19L, LocalDate.of(2025, 8, 12), LocalDate.of(2025, 8, 11),
            "Доставлен", Set.of(Products.product3, Products.product9, Products.product15));
    public Order order20 = new Order(20L, LocalDate.of(2025, 8, 14), null,
            "Проблема с доставкой", Set.of(Products.product2, Products.product7));
    public Order order21 = new Order(21L, LocalDate.of(2025, 8, 3), LocalDate.of(2025, 8, 4),
            "Доставлен", Set.of(Products.product1, Products.product6, Products.product12));
    public Order order22 = new Order(22L, LocalDate.of(2025, 8, 6), LocalDate.of(2025, 8, 7),
            "Доставлен", Set.of(Products.product4, Products.product10, Products.product19));
    public Order order23 = new Order(23L, LocalDate.of(2025, 8, 9), null,
            "Ожидает подтверждения", Set.of(Products.product8, Products.product14));
    public  Order order24 = new Order(24L, LocalDate.of(2025, 8, 12), LocalDate.of(2025, 8, 13),
            "Доставлен", Set.of(Products.product3, Products.product11, Products.product20));
    public  Order order25 = new Order(25L, LocalDate.of(2025, 8, 15), null,
            "В обработке", Set.of(Products.product5, Products.product9));

}
