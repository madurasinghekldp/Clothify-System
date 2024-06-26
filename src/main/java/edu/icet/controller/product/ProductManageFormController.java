package edu.icet.controller.product;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.ProductBo;
import edu.icet.bo.SupplierBo;
import edu.icet.controller.NavigationFormController;
import edu.icet.controller.customer.CustomerSelectorForm;
import edu.icet.controller.supplier.SupplierController;
import edu.icet.controller.supplier.SupplierSelectorForm;
import edu.icet.dto.Product;
import edu.icet.dto.Supplier;
import edu.icet.dto.User;
import edu.icet.dto.tm.ProductTable;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.BoType;
import edu.icet.util.Category;
import edu.icet.util.DBConnection;
import edu.icet.util.HibernateUtil;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
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

    private FXMLLoader productLoader;

    private ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);

    private SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setLoader(FXMLLoader loader){
        this.productLoader = loader;
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
                (inputSize.getText().trim()=="")?0:Integer.parseInt(inputSize.getText()),
                (inputPrice.getText().trim()=="")?0.0:Double.parseDouble(inputPrice.getText()),
                (inputQuantity.getText().trim()=="")?0:Integer.parseInt(inputQuantity.getText()),
                //SupplierController.getInstance().searchSupplier((String) inputSupplier.getValue()),
                (Supplier) inputSupplier.getValue(),
                (Category) inputCategory.getValue()
        );
        boolean b = productBo.save(product);
        if(b) {
            new Alert(Alert.AlertType.INFORMATION,"Product Added..!").show();
        }
        loadProductTable();
        clearInputs();
    }

    public void btnSearchUserOnAction(ActionEvent actionEvent) {
        product = ProductController.getInstance().searchProduct(inputId.getText());
        if(product!=null){
            inputName.setText(product.getName());
            inputSize.setText(String.valueOf(product.getSize()));
            inputPrice.setText(String.valueOf(product.getPrice()));
            inputQuantity.setText(String.valueOf(product.getQty()));
            inputCategory.setValue(product.getCategory());
            inputSupplier.setValue(product.getSupplier());
        }
        else{
            new Alert(Alert.AlertType.WARNING,"No such product..!").show();
        }
    }

    public void btnUpdateProductOnAction(ActionEvent actionEvent) {
        if(product!=null){
            product.setName(inputName.getText());
            product.setSize(Integer.valueOf(inputSize.getText()));
            product.setPrice(Double.valueOf(inputPrice.getText()));
            product.setQty(Integer.valueOf(inputQuantity.getText()));
            product.setCategory((Category) inputCategory.getValue());
            //product.setSupplier(SupplierController.getInstance().searchSupplier(inputSupplier.getId()));
            product.setSupplier((Supplier) inputSupplier.getValue());
            boolean b = productBo.update(product);
            if(b) {
                new Alert(Alert.AlertType.INFORMATION,"Product updated..!").show();
            }
            loadProductTable();
        }
        clearInputs();
    }

    public void btnDeleteProductOnAction(ActionEvent actionEvent) {
        if(product!=null){
            boolean deleted = productBo.delete(product);
            if(deleted) {
                new Alert(Alert.AlertType.INFORMATION,"Product deleted..!").show();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Product can not be deleted..!").show();
            }
        }
        loadProductTable();
        clearInputs();
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
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        List<Supplier> suppliers = supplierBo.getAll();
        supplierList.addAll(suppliers);
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
                product1 -> {
                    ProductTable ptbl = new ProductTable(
                            product1.getId(),
                            product1.getName(),
                            product1.getSize(),
                            product1.getPrice(),
                            product1.getQty(),
                            product1.getSupplier().getId(),
                            product1.getCategory()
                    );
                    table.add(ptbl);
                }
        );
        tblProduct.setItems(table);
    }

    private void clearInputs(){
        inputId.setText("");
        inputName.setText("");
        inputSize.setText("");
        inputPrice.setText("");
        inputQuantity.setText("");
        inputCategory.setValue(null);
        inputSupplier.setValue(null);
        product = null;
    }

    public void btnSelectSupplierOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supplier-table-selector.fxml"));
            Scene scene = new Scene(loader.load());
            SupplierSelectorForm controller = loader.getController();
            controller.setLoader(productLoader);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnGetSupplierReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/reports/Products.jrxml");
            JasperDesign design = JRXmlLoader.load(inputStream);
            JRDesignQuery designQuery = new JRDesignQuery();
            designQuery.setText("select * from productentity");
            design.setQuery(designQuery);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            // Create a JasperViewer instance and set its properties
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            // Display the report viewer
            viewer.setVisible(true);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
