package edu.icet.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTable {
    private String id;
    private String name;
    private String email;
    private String address;
}
