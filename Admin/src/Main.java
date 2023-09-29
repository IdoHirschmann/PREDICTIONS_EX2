import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainPage.MainPageController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Predictions");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainPage/MainPage.fxml"));
        Parent root = loader.load();
        MainPageController mainPageController = loader.getController();
        Scene scene = new Scene(root, 1350, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
