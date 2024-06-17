package edu.icet.dao;

import edu.icet.dao.impl.*;
import edu.icet.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return instance!=null?instance:(instance = new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch(type){
            case SUPPLIER: return (T) new SupplierDaoImpl();
            case USER: return (T) new UserDaoImpl();
            case PRODUCT: return (T) new ProductDaoImpl();
            case EMPLOYEE: return (T) new EmployeeDaoImpl();
            case CUSTOMER: return (T) new CustomerDaoImpl();
            case ORDER: return (T) new OrderDaoImpl();
            case ORDERDETAIL: return (T) new OrderDetailDaoImpl();
        }
        return null;
    }
}
