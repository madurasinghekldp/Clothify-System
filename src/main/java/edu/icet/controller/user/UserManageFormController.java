package edu.icet.controller.user;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.UserBo;
import edu.icet.controller.NavigationFormController;
import edu.icet.dto.User;
import edu.icet.dto.tm.UserTable;
import edu.icet.util.BoType;
import edu.icet.util.EmailValidator;
import edu.icet.util.Encryptor;
import edu.icet.util.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManageFormController implements Initializable{
    public JFXTextField inputId;
    public JFXTextField inputFirstName;
    public JFXTextField inputLastName;
    public DatePicker inputDOB;
    public JFXTextField inputAddress;
    public JFXTextField inputEmail;
    public TableView tblUser;
    public TableColumn colId;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colDOB;
    public TableColumn colType;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public JFXComboBox inputType;

    private Stage stage;

    private User user;

    private User opUser=null;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

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

    public void btnSearchUserOnAction(ActionEvent actionEvent) {
        opUser = UserController.getInstance().searchUser(inputId.getText());
        if(opUser!=null){
            inputFirstName.setText(opUser.getFirstName());
            inputLastName.setText(opUser.getLastName());
            inputDOB.setValue(opUser.getDob());
            inputType.setValue(opUser.getType());
            inputEmail.setText(opUser.getEmail());
            inputAddress.setText(opUser.getAddress());
        }
        else{
            new Alert(Alert.AlertType.WARNING,"No such user..!").show();
        }

    }

    public void btnAddUserOnAction(ActionEvent actionEvent) {
        opUser = new User(
                UserController.getInstance().generateUserId(),
                inputFirstName.getText(),
                inputLastName.getText(),
                inputDOB.getValue(),
                inputAddress.getText(),
                (UserType)(inputType.getValue()),
                EmailValidator.getInstance().validatedEmail(inputEmail.getText().trim()),
                new Encryptor().getEncryptedPassword("x230y5d4h100ks8z")
        );
        if(userBo.getUser(inputEmail.getText())!=null){
            new Alert(Alert.AlertType.WARNING,"User already exists..!").show();
        }
        else{
            boolean saved = userBo.save(opUser);
            if(saved) new Alert(Alert.AlertType.INFORMATION,"New user saved..!").show();
            loadUserTable();
        }
        clearInputs();
    }

    public void btnUpdateUserOnAction(ActionEvent actionEvent) {
        if(opUser!=null){
            opUser.setFirstName(inputFirstName.getText());
            opUser.setLastName(inputLastName.getText());
            opUser.setDob(inputDOB.getValue());
            opUser.setType((UserType) inputType.getValue());
            opUser.setAddress(inputAddress.getText());
            opUser.setEmail(EmailValidator.getInstance().validatedEmail(inputEmail.getText().trim()));
            boolean updated = userBo.update(opUser);
            if(updated) new Alert(Alert.AlertType.INFORMATION,"User updated..!").show();
            loadUserTable();
        }
        clearInputs();
    }

    public void btnDeleteUserOnAction(ActionEvent actionEvent) {
        if(opUser!=null){
            boolean deleted = userBo.delete(opUser);
            if(deleted) {
                new Alert(Alert.AlertType.INFORMATION,"User deleted..!").show();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"User can not be deleted..!").show();
            }
            loadUserTable();
        }
        clearInputs();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadUserTable();

        loadUserTypes();
    }

    private void loadUserTypes() {
        ObservableList<Object> types = FXCollections.observableArrayList();
        types.add(UserType.USER);
        types.add(UserType.ADMIN);
        inputType.setItems(types);
    }

    private void loadUserTable() {
        ObservableList<UserTable> table = FXCollections.observableArrayList();
        List<User> all = userBo.getAll();
        all.forEach(
                user -> {
                    UserTable utbl = new UserTable(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getDob(),
                            user.getType(),
                            user.getEmail(),
                            user.getAddress()
                    );
                    table.add(utbl);
                }
        );
        tblUser.setItems(table);
    }

    private void clearInputs(){
        inputId.setText("");
        inputFirstName.setText("");
        inputLastName.setText("");
        inputEmail.setText("");
        inputAddress.setText("");
        inputDOB.setValue(null);
        inputType.setValue(null);
        opUser = null;
    }

}
