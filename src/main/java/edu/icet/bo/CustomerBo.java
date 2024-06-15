package edu.icet.bo;

import edu.icet.dto.Customer;
import edu.icet.dto.Employee;

import java.util.List;

public interface CustomerBo {
    boolean save(Customer dto);
    long getCount();
    String getLast();

    List<Customer> getAll();

    Customer getById(String id);

    boolean update(Customer dto);

    boolean delete(Customer dto);
}
