package edu.icet.bo;

import edu.icet.dto.Employee;
import edu.icet.dto.Product;

import java.util.List;

public interface EmployeeBo {
    boolean save(Employee dto);
    long getCount();
    String getLast();

    List<Employee> getAll();

    Employee getById(String id);

    boolean update(Employee dto);

    boolean delete(Employee dto);
}
