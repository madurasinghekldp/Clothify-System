package edu.icet.controller.customer;

import edu.icet.bo.BoFactory;
import edu.icet.bo.CustomerBo;
import edu.icet.dto.Customer;
import edu.icet.util.BoType;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerController {

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    private static CustomerController instance;

    private CustomerController(){}

    public static CustomerController getInstance(){
        if(instance==null){
            return instance = new CustomerController();
        }
        return instance;
    }

    public String generateEmployeeId(){
        long count = customerBo.getCount();
        if (count == 0) {
            return "C001";
        }
        String last = customerBo.getLast();
        Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
        Matcher matcher = pattern.matcher(last);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            return String.format("C%03d", number);
        } else {
            new Alert(Alert.AlertType.WARNING,"hello").show();
        }
        return null;
    }
    public Customer searchCustomer(String id){
        return customerBo.getById(id);
    }
}
