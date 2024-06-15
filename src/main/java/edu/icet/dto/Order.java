package edu.icet.dto;

import edu.icet.util.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private Employee employee;
    private Customer customer;
    private Double total;
    private PaymentType paymentType;
    private LocalDate date;
}
