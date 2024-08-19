package it.unicam.cs.NeculaRobertGabriel123390.app;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoadingScene.fxml")));
        stage.setTitle("Formula 1");
        stage.setScene(new Scene(root, LoadingController.WIDTH, LoadingController.HEIGHT));
        stage.setResizable(false);
        stage.show();
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}
