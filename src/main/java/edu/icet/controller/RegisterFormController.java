package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.UserBo;
import edu.icet.controller.user.UserBuilder;
import edu.icet.util.BoType;
import edu.icet.util.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFormController implements Initializable {

    public JFXTextField inputEmail;
    public JFXTextField inputFirst;
    public JFXTextField inputLast;
    public DatePicker inputDOB;
    public JFXTextField inputAddress;
    private Stage stage;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);
    public void setStage(Stage stage){
        this.stage = stage;
    }


    public void btnContinueOnAction(ActionEvent actionEvent) {
        UserBuilder builder = new UserBuilder()
                .setId(generateUserId())
                .setEmail(inputEmail.getText())
                .setFirstName(inputFirst.getText())
                .setLastName(inputLast.getText())
                .setDob(inputDOB.getValue())
                .setAddress(inputAddress.getText())
                .setType(UserType.USER);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/verify-email-form.fxml"));
            Scene scene = new Scene(loader.load());
            VerifyEmailFormController controller = loader.getController();
            controller.setStage(stage);
            controller.setBuilder(builder);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private String generateUserId() {
        long count = userBo.getCount();
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
