package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import edu.icet.controller.product.ProductManageFormController;
import edu.icet.controller.supplier.SupplierManageFormController;
import edu.icet.controller.user.UserManageFormController;
import edu.icet.dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationFormController {
    public JFXButton btnManageUser;

    private Stage stage;

    private User user;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void btnManageUserOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user-manage-form.fxml"));
            Scene scene = new Scene(loader.load());
            UserManageFormController controller = loader.getController();
            controller.setStage(stage);
            controller.setUser(user);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnManageSupplierOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supplier-manage-form.fxml"));
            Scene scene = new Scene(loader.load());
            SupplierManageFormController controller = loader.getController();
            controller.setStage(stage);
            controller.setUser(user);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnManageProductOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/product-manage-form.fxml"));
            Scene scene = new Scene(loader.load());
            ProductManageFormController controller = loader.getController();
            controller.setStage(stage);
            controller.setUser(user);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
