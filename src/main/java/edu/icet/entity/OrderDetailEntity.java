package edu.icet.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetailEntity {
    private String orderId;
    private String productId;
    private Integer qty;
}
