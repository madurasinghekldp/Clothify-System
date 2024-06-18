package edu.icet.bo.impl;

import edu.icet.bo.OrderDetailBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.OrderDetailDao;
import edu.icet.dto.Order;
import edu.icet.dto.OrderDetail;
import edu.icet.entity.OrderDetailEntity;
import edu.icet.entity.OrderEntity;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailBoImpl implements OrderDetailBo {
    private OrderDetailDao orderDetailDao = DaoFactory.getInstance().getDao(DaoType.ORDERDETAIL);
    @Override
    public boolean save(OrderDetail dto) {
        return orderDetailDao.save(new ModelMapper().map(dto, OrderDetailEntity.class));
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
        List<OrderDetailEntity> all = orderDetailDao.getAll();
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(OrderDetailEntity orderDetailEntity: all){
            orderDetails.add(new ModelMapper().map(orderDetailEntity,OrderDetail.class));
        }
        return orderDetails;
    }

    @Override
    public OrderDetail getById(String id) {
        OrderDetailEntity orderDetailEntity = orderDetailDao.getById(id);
        if(orderDetailEntity!=null){
            return new ModelMapper().map(orderDetailEntity,OrderDetail.class);
        }
        return null;
    }

    @Override
    public boolean update(OrderDetail dto) {
        return false;
    }

    @Override
    public boolean delete(OrderDetail dto) {
        return orderDetailDao.delete(new ModelMapper().map(dto,OrderDetailEntity.class));
    }
}
