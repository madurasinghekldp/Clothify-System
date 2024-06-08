package edu.icet.dao;

public interface CrudDao<T> extends SuperDao{
    boolean save(T dao);

    long getCount();

    String getLast();

}
