package edu.icet.dao;

import edu.icet.entity.UserEntity;

public interface CrudDao<T> extends SuperDao{
    boolean save(T dao);

    long getCount();

    String getLast();

    T getById(String id);

    boolean update(T dao);
}
