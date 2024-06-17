package edu.icet.controller.order;

import edu.icet.bo.BoFactory;
import edu.icet.bo.OrderBo;
import edu.icet.dto.Order;
import edu.icet.util.BoType;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderController {
    private OrderBo orderBo = BoFactory.getInstance().getBo(BoType.ORDER);
    private static OrderController instance;

    private OrderController(){}

    public static OrderController getInstance(){
        if(instance==null){
            return instance = new OrderController();
        }
        return instance;
    }

    public String generateOrderId(){
        long count = orderBo.getCount();

        if (count == 0) {
            return "O001";
        }
        String last = orderBo.getLast();
        Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
        Matcher matcher = pattern.matcher(last);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;

            return String.format("O%03d", number);
        } else {
            new Alert(Alert.AlertType.WARNING,"hello").show();
        }
        return null;
    }

    public Order searchOrder(String id){
        return orderBo.getById(id);
    }
}
