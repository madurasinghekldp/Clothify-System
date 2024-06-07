package edu.icet.dto;

import edu.icet.entity.SupplierEntity;
import edu.icet.util.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String name;
    private Integer size;
    private Double price;
    private Integer qty;
    private Supplier supplier;
    private Category category;
}
