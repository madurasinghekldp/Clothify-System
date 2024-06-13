package edu.icet.controller.employee;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.EmployeeBo;
import edu.icet.controller.NavigationFormController;
import edu.icet.dto.User;
import edu.icet.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeManageFormController {
    public JFXTextField inputId;
    public JFXTextField inputName;
    public JFXTextField inputEmail;
    public JFXTextField inputAddress;
    public JFXTextField inputCompany;
    public TableView tblEmployee;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCompany;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public TableColumn colCreator;

    private Stage stage;

    private User user;

    public void setUser(User user){
        this.user = user;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private EmployeeBo empBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

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

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) {

    }

    public void btnSearchEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
    }
}
