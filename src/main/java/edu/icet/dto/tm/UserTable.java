package edu.icet.dto.tm;

import edu.icet.util.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTable {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private UserType type;
    private String email;
    private String address;
}
