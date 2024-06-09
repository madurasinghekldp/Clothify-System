package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.UserBo;
import edu.icet.controller.user.UserBuilder;
import edu.icet.dto.User;
import edu.icet.util.BoType;
import edu.icet.util.Encryptor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinalRegisterFormController {
    public JFXTextField inputPassword;

    private UserBuilder builder;

    private Stage stage;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBuilder(UserBuilder builder){
        this.builder = builder;
    }

    public void btnGoBackOnAction(ActionEvent actionEvent) {
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

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        UserBuilder build = builder
                .setId(generateUserId())
                .setPassword(new Encryptor().getEncryptedPassword(inputPassword.getText()));
        User user = build.getUser();
        boolean saved = userBo.save(user);
        if(saved){
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

    private String generateUserId() {
        long count = userBo.getCount();
        System.out.println(count);
        if (count == 0) {
            return "U001";
        }
        String last = userBo.getLast();
        Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
        Matcher matcher = pattern.matcher(last);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            System.out.println(number);
            return String.format("U%03d", number);
        } else {
            new Alert(Alert.AlertType.WARNING,"hello").show();
        }
        return null;
    }
}
