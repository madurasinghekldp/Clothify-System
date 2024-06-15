package edu.icet.dao.impl;

import edu.icet.dao.EmployeeDao;
import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.ProductEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity entity) {
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
        long count = (long) session.createQuery("SELECT COUNT(e) FROM EmployeeEntity e").uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count;
    }

    @Override
    public String getLast() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String last = (String) session.createQuery("SELECT e.id FROM EmployeeEntity e ORDER BY e.id DESC").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return last;
    }

    @Override
    public EmployeeEntity getById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        EmployeeEntity employee = null;

        try {
            transaction = session.beginTransaction();
            Query<EmployeeEntity> query = session.createQuery("FROM EmployeeEntity e WHERE e.id = :id", EmployeeEntity.class);
            query.setParameter("id", id);
            employee = query.setMaxResults(1).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employee;
    }

    @Override
    public boolean update(EmployeeEntity entity) {
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
    public List<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query<EmployeeEntity> query = session.createQuery("FROM EmployeeEntity",EmployeeEntity.class);
        List<EmployeeEntity> empList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return empList;
    }

    @Override
    public boolean delete(EmployeeEntity entity) {
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
