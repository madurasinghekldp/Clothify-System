package edu.icet.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;
    private String email;
    private String password;
}
