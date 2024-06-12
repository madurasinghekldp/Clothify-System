package edu.icet.entity;

import edu.icet.util.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(nullable = false)
    private String email;
    private String password;
}
