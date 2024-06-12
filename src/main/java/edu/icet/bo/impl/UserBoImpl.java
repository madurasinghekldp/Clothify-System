package edu.icet.bo.impl;

import edu.icet.bo.UserBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.UserDao;
import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {

    private UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);
    @Override
    public long getCount() {
        return userDao.getCount();
    }

    @Override
    public String getLast() {
        return userDao.getLast();
    }

    @Override
    public boolean save(User dto) {
        return userDao.save(new ModelMapper().map(dto, UserEntity.class));
    }

    @Override
    public User getUser(String email) {
        UserEntity userEntity = userDao.getUserEntity(email);
        if(userEntity!=null){
            return new ModelMapper().map(userEntity,User.class);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<UserEntity> all = userDao.getAll();
        List<User> users = new ArrayList<>();
        for(UserEntity userEntity:all){
            users.add(new ModelMapper().map(userEntity,User.class));
        }
        return users;
    }

    @Override
    public User getById(String id) {
        UserEntity userEntity = userDao.getById(id);
        if(userEntity!=null){
            return new ModelMapper().map(userEntity,User.class);
        }
        else return null;
    }

    @Override
    public boolean update(User dto) {
        return userDao.update(new ModelMapper().map(dto,UserEntity.class));
    }

    @Override
    public boolean delete(User dto) {
        return userDao.delete(new ModelMapper().map(dto,UserEntity.class));
    }
}
