package edu.icet.bo.impl;

import edu.icet.bo.UserBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.UserDao;
import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

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
    public UserEntity getuserEntity(String email) {
        return userDao.getUserEntity(email);
    }
}
