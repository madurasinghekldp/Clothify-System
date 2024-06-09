package edu.icet.dao;

import edu.icet.entity.UserEntity;

public interface UserDao extends CrudDao<UserEntity>{

    UserEntity getUserEntity(String email);
}
