package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VerifyEmailFormController {
    public JFXTextField digit1;
    public JFXTextField digit2;
    public JFXTextField digit3;
    public JFXTextField digit4;
    public JFXTextField digit5;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
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


}
