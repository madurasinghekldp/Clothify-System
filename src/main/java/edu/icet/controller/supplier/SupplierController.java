package edu.icet.controller.supplier;

import edu.icet.bo.BoFactory;
import edu.icet.bo.SupplierBo;
import edu.icet.dto.Supplier;
import edu.icet.util.BoType;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public String generateSupplierId(){
        long count = supplierBo.getCount();
        System.out.println(count);
        if (count == 0) {
            return "S001";
        }
        String last = supplierBo.getLast();
        Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
        Matcher matcher = pattern.matcher(last);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            System.out.println(number);
            return String.format("S%03d", number);
        } else {
            new Alert(Alert.AlertType.WARNING,"hello").show();
        }
        return null;
    }
}
