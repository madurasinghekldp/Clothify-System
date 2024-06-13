package edu.icet.controller.employee;

import edu.icet.bo.BoFactory;
import edu.icet.bo.EmployeeBo;
import edu.icet.dto.Employee;
import edu.icet.dto.Product;
import edu.icet.util.BoType;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeController {

    private static EmployeeController instance;

    private EmployeeBo empBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    private EmployeeController(){}

    public static EmployeeController getInstance(){
        if(instance==null){
            return instance = new EmployeeController();
        }
        return instance;
    }

    public String generateEmployeeId(){
        long count = empBo.getCount();
        if (count == 0) {
            return "E001";
        }
        String last = empBo.getLast();
        Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
        Matcher matcher = pattern.matcher(last);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            return String.format("E%03d", number);
        } else {
            new Alert(Alert.AlertType.WARNING,"hello").show();
        }
        return null;
    }

    public Employee searchEmployee(String id){
        return empBo.getById(id);
    }
}
