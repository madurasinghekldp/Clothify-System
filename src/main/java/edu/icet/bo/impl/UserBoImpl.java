package edu.icet.bo.impl;

import edu.icet.bo.UserBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.UserDao;
import edu.icet.util.DaoType;

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
}
