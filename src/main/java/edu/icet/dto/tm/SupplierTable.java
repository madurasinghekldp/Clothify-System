package edu.icet.dto.tm;

import edu.icet.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierTable {

    private String id;
    private String name;
    private String company;
    private String email;
    private String address;
    private String user;

}
