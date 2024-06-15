package edu.icet.entity;

import edu.icet.dto.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmployeeEntity {
    @Id
    private String id;
    private String name;
    private String company;

    @Column(nullable = false,unique = true)
    private String email;

    private String address;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserEntity user;
}
