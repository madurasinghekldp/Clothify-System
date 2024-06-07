package edu.icet.dto;

import edu.icet.util.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private Double total;
    private PaymentType paymentType;
}
