package edu.icet.controller.product;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.ProductBo;
import edu.icet.bo.SupplierBo;
import edu.icet.controller.NavigationFormController;
import edu.icet.controller.supplier.SupplierController;
import edu.icet.dto.Product;
import edu.icet.dto.Supplier;
import edu.icet.dto.User;
import edu.icet.dto.tm.ProductTable;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.BoType;
import edu.icet.util.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductManageFormController implements Initializable {
    public JFXTextField inputId;
    public JFXTextField inputName;
    public JFXTextField inputSize;
    public JFXTextField inputPrice;
    public JFXTextField inputQuantity;
    public JFXComboBox inputCategory;
    public JFXComboBox inputSupplier;
    public TableView tblProduct;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colSize;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public TableColumn colCategory;
    public TableColumn colSupplier;

    private Stage stage;

    private User user;

    private Product product;

    private ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);

    private SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(User user){
        this.user = user;
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

    public void btnAddProductOnAction(ActionEvent actionEvent) {
        product = new Product(
                ProductController.getInstance().generateProductId(),
                inputName.getText(),
                Integer.parseInt(inputSize.getText()),
                Double.parseDouble(inputPrice.getText()),
                Integer.parseInt(inputQuantity.getText()),
                SupplierController.getInstance().searchSupplier(inputSupplier.getValue().toString()),
                (Category) inputCategory.getValue()
        );
        System.out.println(product);
        boolean b = productBo.save(product);
        loadProductTable();
    }

    public void btnSearchUserOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateProductOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteProductOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        loadProductTable();
        loadCategories();
        loadSuppliers();
    }

    private void loadSuppliers() {
        ObservableList<String> supplierList = FXCollections.observableArrayList();
        List<Supplier> suppliers = supplierBo.getAll();
        suppliers.forEach(
                supplier -> {
                    supplierList.add(supplier.getId());
                }
        );
        inputSupplier.setItems(supplierList);
    }

    private void loadCategories() {
        ObservableList<Object> types = FXCollections.observableArrayList();
        types.add(Category.GENTS);
        types.add(Category.LADIES);
        types.add(Category.KIDS);
        inputCategory.setItems(types);
    }

    private void loadProductTable() {
        ObservableList<ProductTable> table = FXCollections.observableArrayList();
        List<Product> all = productBo.getAll();
        all.forEach(
                product -> {
                    ProductTable ptbl = new ProductTable(
                            product.getId(),
                            product.getName(),
                            product.getSize(),
                            product.getPrice(),
                            product.getQty(),
                            product.getSupplier().getId(),
                            product.getCategory()
                    );
                    table.add(ptbl);
                }
        );
        tblProduct.setItems(table);
    }
}
