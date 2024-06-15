package edu.icet.controller.employee;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.EmployeeBo;
import edu.icet.controller.NavigationFormController;
import edu.icet.dto.Employee;
import edu.icet.dto.User;
import edu.icet.dto.tm.EmployeeTable;
import edu.icet.util.BoType;
import edu.icet.util.EmailValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeManageFormController implements Initializable {
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
    
    private Employee employee;

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
        employee = new Employee(
                EmployeeController.getInstance().generateEmployeeId(),
                inputName.getText(),
                inputCompany.getText(),
                EmailValidator.getInstance().validatedEmail(inputEmail.getText()),
                inputAddress.getText(),
                user
        );
        boolean b = empBo.save(employee);
        if(b) {
            new Alert(Alert.AlertType.INFORMATION,"Employee Added..!").show();
        }
        loadEmployeeTable();
        clearInputs();
    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTable> table = FXCollections.observableArrayList();
        List<Employee> all = empBo.getAll();
        all.forEach(
                employee1 -> {
                    EmployeeTable etbl = new EmployeeTable(
                            employee1.getId(),
                            employee1.getName(),
                            employee1.getCompany(),
                            employee1.getEmail(),
                            employee1.getAddress(),
                            employee1.getUser().getId()
                    );
                    table.add(etbl);
                }
        );
        tblEmployee.setItems(table);
    }

    public void btnSearchEmployeeOnAction(ActionEvent actionEvent) {
        employee = EmployeeController.getInstance().searchEmployee(inputId.getText());
        if(employee!=null){
            inputName.setText(employee.getName());
            inputCompany.setText(employee.getCompany());
            inputEmail.setText(employee.getEmail());
            inputAddress.setText(employee.getAddress());
        }
        else{
            new Alert(Alert.AlertType.WARNING,"No such employee..!").show();
        }
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
        if(employee!=null){
            employee.setName(inputName.getText());
            employee.setCompany(inputCompany.getText());
            employee.setEmail(EmailValidator.getInstance().validatedEmail(inputEmail.getText()));
            employee.setAddress(inputAddress.getText());
            employee.setUser(user);
            boolean b = empBo.update(employee);
            if(b) {
                new Alert(Alert.AlertType.INFORMATION,"Employee updated..!").show();
            }
            loadEmployeeTable();
        }
        clearInputs();
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
        if(employee!=null){
            boolean b = empBo.delete(employee);
            if(b) {
                new Alert(Alert.AlertType.INFORMATION,"Employee deleted..!").show();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Employee can not be deleted..!").show();
            }
        }
        loadEmployeeTable();
        clearInputs();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCreator.setCellValueFactory(new PropertyValueFactory<>("user"));

        loadEmployeeTable();
    }

    private void clearInputs(){
        inputId.setText("");
        inputName.setText("");
        inputCompany.setText("");
        inputEmail.setText("");
        inputAddress.setText("");
        employee = null;
    }
}
