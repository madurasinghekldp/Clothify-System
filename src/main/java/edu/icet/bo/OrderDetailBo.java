package edu.icet.bo;

import edu.icet.dto.Order;
import edu.icet.dto.OrderDetail;

import java.util.List;

public interface OrderDetailBo extends SuperBo{
    boolean save(OrderDetail dto);
    long getCount();
    String getLast();

    List<OrderDetail> getAll();

    OrderDetail getById(String id);

    boolean update(OrderDetail dto);

    boolean delete(OrderDetail dto);
}
