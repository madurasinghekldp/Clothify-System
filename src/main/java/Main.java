import edu.icet.controller.HomeFormController;
import edu.icet.controller.LoginFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/home-form.fxml"));
        Scene scene = new Scene(loader.load());

        HomeFormController controller = loader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }
}