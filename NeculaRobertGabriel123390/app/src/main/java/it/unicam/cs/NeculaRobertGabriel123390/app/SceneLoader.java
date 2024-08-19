package it.unicam.cs.NeculaRobertGabriel123390.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility class responsible for loading and transitioning between scenes in the application.
 * It provides a method to load a new scene given an {@link FXMLLoader} and the current {@link Stage}.
 * This class centralizes the logic for scene loading, simplifying the process of transitioning
 * between different parts of the application.
 */
public final class SceneLoader {


    /**
     * Loads a new scene from the given {@link FXMLLoader} and sets it to the provided {@link Stage}.
     * This method is typically used when transitioning between different scenes in the application,
     * such as from the race scene to a win or loading scene.
     *
     * @param loader the {@link FXMLLoader} used to load the new scene's FXML file
     * @param stage  the current {@link Stage} on which the new scene will be set
     * @throws RuntimeException if an {@link IOException} occurs while loading the FXML
     */
    public static void loadNewScene(FXMLLoader loader, Stage stage){
        Pane root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //the race scene is loaded
        stage.setScene(new Scene(root, LoadingController.WIDTH, LoadingController.HEIGHT));
        stage.show();
    }
}
