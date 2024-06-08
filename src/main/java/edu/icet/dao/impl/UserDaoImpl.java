package edu.icet.dao.impl;

import edu.icet.dao.UserDao;
import edu.icet.entity.UserEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity dao) {
        return false;
    }

    @Override
    public long getCount() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        long count = (long) session.createQuery("SELECT COUNT(u) FROM UserEntity u").uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count;
    }

    @Override
    public String getLast() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String last = (String) session.createQuery("SELECT u.id FROM UserEntity u ORDER BY u.id DESC").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return last;
    }
}
