package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ResetPasswordFormController {

    public JFXTextField inputEmail;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void btnSendOnAction(ActionEvent actionEvent) {
    }

    public void btnGoBackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login-form.fxml"));
            Scene scene = new Scene(loader.load());
            LoginFormController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
