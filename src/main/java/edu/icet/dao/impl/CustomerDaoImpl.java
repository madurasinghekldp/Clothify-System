package edu.icet.dao.impl;

import edu.icet.dao.CustomerDao;
import edu.icet.entity.CustomerEntity;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity entity) {
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
        long count = (long) session.createQuery("SELECT COUNT(c) FROM CustomerEntity c").uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count;
    }

    @Override
    public String getLast() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String last = (String) session.createQuery("SELECT c.id FROM CustomerEntity c ORDER BY c.id DESC").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return last;
    }

    @Override
    public CustomerEntity getById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        CustomerEntity customer = null;

        try {
            transaction = session.beginTransaction();
            Query<CustomerEntity> query = session.createQuery("FROM CustomerEntity c WHERE c.id = :id", CustomerEntity.class);
            query.setParameter("id", id);
            customer = query.setMaxResults(1).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customer;
    }

    @Override
    public boolean update(CustomerEntity entity) {
        try{
            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public List<CustomerEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query<CustomerEntity> query = session.createQuery("FROM CustomerEntity",CustomerEntity.class);
        List<CustomerEntity> custList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return custList;
    }

    @Override
    public boolean delete(CustomerEntity entity) {
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
