package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.UserBo;
import edu.icet.dto.User;
import edu.icet.util.BoType;
import edu.icet.util.Encryptor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FinalPasswordChangeFormController {
    public JFXTextField inputPassword;

    private Stage stage;

    private String otp;

    private User user;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void btnGoBackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/verify-reset-form.fxml"));
            Scene scene = new Scene(loader.load());
            VerifyResetFormController controller = loader.getController();
            controller.setStage(stage);
            controller.setUser(user);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void btnChangePasswordOnAction(ActionEvent actionEvent) {
        user.setPassword(new Encryptor().getEncryptedPassword(inputPassword.getText()));
        boolean updated = userBo.update(user);
        if(updated){
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
}
