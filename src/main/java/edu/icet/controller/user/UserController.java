package edu.icet.controller.user;

import edu.icet.bo.BoFactory;
import edu.icet.bo.UserBo;
import edu.icet.dto.User;
import edu.icet.util.BoType;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {

    private static UserController instance;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    private UserController(){}

    public static UserController getInstance(){
        if(instance==null){
            return instance = new UserController();
        }
        return instance;
    }

    public User searchUser(String id){
        return userBo.getById(id);
    }

    public String generateUserId(){
        long count = userBo.getCount();
        System.out.println(count);
        if (count == 0) {
            return "U001";
        }
        String last = userBo.getLast();
        Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
        Matcher matcher = pattern.matcher(last);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            System.out.println(number);
            return String.format("U%03d", number);
        } else {
            new Alert(Alert.AlertType.WARNING,"hello").show();
        }
        return null;
    }
}
