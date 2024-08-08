package garden;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("bee_simulator.fxml"));
        primaryStage.setTitle("Bee Runner 2049");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }
}
