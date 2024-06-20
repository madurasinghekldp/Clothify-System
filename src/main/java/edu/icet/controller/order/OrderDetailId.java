package edu.icet.controller.order;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetailId implements Serializable {
    private String order;
    private String product;

    public OrderDetailId() {
    }

    public OrderDetailId(String order, String product) {
        this.order = order;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailId that)) return false;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
