package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterFormController {

    public JFXTextField inputEmail;
    public JFXTextField inputFirst;
    public JFXTextField inputLast;
    public DatePicker inputDOB;
    public JFXTextField inputAddress;
    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }


    public void btnContinueOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/verify-email-form.fxml"));
            Scene scene = new Scene(loader.load());
            VerifyEmailFormController controller = loader.getController();
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
