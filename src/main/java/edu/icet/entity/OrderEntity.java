package edu.icet.entity;

import edu.icet.util.PaymentType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {
    private String id;
    private Double total;
    private PaymentType paymentType;
}
