package edu.icet.bo;

import edu.icet.bo.impl.*;
import edu.icet.util.BoType;

public class BoFactory {
    private static BoFactory instance;
    private BoFactory(){}

    public static BoFactory getInstance(){
        return instance!=null?instance:(instance=new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case SUPPLIER: return (T) new SupplierBoImpl();
            case USER: return (T) new UserBoImpl();
            case PRODUCT: return (T) new ProductBoImpl();
            case EMPLOYEE: return (T) new EmployeeBoImpl();
            case CUSTOMER: return (T) new CustomerBoImpl();
            case ORDER: return (T) new OrderBoImpl();
            case ORDERDETAIL: return (T) new OrderDetailBoImpl();
        }
        return null;
    }
}
