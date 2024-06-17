package edu.icet.controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.CustomerBo;
import edu.icet.bo.EmployeeBo;
import edu.icet.bo.ProductBo;
import edu.icet.controller.NavigationFormController;
import edu.icet.controller.product.ProductController;
import edu.icet.dto.*;
import edu.icet.dto.tm.CartTable;
import edu.icet.util.BoType;
import edu.icet.util.PaymentType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderManageFormController implements Initializable {
    public JFXTextField inputId;
    public JFXTextField inputDate;
    public JFXComboBox inputEmployee;
    public JFXComboBox inputProduct;
    public JFXTextField inputQty;
    public JFXComboBox inputCustomer;
    public TableView tblCart;
    public TableView tblOrder;
    public TableView tblOrderDetail;
    public TableColumn colProduct;
    public TableColumn colProductName;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colOrder;
    public TableColumn colEmployee;
    public TableColumn colPayment;
    public TableColumn colCustomer;
    public TableColumn colTotal2;
    public TableColumn colDate;
    public TableColumn colOrder2;
    public TableColumn colProduct2;
    public TableColumn colQty2;
    public JFXComboBox inputPaymentType;
    public Label lblQty;
    public Label lblTotal;

    private User user;
    private Stage stage;

    private Order order;
    private Product product;

    private Double total=0.0;

    private ObservableList<CartTable> cartList = FXCollections.observableArrayList();
    private List<Product> updatedProducts = new ArrayList<>();

    private ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    private EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    public void setUser(User user){
        this.user = user;
    }

    public void setStage(Stage stage){
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

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        product = (Product)inputProduct.getValue();
        Integer qty = inputQty.getText().trim()==""?0:Integer.valueOf(inputQty.getText().trim());
        if(product!=null){
            if(qty<=product.getQty()){
                cartList.add(new CartTable(
                        product.getId(),
                        product.getName(),
                        product.getPrice().toString(),
                        inputQty.getText(),
                        String.valueOf(qty*product.getPrice())
                ));
                total += qty*product.getPrice();
                product.setQty(product.getQty()-qty);
                updatedProducts.add(product);
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Do not have enough quantity.").show();
            }
            tblCart.setItems(cartList);
            lblTotal.setText(String.valueOf(total));
        }

        inputProduct.setValue(null);
        inputQty.setText("");
    }

    public void btnSearchOrderOnAction(ActionEvent actionEvent) {
        order = OrderController.getInstance().searchOrder(inputId.getText());
        if(order!=null){
            inputDate.setText(String.valueOf(order.getDate()));
            inputEmployee.setValue(order.getEmployee());
            inputCustomer.setValue(order.getCustomer());

        }
        else{
            new Alert(Alert.AlertType.WARNING,"No such order..!").show();
        }
    }

    public void btnReturnOnAction(ActionEvent actionEvent) {
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadEmployees();
        loadCustomers();
        loadProducts();
        loadPaymentTypes();
        loadDate();

        inputProduct.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->{
            setQuantity((Product)newValue);
        });
    }

    private void setQuantity(Product product) {
        if(product!=null) {
            lblQty.setText(String.valueOf(product.getQty()));
        }
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        inputDate.setText(f.format(date));
    }

    private void loadPaymentTypes() {
        ObservableList<Object> types = FXCollections.observableArrayList();
        types.add(PaymentType.CARD);
        types.add(PaymentType.CASH);
        inputPaymentType.setItems(types);
    }

    private void loadProducts() {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        List<Product> products = productBo.getAll();
        productList.addAll(products);
        inputProduct.setItems(productList);
    }

    private void loadCustomers() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        List<Customer> customers = customerBo.getAll();
        customerList.addAll(customers);
        inputCustomer.setItems(customerList);
    }

    private void loadEmployees() {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        List<Employee> employees = employeeBo.getAll();
        employeeList.addAll(employees);
        inputEmployee.setItems(employeeList);
    }
}
