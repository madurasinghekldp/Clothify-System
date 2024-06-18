package edu.icet.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTable {
    private String id;
    private String employee;
    private String customer;
    private String total;
    private String paymentType;
    private String date;
}
