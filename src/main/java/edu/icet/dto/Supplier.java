package edu.icet.dto;

import edu.icet.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private String id;
    private String name;
    private String company;
    private String email;
    private String address;
    private User user;

    @Override
    public String toString() {
        return id+"";
    }
}
