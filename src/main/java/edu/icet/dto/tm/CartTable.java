package edu.icet.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartTable {
    private String productId;
    private String productName;
    private String unitPrice;
    private String qty;
    private String total;
}
