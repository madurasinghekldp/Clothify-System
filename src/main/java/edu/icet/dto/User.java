package edu.icet.dto;

import edu.icet.util.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;
    private UserType type;
    private String email;
    private String password;
}
