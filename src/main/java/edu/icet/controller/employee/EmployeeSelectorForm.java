package edu.icet.controller.employee;

import edu.icet.bo.BoFactory;
import edu.icet.bo.EmployeeBo;
import edu.icet.controller.order.OrderManageFormController;
import edu.icet.controller.product.ProductController;
import edu.icet.dto.Employee;
import edu.icet.dto.tm.EmployeeTable;
import edu.icet.dto.tm.ProductTable;
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

public class EmployeeSelectorForm implements Initializable {
    public TableView tblEmployee;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCompany;
    public TableColumn colEmail;
    public TableColumn colAddress;

    private FXMLLoader loader;

    private EmployeeBo empBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    public void setLoader(FXMLLoader loader){
        this.loader = loader;
    }

    public void btnSelectEmployeeOnAction(ActionEvent actionEvent) {
        Object selectedItem = tblEmployee.getSelectionModel().getSelectedItem();
        EmployeeTable item = new ModelMapper().map(selectedItem,EmployeeTable.class);

        OrderManageFormController controller = loader.getController();
        controller.inputEmployee.setValue(EmployeeController.getInstance().searchEmployee(item.getId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadEmployeeTable();
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
}
