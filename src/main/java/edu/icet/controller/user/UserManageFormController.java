package edu.icet.controller.user;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.UserBo;
import edu.icet.controller.NavigationFormController;
import edu.icet.dto.User;
import edu.icet.dto.tm.UserTable;
import edu.icet.util.BoType;
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

public class UserManageFormController implements Initializable{
    public JFXTextField inputId;
    public JFXTextField inputFirstName;
    public JFXTextField inputLastName;
    public DatePicker inputDOB;
    public JFXTextField inputAddress;
    public JFXTextField inputType;
    public JFXTextField inputEmail;
    public TableView tblUser;
    public TableColumn colId;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colDOB;
    public TableColumn colType;
    public TableColumn colEmail;
    public TableColumn colAddress;

    private Stage stage;

    private User user;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

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

    public void btnSearchUserOnAction(ActionEvent actionEvent) {
        user = UserController.getInstance().searchUser(inputId.getText());
        if(user!=null){
            inputFirstName.setText(user.getFirstName());
            inputLastName.setText(user.getLastName());
            inputDOB.setValue(user.getDob());
            inputType.setText(user.getType().toString());
            inputEmail.setText(user.getEmail());
            inputAddress.setText(user.getAddress());
        }
        else{
            new Alert(Alert.AlertType.WARNING,"No such user..!").show();
        }

    }

    public void btnAddUserOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateUserOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteUserOnAction(ActionEvent actionEvent) {
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
}
