package edu.icet.controller.user;

import com.jfoenix.controls.JFXTextField;
import edu.icet.controller.NavigationFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserManageFormController{
    public JFXTextField inputId;
    public JFXTextField inputFirstName;
    public JFXTextField inputLastName;
    public DatePicker inputDOB;
    public JFXTextField inputAddress;
    public JFXTextField inputType;
    public JFXTextField inputEmail;
    public TableView tblUser;
    public TableColumn colId;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void btnGoBackOnAction(ActionEvent actionEvent) {
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

    public void btnSearchUserOnAction(ActionEvent actionEvent) {
    }

    public void btnAddUserOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateUserOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteUserOnAction(ActionEvent actionEvent) {
    }


}
