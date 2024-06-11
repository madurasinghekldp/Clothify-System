package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.UserBo;
import edu.icet.dto.User;
import edu.icet.util.BoType;
import edu.icet.util.Encryptor;
import edu.icet.util.OTPGenerator;
import edu.icet.util.SendMailUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ResetPasswordFormController {

    public JFXTextField inputEmail;
    private Stage stage;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void btnSendOnAction(ActionEvent actionEvent) {
        User user = userBo.getUser(inputEmail.getText());
        if(user!=null){
            String otp = new OTPGenerator().generateOTP();
            new SendMailUtil(inputEmail.getText(),otp,"change password.").SendMail();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/verify-reset-form.fxml"));
                Scene scene = new Scene(loader.load());
                VerifyResetFormController controller = loader.getController();
                controller.setStage(stage);
                controller.setOtp(otp);
                controller.setUser(user);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
