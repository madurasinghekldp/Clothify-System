package edu.icet.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmployeeEntity {
    private String id;
    private String name;
    private String company;
    private String email;
    private String address;
}
