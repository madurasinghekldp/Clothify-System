package edu.icet.bo;

import edu.icet.dto.Customer;
import edu.icet.dto.Order;

import java.util.List;

public interface OrderBo extends SuperBo{
    boolean save(Order dto);
    long getCount();
    String getLast();

    List<Order> getAll();

    Order getById(String id);

    boolean update(Order dto);

    boolean delete(Order dto);
}
