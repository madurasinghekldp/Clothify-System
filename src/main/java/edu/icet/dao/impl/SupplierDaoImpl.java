package edu.icet.dao.impl;

import edu.icet.dao.SupplierDao;
import edu.icet.entity.SupplierEntity;
import edu.icet.entity.UserEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity entity) {
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
        long count = (long) session.createQuery("SELECT COUNT(s) FROM SupplierEntity s").uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count;
    }

    @Override
    public String getLast() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String last = (String) session.createQuery("SELECT s.id FROM SupplierEntity s ORDER BY s.id DESC").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return last;
    }

    @Override
    public SupplierEntity getById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        SupplierEntity supplier = null;

        try {
            transaction = session.beginTransaction();
            Query<SupplierEntity> query = session.createQuery("FROM SupplierEntity s WHERE s.id = :id", SupplierEntity.class);
            query.setParameter("id", id);
            supplier = query.setMaxResults(1).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return supplier;
    }

    @Override
    public boolean update(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    @Override
    public List<SupplierEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query<SupplierEntity> query = session.createQuery("FROM SupplierEntity",SupplierEntity.class);
        List<SupplierEntity> supplierList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return supplierList;
    }

    @Override
    public boolean delete(SupplierEntity entity) {
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
