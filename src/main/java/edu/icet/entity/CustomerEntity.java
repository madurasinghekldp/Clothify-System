package edu.icet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerEntity {
    @Id
    private String id;
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    private String address;
}
