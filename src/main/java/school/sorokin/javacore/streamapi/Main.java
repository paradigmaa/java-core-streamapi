import school.sorokin.javacore.streamapi.Customer;
import school.sorokin.javacore.streamapi.Order;
import school.sorokin.javacore.streamapi.Product;
import school.sorokin.javacore.streamapi.Orders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;


public static void main(String[] args) {
    Orders Orders = new Orders();
    Customer customer1 = new Customer(1L, "Evgeny", 1L, Set.of(Orders.order1,
            Orders.order25, Orders.order2, Orders.order24, Orders.order3));
    Customer customer2 = new Customer(2L, "Alex", 2L, Set.of(Orders.order4,
            Orders.order23, Orders.order5, Orders.order22, Orders.order6));
    Customer customer3 = new Customer(3L, "Bob", 2L, Set.of(Orders.order7,
            Orders.order21, Orders.order8, Orders.order20, Orders.order9));
    Customer customer4 = new Customer(4L, "Milli", 3L, Set.of(Orders.order10,
            Orders.order19, Orders.order11, Orders.order18, Orders.order12));
    Customer customer5 = new Customer(5L, "Anna", 2L, Set.of(Orders.order13,
            Orders.order17, Orders.order14, Orders.order16, Orders.order15));

    //1. Здесь мы получили список из продуктов, цена которых равна 150
    List<Customer> customerList = List.of(customer1, customer2, customer3, customer4, customer5);
    List<Product> price150 = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .flatMap(order -> order.getProducts().stream())
            .filter(n -> n.getCategory().equals("fruits") &&
                    n.getPrice().compareTo(new BigDecimal("150")) == 0)
            .collect(Collectors.toList());

    //2. Здесь мы получили список заказов, с продуктами из категории "vegetables"
    List<Order> orderVegetables = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .filter(order -> order.getProducts().stream()
                    .anyMatch(product -> product.getCategory().equalsIgnoreCase("vegetables")))
            .collect(Collectors.toList());


    //3. Здесь мы получаем список продуктов из категории meat, применяем скидку 10% и получаем сумму всех продуктов
    Optional<BigDecimal> result = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .flatMap(order -> order.getProducts().stream())
            .filter(meet -> meet.getCategory().equalsIgnoreCase("meat"))
            .map(meat -> meat.getPrice().multiply(new BigDecimal(0.9)))
            .reduce(BigDecimal::add);

    //4. Здесь получаем список продуктов, заказанных клиентом второго уровня между 01-июля-2025 и 01-августа-2025.
    List<Order> list = customerList.stream()
            .filter(customer -> customer.getLevel() == 2)
            .flatMap(order -> order.getOrderds().stream())
            .filter(time -> !time.getOrderDate().isBefore(LocalDate.of(2025, 7, 1))
                    && !time.getOrderDate().isAfter(LocalDate.of(2025, 8, 1)))
            .collect(Collectors.toList());

    //5. Получите топ 2 самые дешевые продукты из категории "fruits".
    List<Product> top2 = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .flatMap(order -> order.getProducts().stream())
            .filter(products -> products.getCategory().equalsIgnoreCase("fruits"))
            .sorted(Comparator.comparing(Product::getPrice))
            .limit(2)
            .collect(Collectors.toList());
    /*  System.out.println(top2);*/

    //6. Получите 3 самых последних сделанных заказа.
    customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .sorted(Comparator.comparing(Order::getOrderDate).reversed())
            .limit(3);
    /*  .forEach(System.out::println);*/

    //7. Получите список заказов, сделанных 12-августа-2025, выведите id заказов в консоль и затем верните
    //список их продуктов.
    List<Product> prod = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2025, 8, 12)))
            .flatMap(order -> order.getProducts().stream())
            .collect(Collectors.toList());
    //System.out.println(prod);

    //8. Рассчитайте общую сумму всех заказов, сделанных в июле 2025.
    YearMonth yearMonth = YearMonth.of(2025, 7);
    Optional<BigDecimal> sum = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .filter(ordersTime ->
                    !ordersTime.getOrderDate().isBefore(yearMonth.atDay(1))
                            && !ordersTime.getOrderDate().isAfter(yearMonth.atEndOfMonth()))
            .flatMap(order -> order.getProducts().stream())
            .map(Product::getPrice)
            .reduce(BigDecimal::add);
    // System.out.println(sum.get());


    // 9. Рассчитайте средний платеж по заказам, сделанным 12-августа-2025 года.
    Optional<BigDecimal> sumprice = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .filter(orderTime -> orderTime.getOrderDate().isEqual(LocalDate.of(2025, 8, 12)))
            .flatMap(order -> order.getProducts().stream())
            .map(Product::getPrice)
            .reduce(BigDecimal::add);
    long count = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2025, 8, 12)))
            .mapToLong(products -> products.getProducts().size())
            .sum();
    if (sumprice.isPresent()) {
        BigDecimal price = sumprice.get().divide(new BigDecimal(count));
        //System.out.println(price);
    } else {
        //System.out.println("Заказов нет");
    }
    // Вариант два, выполнение с помощью хитрости массива в лямбде
    BigDecimal[] array = {BigDecimal.ZERO};
    int[] intcount = {0};
    customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2025, 8, 12)))
            .flatMap(products -> products.getProducts().stream())
            .forEach(product -> {
                array[0] = array[0].add(product.getPrice());
                intcount[0]++;
            });
       /* if (count > 0) {
            System.out.println(array[0].divide(new BigDecimal(count)));
        }*/
    //Ещё один вариант через Коллекторы
    BigDecimal temp = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .filter(orderTime -> orderTime.getOrderDate().isEqual(LocalDate.of(2025, 8, 12)))
            .flatMap(Order -> Order.getProducts().stream())
            .collect(Collectors.teeing(
                    Collectors.reducing(BigDecimal.ZERO, Product::getPrice, BigDecimal::add),
                    Collectors.counting(),
                    (sumtotal, total) -> total == 0 ? null : sumtotal.divide(new BigDecimal(total))
            ));

       /* if (temp != null) {
            System.out.println(temp);
        }*/

    // 10. Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех
    //продуктов категории "dairy".
    DoubleSummaryStatistics statictic = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .flatMap(order -> order.getProducts().stream())
            .filter(product -> product.getCategory().equalsIgnoreCase("dairy"))
            .mapToDouble(getPrice -> getPrice.getPrice().doubleValue())
            .summaryStatistics();
  /*      System.out.println("Сумма=" + statictic.getSum() + ", " +
        "Среднее=" + statictic.getAverage() + ", " + "Максимум=" + statictic.getMax() + ", "
        + "Минимум=" + statictic.getMin() + ", " + "Количество=" + statictic.getCount());*/

    //  11. Получите данные Map<Long, Integer> → key - id заказа, value - кол-во товаров в заказе
    Map<Long, Integer> countMap = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .collect(Collectors.toMap(
                    key -> key.getId(),
                    value -> value.getProducts().size()
            ));
    /*     System.out.println(countMap);*/


    //  12. Создайте Map<Customer, List<Order>> → key - покупатель, value - список его заказов
    Map<Customer, List<Order>> mapOrder = customerList.stream()
            .collect(Collectors.toMap(
                            customer -> customer,
                            value -> new ArrayList<>(value.getOrderds())
                    )
            );
    /* System.out.println(mapOrder);*/
    // 13. Создайте Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа.
    Map<Order, Double> mapDouble = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .collect(Collectors.toMap(
                    order -> order,
                    value -> value.getProducts().stream()
                            .mapToDouble(proucts -> proucts.getPrice().doubleValue())
                            .sum()
            ));
    /* System.out.println(mapDouble);*/
    //14. Получите Map<String, List<String>> → key - категория, value - список названий товаров в категории
    Map<String, List<String>> categoryName = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .flatMap(order -> order.getProducts().stream())
            .collect(Collectors.toMap(
                    key -> key.getCategory(),
                    value -> new ArrayList<>(List.of(value.getName())),
                    (ex, repl) -> {
                        ex.addAll(repl);
                        return ex;
                    }
            ));

    //Второй вариант для закрепления работы с дубликатами в Мапе
    Map<String, List<String>> categoryName1 = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .flatMap(order -> order.getProducts().stream())
            .collect(Collectors.groupingBy(
                            Product::getCategory,
                            Collectors.mapping(Product::getName, Collectors.toList())
                    )
            );

      /*  System.out.println(categoryName);
        System.out.println(categoryName1);*/

    // 15 Получите Map<String, Product> → самый дорогой продукт по каждой категории.*/
    Map<String, Product> products = customerList.stream()
            .flatMap(customer -> customer.getOrderds().stream())
            .flatMap(order -> order.getProducts().stream())
            .collect(Collectors.toMap(
                            Product::getCategory,
                            product -> product,
                            (ex, repl) ->
                                    ex.getPrice().compareTo(repl.getPrice()) > 0 ? ex : repl
                    )
            );
    /*    System.out.println(products);*/
}
