package edu.icet.bo.impl;

import edu.icet.bo.OrderDetailBo;
import edu.icet.dto.OrderDetail;

import java.util.List;

public class OrderDetailBoImpl implements OrderDetailBo {
    @Override
    public boolean save(OrderDetail dto) {
        return false;
    }

    @Override
    public long getCount() {
        return 0;
    }

    @Override
    public String getLast() {
        return null;
    }

    @Override
    public List<OrderDetail> getAll() {
        return null;
    }

    @Override
    public OrderDetail getById(String id) {
        return null;
    }

    @Override
    public boolean update(OrderDetail dto) {
        return false;
    }

    @Override
    public boolean delete(OrderDetail dto) {
        return false;
    }
}
