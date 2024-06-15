package edu.icet.controller.customer;

import com.jfoenix.controls.JFXTextField;
import edu.icet.controller.NavigationFormController;
import edu.icet.dto.Customer;
import edu.icet.dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerManageFormController implements Initializable {
    public JFXTextField inputId;
    public JFXTextField inputName;
    public JFXTextField inputEmail;
    public JFXTextField inputAddress;

    private User user;
    private Stage stage;
    private Customer customer;

    public void setUser(User user) {
        this.user = user;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void btnGoBackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/navigation-form.fxml"));
            Scene scene = new Scene(loader.load());
            NavigationFormController controller = loader.getController();
            controller.setStage(stage);
            controller.setUser(user);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        customer = new Customer(

        );
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
