package it.unicam.cs.NeculaRobertGabriel123390.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * Controller for the "No Winner" scene. This class manages the user interactions
 * within the scene that is displayed when the race concludes without a winner.
 * It allows the user to start a new race by restarting the game and loading the
 * initial scene.
 */
public class NoWinnerController {


    @FXML
    private Button startNewRaceButton;



    /**
     * Handles the event when the "Start New Race" button is clicked.
     * It triggers the loading of the initial loading scene to begin the setup for a new race.
     */
    @FXML
    private void restartRace() {
        loadLoadingScene();
    }


    /**
     * Loads the initial loading scene. This method is responsible for transitioning
     * the application from the "No Winner" scene to the "Loading" scene, which begins
     * the process of setting up a new race.
     */
    private void loadLoadingScene() {
        Stage stage = (Stage) this.startNewRaceButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoadingScene.fxml"));
        SceneLoader.loadNewScene(fxmlLoader, stage);
    }

}
