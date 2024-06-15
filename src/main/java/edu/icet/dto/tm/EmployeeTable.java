package edu.icet.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTable {
    private String id;
    private String name;
    private String company;
    private String email;
    private String address;
    private String user;
}
