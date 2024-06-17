package edu.icet.dao.impl;

import edu.icet.dao.OrderDetailDao;
import edu.icet.entity.OrderDetailEntity;
import edu.icet.entity.OrderEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean save(OrderDetailEntity entity) {
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
        long count = (long) session.createQuery("SELECT COUNT(o) FROM OrderDetailEntity o").uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count;
    }

    @Override
    public String getLast() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String last = (String) session.createQuery("SELECT o.orderIid FROM OrderDetailEntity o ORDER BY o.orderId DESC").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return last;
    }

    @Override
    public OrderDetailEntity getById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        OrderDetailEntity orderDetail = null;

        try {
            transaction = session.beginTransaction();
            Query<OrderDetailEntity> query = session.createQuery("FROM OrderDetailEntity o WHERE o.orderId = :orderId", OrderDetailEntity.class);
            query.setParameter("orderId", id);
            orderDetail = query.setMaxResults(1).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orderDetail;
    }

    @Override
    public boolean update(OrderDetailEntity dao) {
        return false;
    }

    @Override
    public List<OrderDetailEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query<OrderDetailEntity> query = session.createQuery("FROM OrderDetailEntity",OrderDetailEntity.class);
        List<OrderDetailEntity> orderDetailList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return orderDetailList;
    }

    @Override
    public boolean delete(OrderDetailEntity entity) {
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
