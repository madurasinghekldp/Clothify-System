package edu.icet.dao.impl;

import edu.icet.dao.UserDao;
import edu.icet.entity.SupplierEntity;
import edu.icet.entity.UserEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
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

    @Override
    public UserEntity getById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        UserEntity user = null;

        try {
            transaction = session.beginTransaction();
            Query<UserEntity> query = session.createQuery("FROM UserEntity u WHERE u.id = :id", UserEntity.class);
            query.setParameter("id", id);
            user = query.setMaxResults(1).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public boolean update(UserEntity dao) {
        return false;
    }


    @Override
    public UserEntity getUserEntity(String email) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        UserEntity user = null;

        try {
            transaction = session.beginTransaction();
            Query<UserEntity> query = session.createQuery("FROM UserEntity u WHERE u.email = :email", UserEntity.class);
            query.setParameter("email", email);
            user = query.setMaxResults(1).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<UserEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query<UserEntity> query = session.createQuery("FROM UserEntity",UserEntity.class);
        List<UserEntity> userList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return userList;
    }
}
