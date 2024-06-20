package edu.icet.dao;

import edu.icet.entity.OrderDetailEntity;

import java.util.List;

public interface OrderDetailDao extends CrudDao<OrderDetailEntity>{
    List<OrderDetailEntity> getByOrder(String id);
}
