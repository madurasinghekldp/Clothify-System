package edu.icet.controller.supplier;

import edu.icet.bo.BoFactory;
import edu.icet.bo.SupplierBo;
import edu.icet.dto.Supplier;
import edu.icet.util.BoType;

public class SupplierController {
    private static SupplierController instance;

    private SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);
    private SupplierController(){

    }

    public static SupplierController getInstance(){
        if(instance==null){
            return instance = new SupplierController();
        }
        return instance;
    }

    public Supplier searchSupplier(String id){
        return supplierBo.getById(id);
    }
}
