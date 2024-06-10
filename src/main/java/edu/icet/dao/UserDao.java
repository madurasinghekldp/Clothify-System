package edu.icet.dao;

import edu.icet.entity.UserEntity;

import java.util.List;

public interface UserDao extends CrudDao<UserEntity>{

    UserEntity getUserEntity(String email);

    List<UserEntity> getAll();
}
