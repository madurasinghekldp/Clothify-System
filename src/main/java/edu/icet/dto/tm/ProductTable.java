package edu.icet.dto.tm;

import edu.icet.dto.Supplier;
import edu.icet.util.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTable {
    private String id;
    private String name;
    private Integer size;
    private Double price;
    private Integer qty;
    private String supplier;
    private Category category;
}
