package edu.icet.dao.impl;

import edu.icet.dao.SupplierDao;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;

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
}
