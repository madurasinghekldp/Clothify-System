package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetailEntity {
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
}
