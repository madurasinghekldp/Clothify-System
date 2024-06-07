package edu.icet.entity;

import edu.icet.util.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private Integer size;
    private Double price;
    private Integer qty;

    @ManyToOne
    @JoinColumn(name = "supplierId",nullable = false)
    private SupplierEntity supplier;

    @Enumerated(EnumType.STRING)
    private Category category;
}
