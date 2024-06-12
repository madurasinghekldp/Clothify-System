package edu.icet.bo;

import edu.icet.dto.User;
import edu.icet.entity.UserEntity;

import java.util.List;

public interface UserBo extends SuperBo{

    long getCount();
    String getLast();

    boolean save(User dto);

    User getUser(String email);

    List<User> getAll();

    User getById(String id);

    boolean update(User dto);

    boolean delete(User dto);
}
