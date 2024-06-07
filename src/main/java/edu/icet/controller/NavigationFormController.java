package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import edu.icet.controller.supplier.SupplierManageFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationFormController {
    public JFXButton btnManageUser;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void btnManageUserOnAction(ActionEvent actionEvent) {
    }

    public void btnManageSupplierOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supplier-manage-form.fxml"));
            Scene scene = new Scene(loader.load());
            SupplierManageFormController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnManageProductOnAction(ActionEvent actionEvent) {
    }

    public void btnManageEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnManageCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnManageOrderOnAction(ActionEvent actionEvent) {
    }


    public void btnChangePasswordOnAction(ActionEvent actionEvent) {
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
