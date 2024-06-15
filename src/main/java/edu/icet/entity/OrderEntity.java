package edu.icet.entity;

import edu.icet.util.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "employeeId",nullable = false)
    private EmployeeEntity employee;
    @ManyToOne
    @JoinColumn(name = "customerId",nullable = false)
    private CustomerEntity customer;
    private Double total;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private LocalDate date;
}
