package edu.icet.controller.user;

import edu.icet.bo.BoFactory;
import edu.icet.bo.UserBo;
import edu.icet.dto.User;
import edu.icet.util.BoType;

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
}
