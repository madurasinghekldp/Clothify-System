package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField inputEmail;
    public JFXTextField inputPassword;
    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }


    public void btnLoginOnAction(ActionEvent actionEvent) {
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
