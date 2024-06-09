package edu.icet.bo;

import edu.icet.dto.User;
import edu.icet.entity.UserEntity;

public interface UserBo extends SuperBo{

    long getCount();
    String getLast();

    boolean save(User dto);

    UserEntity getuserEntity(String email);
}
