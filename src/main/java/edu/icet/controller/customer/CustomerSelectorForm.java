package edu.icet.controller.customer;

import edu.icet.bo.BoFactory;
import edu.icet.bo.CustomerBo;
import edu.icet.controller.order.OrderManageFormController;
import edu.icet.dto.Customer;
import edu.icet.dto.tm.CustomerTable;
import edu.icet.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.modelmapper.ModelMapper;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerSelectorForm implements Initializable {
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colAddress;

    private FXMLLoader loader;

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void setLoader(FXMLLoader loader){
        this.loader = loader;
    }

    public void btnSelectCustomerOnAction(ActionEvent actionEvent) {
        Object selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        CustomerTable item = new ModelMapper().map(selectedItem,CustomerTable.class);

        OrderManageFormController controller = loader.getController();
        controller.inputCustomer.setValue(CustomerController.getInstance().searchCustomer(item.getId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadCustomerTable();
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
}
