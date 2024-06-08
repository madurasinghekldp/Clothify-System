package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import edu.icet.controller.user.UserBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VerifyEmailFormController implements Initializable {
    public JFXTextField digit1;
    public JFXTextField digit2;
    public JFXTextField digit3;
    public JFXTextField digit4;
    public JFXTextField digit5;

    private UserBuilder builder;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBuilder(UserBuilder builder){
        this.builder = builder;
    }

    public void btnGoBackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/register-form.fxml"));
            Scene scene = new Scene(loader.load());
            RegisterFormController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnContinueOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/final-register-form.fxml"));
            Scene scene = new Scene(loader.load());
            FinalRegisterFormController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
