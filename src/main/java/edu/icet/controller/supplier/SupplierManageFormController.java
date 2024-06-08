package edu.icet.controller.supplier;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.SupplierBo;
import edu.icet.controller.LoginFormController;
import edu.icet.controller.NavigationFormController;
import edu.icet.dto.Supplier;
import edu.icet.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierManageFormController {
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

    private Stage stage;

    private SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void btnGoBackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/navigation-form.fxml"));
            Scene scene = new Scene(loader.load());
            NavigationFormController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSearchSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                inputId.getText(),
                inputName.getText(),
                inputCompany.getText(),
                inputEmail.getText(),
                inputAddress.getText()
        );
        boolean b = supplierBo.save(supplier);
        System.out.println(b);
    }

    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) {
    }
}
