package edu.icet.controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.*;
import edu.icet.controller.NavigationFormController;
import edu.icet.controller.customer.CustomerSelectorForm;
import edu.icet.controller.employee.EmployeeSelectorForm;
import edu.icet.controller.product.ProductSelectorForm;
import edu.icet.dto.*;
import edu.icet.dto.tm.CartTable;
import edu.icet.dto.tm.OrderDetailTable;
import edu.icet.dto.tm.OrderTable;
import edu.icet.util.BoType;
import edu.icet.util.DBConnection;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private FXMLLoader orderLoader;

    private ObservableList<CartTable> cartList = FXCollections.observableArrayList();
    private List<Product> updatedProducts = new ArrayList<>();

    private List<OrderDetail> orderDetailList = new ArrayList<>();

    private ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    private EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    private OrderBo orderBo = BoFactory.getInstance().getBo(BoType.ORDER);
    private OrderDetailBo orderDetailBo = BoFactory.getInstance().getBo(BoType.ORDERDETAIL);

    public void setUser(User user){
        this.user = user;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setLoader(FXMLLoader loader){
        this.orderLoader = loader;
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
            if(qty <= product.getQty()){
                if(!isProductExist(product)){
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
                    orderDetailList.add(new OrderDetail(null,product,qty));
                }
                else{
                    new Alert(Alert.AlertType.WARNING,"Product is already added.").show();
                }
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

    private boolean isProductExist(Product product) {
        for(CartTable item: cartList){
            if(item.getProductId().equals(product.getId())){
                return true;
            }
        }
        return false;
    }

    public void btnSearchOrderOnAction(ActionEvent actionEvent) {
        order = OrderController.getInstance().searchOrder(inputId.getText());

        if(order!=null){
            inputDate.setText(String.valueOf(order.getDate()));
            inputEmployee.setValue(order.getEmployee());
            inputCustomer.setValue(order.getCustomer());
            orderDetailList = orderDetailBo.getByOrder(order.getId());
        }
        else{
            new Alert(Alert.AlertType.WARNING,"No such order..!").show();
        }
    }

    public void btnReturnOnAction(ActionEvent actionEvent) {
        if(order!=null){
            orderDetailList.forEach(
                    orderDetail ->{
                        Product product = orderDetail.getProduct();
                        Integer qty = orderDetail.getQty();
                        product.setQty(product.getQty()+qty);
                        orderDetailBo.delete(orderDetail);
                        productBo.update(product);
                    }
            );
            boolean deleted = orderBo.delete(order);
            if(deleted){
                new Alert(Alert.AlertType.INFORMATION,"Order deleted..!").show();
                order = null;
                orderDetailList.clear();
                loadOrderTable();
                loadOrderDetailTable();
                loadProducts();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Order can not be deleted..!").show();
            }
        }
        inputCustomer.setValue(null);
        inputEmployee.setValue(null);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            order = new Order(
                    OrderController.getInstance().generateOrderId(),
                    (Employee) inputEmployee.getValue(),
                    (Customer) inputCustomer.getValue(),
                    Double.valueOf(lblTotal.getText()),
                    (PaymentType) inputPaymentType.getValue(),
                    f.parse(inputDate.getText())
            );
            boolean b = orderBo.save(order);
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Order Added..!").show();
                updateProducts(updatedProducts);
                addOrderDetails(order);
                updatedProducts.clear();
                orderDetailList.clear();
                generateBill();
                order = null;
                cartList.clear();
                tblCart.setItems(cartList);
                loadOrderTable();
                loadOrderDetailTable();
                loadProducts();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateBill() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/reports/CustomerBill.jrxml");
            JasperDesign design = JRXmlLoader.load(inputStream);
            JRDesignQuery designQuery = new JRDesignQuery();
            designQuery.setText("select orderentity.id,\n" +
                    "customerentity.name,\n" +
                    "customerentity.email,\n" +
                    "orderentity.total,\n" +
                    "orderdetailentity.qty,\n" +
                    "orderdetailentity.productId,\n" +
                    "productentity.price\n" +
                    "from customerentity inner join orderentity inner join orderdetailentity inner join\n" +
                    "productentity\n" +
                    "on customerentity.id = orderentity.customerId and \n" +
                    "orderentity.id = orderdetailentity.orderId and\n" +
                    "orderdetailentity.productId = productentity.id where orderentity.id = $P{id}");
            design.setQuery(designQuery);

            JRDesignParameter parameter = new JRDesignParameter();
            parameter.setName("id");
            parameter.setValueClass(String.class);
            design.addParameter(parameter);

            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", order.getId());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DBConnection.getInstance().getConnection());
            // Create a JasperViewer instance and set its properties
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            // Display the report viewer
            viewer.setVisible(true);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateProducts(List<Product> updatedProducts) {
        updatedProducts.forEach(
                updated -> {
                    productBo.update(updated);
                }
        );
    }

    private void addOrderDetails(Order order) {
        orderDetailList.forEach(
                orderDetail -> {
                    orderDetail.setOrder(order);
                    orderDetailBo.save(orderDetail);
                }
        );
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        cartList.clear();
        tblCart.setItems(cartList);
        loadProducts();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        colOrder.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployee.setCellValueFactory(new PropertyValueFactory<>("employee"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        colTotal2.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        colOrder2.setCellValueFactory(new PropertyValueFactory<>("order"));
        colProduct2.setCellValueFactory(new PropertyValueFactory<>("product"));
        colQty2.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadEmployees();
        loadCustomers();
        loadProducts();
        loadPaymentTypes();
        loadDate();
        loadOrderTable();
        loadOrderDetailTable();

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

    private void loadOrderTable(){
        ObservableList<OrderTable> table = FXCollections.observableArrayList();
        List<Order> all = orderBo.getAll();
        all.forEach(
                order1 -> {
                    OrderTable otbl = new OrderTable(
                            order1.getId(),
                            order1.getEmployee().getId(),
                            order1.getCustomer().getId(),
                            String.valueOf(order1.getTotal()),
                            String.valueOf(order1.getPaymentType()),
                            String.valueOf(order1.getDate())
                    );
                    table.add(otbl);
                }
        );
        tblOrder.setItems(table);
    }

    private void loadOrderDetailTable(){
        ObservableList<OrderDetailTable> table = FXCollections.observableArrayList();
        List<OrderDetail> all = orderDetailBo.getAll();
        all.forEach(
                orderDetail -> {
                    OrderDetailTable odtbl = new OrderDetailTable(
                            orderDetail.getOrder().getId(),
                            orderDetail.getProduct().getId(),
                            String.valueOf(orderDetail.getQty())
                    );
                    table.add(odtbl);
                }
        );
        tblOrderDetail.setItems(table);
    }

    public void btnSelectProductOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/product-table-selector.fxml"));
            Scene scene = new Scene(loader.load());
            ProductSelectorForm controller = loader.getController();
            controller.setLoader(orderLoader);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSelectCustomerOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer-table-selector.fxml"));
            Scene scene = new Scene(loader.load());
            CustomerSelectorForm controller = loader.getController();
            controller.setLoader(orderLoader);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSelectEmployeeOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employee-table-selector.fxml"));
            Scene scene = new Scene(loader.load());
            EmployeeSelectorForm controller = loader.getController();
            controller.setLoader(orderLoader);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
