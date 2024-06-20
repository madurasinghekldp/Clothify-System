package edu.icet.entity;

import edu.icet.controller.order.OrderDetailId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@IdClass(OrderDetailId.class)
public class OrderDetailEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "orderId",nullable = false)
    private OrderEntity order;
    @Id
    @ManyToOne
    @JoinColumn(name = "productId",nullable = false)
    private ProductEntity product;
    @Column(nullable = false)
    private Integer qty;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof OrderDetailEntity that)) return false;
//        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(order, product);
//    }
}
