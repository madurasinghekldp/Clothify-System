package edu.icet.controller.product;

import edu.icet.bo.BoFactory;
import edu.icet.bo.ProductBo;
import edu.icet.controller.NavigationFormController;
import edu.icet.controller.order.OrderManageFormController;
import edu.icet.dto.Product;
import edu.icet.dto.tm.ProductTable;
import edu.icet.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductSelectorForm implements Initializable {
    public TableView tblProduct;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colSize;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public TableColumn colCategory;

    private FXMLLoader loader;

    public void setLoader(FXMLLoader loader){
        this.loader = loader;
    }

    private ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        loadProductTable();
        
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

    public void btnSelectProductOnAction(ActionEvent actionEvent) {
        Object selectedItem = tblProduct.getSelectionModel().getSelectedItem();
        ProductTable item = new ModelMapper().map(selectedItem,ProductTable.class);

        OrderManageFormController controller = loader.getController();
        controller.inputProduct.setValue(ProductController.getInstance().searchProduct(item.getId()));
    }
}
