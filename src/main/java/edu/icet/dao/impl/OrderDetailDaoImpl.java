package edu.icet.dao.impl;

import edu.icet.dao.OrderDetailDao;
import edu.icet.entity.OrderDetailEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
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

        return null;
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

    @Override
    public List<OrderDetailEntity> getByOrder(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query<OrderDetailEntity> query = session.createQuery("FROM OrderDetailEntity WHERE order.id = :orderId", OrderDetailEntity.class);
        query.setParameter("orderId", id);
        List<OrderDetailEntity> orderDetailList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return orderDetailList;
    }
}
