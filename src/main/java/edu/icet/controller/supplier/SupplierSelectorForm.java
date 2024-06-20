package edu.icet.controller.supplier;

import edu.icet.bo.BoFactory;
import edu.icet.bo.SupplierBo;
import edu.icet.controller.employee.EmployeeController;
import edu.icet.controller.order.OrderManageFormController;
import edu.icet.controller.product.ProductManageFormController;
import edu.icet.dto.Supplier;
import edu.icet.dto.tm.EmployeeTable;
import edu.icet.dto.tm.SupplierTable;
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

public class SupplierSelectorForm implements Initializable {
    public TableView tblSupplier;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCompany;
    public TableColumn colEmail;
    public TableColumn colAddress;

    private FXMLLoader loader;

    private SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

    public void setLoader(FXMLLoader loader){
        this.loader = loader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadSupplierTable();
    }

    public void btnSelectSupplierOnAction(ActionEvent actionEvent) {
        Object selectedItem = tblSupplier.getSelectionModel().getSelectedItem();
        SupplierTable item = new ModelMapper().map(selectedItem,SupplierTable.class);

        ProductManageFormController controller = loader.getController();
        controller.inputSupplier.setValue(SupplierController.getInstance().searchSupplier(item.getId()));
    }

    private void loadSupplierTable() {
        ObservableList<SupplierTable> table = FXCollections.observableArrayList();
        List<Supplier> all = supplierBo.getAll();
        all.forEach(
                supplier -> {
                    SupplierTable stbl = new SupplierTable(
                            supplier.getId(),
                            supplier.getName(),
                            supplier.getCompany(),
                            supplier.getEmail(),
                            supplier.getAddress(),
                            supplier.getUser().getId()
                    );
                    table.add(stbl);
                }
        );
        tblSupplier.setItems(table);
    }
}
