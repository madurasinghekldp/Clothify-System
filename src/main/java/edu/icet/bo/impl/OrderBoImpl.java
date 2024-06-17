package edu.icet.bo.impl;

import edu.icet.bo.OrderBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.OrderDao;
import edu.icet.dto.Order;
import edu.icet.entity.OrderEntity;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderBoImpl implements OrderBo {
    private OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
    @Override
    public boolean save(Order dto) {
        return orderDao.save(new ModelMapper().map(dto, OrderEntity.class));
    }

    @Override
    public long getCount() {
        return orderDao.getCount();
    }

    @Override
    public String getLast() {
        return orderDao.getLast();
    }

    @Override
    public List<Order> getAll() {
        List<OrderEntity> all = orderDao.getAll();
        List<Order> orders = new ArrayList<>();
        for(OrderEntity orderEntity: all){
            orders.add(new ModelMapper().map(orderEntity,Order.class));
        }
        return orders;
    }

    @Override
    public Order getById(String id) {
        OrderEntity orderEntity = orderDao.getById(id);
        if(orderEntity!=null){
            return new ModelMapper().map(orderEntity,Order.class);
        }
        return null;
    }

    @Override
    public boolean update(Order dto) {
        return false;
    }

    @Override
    public boolean delete(Order dto) {
        return orderDao.delete(new ModelMapper().map(dto,OrderEntity.class));
    }
}
