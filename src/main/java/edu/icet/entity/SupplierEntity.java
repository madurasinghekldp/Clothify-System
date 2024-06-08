package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SupplierEntity {
    @Id
    private String id;
    private String name;
    private String company;
    private String email;
    private String address;

}
