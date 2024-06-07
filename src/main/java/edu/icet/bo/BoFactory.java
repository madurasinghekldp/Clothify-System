package edu.icet.bo;

import edu.icet.bo.impl.SupplierBoImpl;
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
        }
        return null;
    }
}
