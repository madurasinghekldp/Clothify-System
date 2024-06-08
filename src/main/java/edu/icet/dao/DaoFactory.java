package edu.icet.dao;

import edu.icet.dao.impl.SupplierDaoImpl;
import edu.icet.dao.impl.UserDaoImpl;
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
        }
        return null;
    }
}
