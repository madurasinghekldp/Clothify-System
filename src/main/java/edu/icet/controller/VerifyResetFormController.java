package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import edu.icet.controller.user.UserBuilder;
import edu.icet.dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VerifyResetFormController implements Initializable {
    public JFXTextField digit1;
    public JFXTextField digit2;
    public JFXTextField digit3;
    public JFXTextField digit4;
    public JFXTextField digit5;

    private User user;

    private String otp;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setOtp(String otp){
        this.otp = otp;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void btnGoBackOnAction(ActionEvent actionEvent) {
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

    public void btnContinueOnAction(ActionEvent actionEvent) {
        if(otp.equals(digit1.getText()+digit2.getText()+digit3.getText()+digit4.getText()+digit5.getText())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/final-password-change-form.fxml"));
                Scene scene = new Scene(loader.load());
                FinalPasswordChangeFormController controller = loader.getController();
                controller.setStage(stage);
                controller.setOtp(otp);
                controller.setUser(user);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR,"OTP is not correct.").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        digit1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 1) {
                digit1.setText(oldValue);
                digit2.requestFocus();
                digit2.positionCaret(0);
            }
        });
        digit2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 1) {
                digit2.setText(oldValue);
                digit3.requestFocus();
                digit3.positionCaret(0);
            }
        });
        digit3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 1) {
                digit3.setText(oldValue);
                digit4.requestFocus();
                digit4.positionCaret(0);
            }
        });
        digit4.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 1) {
                digit4.setText(oldValue);
                digit5.requestFocus();
                digit5.positionCaret(0);
            }
        });
        digit5.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 1) {
                digit5.setText(oldValue);
            }
        });
    }
}
