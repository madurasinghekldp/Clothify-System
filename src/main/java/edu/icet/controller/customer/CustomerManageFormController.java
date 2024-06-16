package edu.icet.controller.customer;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.CustomerBo;
import edu.icet.controller.NavigationFormController;
import edu.icet.dto.Customer;
import edu.icet.dto.User;
import edu.icet.dto.tm.CustomerTable;
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

public class CustomerManageFormController implements Initializable {
    public JFXTextField inputId;
    public JFXTextField inputName;
    public JFXTextField inputEmail;
    public JFXTextField inputAddress;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public TableView tblCustomer;

    private User user;
    private Stage stage;
    private Customer customer;

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

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
            CustomerController.getInstance().generateCustomerId(),
                inputName.getText(),
                EmailValidator.getInstance().validatedEmail(inputEmail.getText().trim()),
                inputAddress.getText()
        );
        boolean b = customerBo.save(customer);
        if(b) {
            new Alert(Alert.AlertType.INFORMATION,"Customer Added..!").show();
        }
        loadCustomerTable();
        clearInputs();
    }

    private void clearInputs() {
        inputId.setText("");
        inputName.setText("");
        inputEmail.setText("");
        inputAddress.setText("");
        customer = null;
    }

    private void loadCustomerTable() {
        ObservableList<CustomerTable> table = FXCollections.observableArrayList();
        List<Customer> all = customerBo.getAll();
        all.forEach(
                customer1 -> {
                    CustomerTable ctbl = new CustomerTable(
                            customer1.getId(),
                            customer1.getName(),
                            customer1.getEmail(),
                            customer1.getAddress()
                    );
                    table.add(ctbl);
                }
        );
        tblCustomer.setItems(table);
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        customer = CustomerController.getInstance().searchCustomer(inputId.getText());
        if(customer!=null){
            inputName.setText(customer.getName());
            inputEmail.setText(customer.getEmail());
            inputAddress.setText(customer.getAddress());
        }
        else{
            new Alert(Alert.AlertType.WARNING,"No such customer..!").show();
        }
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
        if(customer!=null){
            customer.setName(inputName.getText());
            customer.setEmail(EmailValidator.getInstance().validatedEmail(inputEmail.getText().trim()));
            customer.setAddress(inputAddress.getText());
            boolean b = customerBo.update(customer);
            if(b) {
                new Alert(Alert.AlertType.INFORMATION,"Customer updated..!").show();
            }
            loadCustomerTable();
        }
        clearInputs();
    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent) {
        if(customer!=null){
            boolean b = customerBo.delete(customer);
            if(b) {
                new Alert(Alert.AlertType.INFORMATION,"Customer deleted..!").show();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Customer can not be deleted..!").show();
            }
        }
        loadCustomerTable();
        clearInputs();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadCustomerTable();
    }
}
