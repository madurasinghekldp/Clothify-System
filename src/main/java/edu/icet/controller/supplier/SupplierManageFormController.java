package edu.icet.controller.supplier;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.SupplierBo;
import edu.icet.controller.LoginFormController;
import edu.icet.controller.NavigationFormController;
import edu.icet.dto.Supplier;
import edu.icet.dto.User;
import edu.icet.dto.tm.SupplierTable;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.BoType;
import edu.icet.util.DBConnection;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.modelmapper.ModelMapper;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierManageFormController implements Initializable {
    public JFXTextField inputId;
    public JFXTextField inputName;
    public JFXTextField inputEmail;
    public JFXTextField inputCompany;
    public JFXTextField inputAddress;
    public TableView tblSupplier;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCompany;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public TableColumn colCreator;

    private Stage stage;

    private User user;

    private Supplier supplier;

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

    public void btnSearchSupplierOnAction(ActionEvent actionEvent) {
        supplier = SupplierController.getInstance().searchSupplier(inputId.getText());
        if(supplier!=null){
            inputName.setText(supplier.getName());
            inputEmail.setText(supplier.getEmail());
            inputCompany.setText(supplier.getCompany());
            inputAddress.setText(supplier.getAddress());
        }
        else{
            new Alert(Alert.AlertType.WARNING,"No such supplier..!").show();
        }
    }

    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
        supplier = new Supplier(
                SupplierController.getInstance().generateSupplierId(),
                inputName.getText(),
                inputCompany.getText(),
                EmailValidator.getInstance().validatedEmail(inputEmail.getText()),
                inputAddress.getText(),
                user
        );
        boolean b = supplierBo.save(supplier);
        if(b) {
            new Alert(Alert.AlertType.INFORMATION,"Supplier Added..!").show();
        }
        loadSupplierTable();
        clearInputs();
    }

    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
        if(supplier!=null){
            supplier.setName(inputName.getText());
            supplier.setEmail(EmailValidator.getInstance().validatedEmail(inputEmail.getText()));
            supplier.setCompany(inputCompany.getText());
            supplier.setAddress(inputAddress.getText());
            supplier.setUser(user);
            boolean b = supplierBo.update(supplier);
            if(b) {
                new Alert(Alert.AlertType.INFORMATION,"Supplier updated..!").show();
            }
            loadSupplierTable();
        }
        clearInputs();
    }

    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) {
        if(supplier!=null){
            boolean deleted = supplierBo.delete(supplier);
            if(deleted) {
                new Alert(Alert.AlertType.INFORMATION,"Supplier deleted..!").show();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Supplier can not be deleted..!").show();
            }
        }
        loadSupplierTable();
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

        loadSupplierTable();
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

    private void clearInputs(){
        inputId.setText("");
        inputName.setText("");
        inputCompany.setText("");
        inputEmail.setText("");
        inputAddress.setText("");
        supplier = null;
    }

    public void btnGetSupplierReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/reports/Suppliers.jrxml");
            JasperDesign design = JRXmlLoader.load(inputStream);
            JRDesignQuery designQuery = new JRDesignQuery();
            designQuery.setText("select * from supplierentity");
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
