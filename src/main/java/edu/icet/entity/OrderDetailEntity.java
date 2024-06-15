package edu.icet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetailEntity {
    @ManyToOne
    @JoinColumn(name = "orderId",nullable = false)
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "productId",nullable = false)
    private ProductEntity product;
    private Integer qty;
}
