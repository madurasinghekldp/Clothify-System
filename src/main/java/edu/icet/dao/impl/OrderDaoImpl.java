package edu.icet.dao.impl;

import edu.icet.dao.OrderDao;
import edu.icet.entity.CustomerEntity;
import edu.icet.entity.OrderEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity entity) {
        try{
            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();
            session.persist(entity);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public long getCount() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        long count = (long) session.createQuery("SELECT COUNT(o) FROM OrderEntity o").uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count;
    }

    @Override
    public String getLast() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String last = (String) session.createQuery("SELECT o.id FROM OrderEntity o ORDER BY o.id DESC").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return last;
    }

    @Override
    public OrderEntity getById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        OrderEntity order = null;

        try {
            transaction = session.beginTransaction();
            Query<OrderEntity> query = session.createQuery("FROM OrderEntity o WHERE o.id = :id", OrderEntity.class);
            query.setParameter("id", id);
            order = query.setMaxResults(1).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return order;
    }

    @Override
    public boolean update(OrderEntity dao) {
        return false;
    }

    @Override
    public List<OrderEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query<OrderEntity> query = session.createQuery("FROM OrderEntity",OrderEntity.class);
        List<OrderEntity> orderList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return orderList;
    }

    @Override
    public boolean delete(OrderEntity entity) {
        try{
            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();
            session.remove(entity);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
