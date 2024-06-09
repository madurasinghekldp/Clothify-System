package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.UserBo;
import edu.icet.entity.UserEntity;
import edu.icet.util.BoType;
import edu.icet.util.Encryptor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField inputEmail;
    public JFXTextField inputPassword;
    private Stage stage;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);
    public void setStage(Stage stage){
        this.stage = stage;
    }


    public void btnLoginOnAction(ActionEvent actionEvent) {
        UserEntity userEntity = userBo.getuserEntity(inputEmail.getText());
        if(userEntity!=null){
            if(new Encryptor().getEncryptedPassword(inputPassword.getText()).equals(userEntity.getPassword())){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/navigation-form.fxml"));
                    Scene scene = new Scene(loader.load());
                    NavigationFormController controller = loader.getController();
                    controller.setStage(stage);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Incorrect user credentials.").show();
            }
        }
        else{
            new Alert(Alert.AlertType.ERROR,"Please sing up first.").show();
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reset-password-form.fxml"));
            Scene scene = new Scene(loader.load());
            ResetPasswordFormController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnGoBackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home-form.fxml"));
            Scene scene = new Scene(loader.load());
            HomeFormController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
