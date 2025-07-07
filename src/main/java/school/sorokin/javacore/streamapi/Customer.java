package school.sorokin.javacore.streamapi;

import java.util.Objects;
import java.util.Set;

public class Customer {
    private final Long id;

    private final String name;

    private final Long level;

    private final Set<Order> orderds;

    public Customer(Long id, String name, Long level, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.orderds = orders;
    }

    public String getName() {
        return name;
    }

    public Long getLevel() {
        return level;
    }

    public Set<Order> getOrderds() {
        return orderds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(level, customer.level) && Objects.equals(orderds, customer.orderds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, orderds);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", orderds=" + orderds +
                '}';
    }
}
