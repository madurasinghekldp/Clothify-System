package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import edu.icet.controller.customer.CustomerManageFormController;
import edu.icet.controller.employee.EmployeeManageFormController;
import edu.icet.controller.order.OrderManageFormController;
import edu.icet.controller.product.ProductManageFormController;
import edu.icet.controller.supplier.SupplierManageFormController;
import edu.icet.controller.user.UserManageFormController;
import edu.icet.dto.User;
import edu.icet.util.OTPGenerator;
import edu.icet.util.SendMailUtil;
import edu.icet.util.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.loader.ast.spi.AfterLoadAction;
import org.hibernate.metamodel.mapping.EntityMappingType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavigationFormController implements Initializable{
    public JFXButton btnManageUser;
    public JFXButton btnmanageEmployee;
    public RowConstraints userRow;
    public RowConstraints employeeRow;

    private Stage stage;

    private User user;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(User user){
        this.user = user;
        init();
    }

    private void init(){
        if(user.getType()==UserType.USER){
            userRow.setMaxHeight(0);
            employeeRow.setMaxHeight(0);
            btnmanageEmployee.setVisible(false);
            btnManageUser.setVisible(false);
        }
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
            controller.setLoader(loader);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnManageEmployeeOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/emp-manage-form.fxml"));
            Scene scene = new Scene(loader.load());
            EmployeeManageFormController controller = loader.getController();
            controller.setStage(stage);
            controller.setUser(user);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnManageCustomerOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer-manage-form.fxml"));
            Scene scene = new Scene(loader.load());
            CustomerManageFormController controller = loader.getController();
            controller.setStage(stage);
            controller.setUser(user);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnManageOrderOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/order-manage-form.fxml"));
            Scene scene = new Scene(loader.load());
            OrderManageFormController controller = loader.getController();
            controller.setStage(stage);
            controller.setUser(user);
            controller.setLoader(loader);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void btnChangePasswordOnAction(ActionEvent actionEvent) {
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
