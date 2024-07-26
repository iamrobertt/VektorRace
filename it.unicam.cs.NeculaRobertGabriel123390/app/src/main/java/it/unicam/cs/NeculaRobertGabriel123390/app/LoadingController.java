package it.unicam.cs.NeculaRobertGabriel123390.app;

import it.unicam.cs.NeculaRobertGabriel123390.api.LoadingDataController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class LoadingController {

    public static int WIDTH = 1400;
    public static int HEIGHT = 800;

    @FXML
    private Text numBotsText;


    @FXML
    private Text numHumansText;


    private final LoadingDataController controller = new LoadingDataController();


    /**
     * Method that takes the file chose by the user and try to parse it to set up a race
     * @param event - The event generated when clicking the button
     */
    @FXML
    private void onOpenFile(Event event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Txt Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            try {
                this.controller.initializeRaceData(selectedFile);
                updatePlayersData();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("The file configuration is not valid for a race");
            }
        }
    }


    /**
     * Method called when start button is pressed
     */
    @FXML
    private void onStartRace() {
        if(this.controller.isRaceValid()) loadRaceScene();
    }


    /**
     * Method that is called whenever the file is correctly loaded, displaying all player's data
     */
    private void updatePlayersData() {
        this.numBotsText.setText(String.valueOf(this.controller.getNumBotPlayers()));
        this.numHumansText.setText(String.valueOf(this.controller.getNumHumanPlayers()));
    }


    /**
     * Function that loads the race scene when start button is pressed
     */
    private void loadRaceScene() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RaceScene.fxml"));

        RaceController raceView = new RaceController(this.controller.getRace());
        loader.setController(raceView);

        Stage stage = (Stage) this.numBotsText.getScene().getWindow();
        Pane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //the race scene is loaded
        stage.setScene(new Scene(root, LoadingController.WIDTH, LoadingController.HEIGHT));
        stage.show();
        raceView.build();
    }
}
