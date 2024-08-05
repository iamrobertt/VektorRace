package it.unicam.cs.NeculaRobertGabriel123390.app;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.RaceSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParser;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParserFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.MoveHandlerFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Race;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadingController {

    public static int WIDTH = 1400;
    public static int HEIGHT = 800;


    @FXML
    private Text numBotsText;


    @FXML
    private Text numHumansText;


    @FXML
    private ChoiceBox<String> allowCollisionsChoiceBox;


    @FXML
    private ChoiceBox<String> onCrashChoiceBox;


    @FXML
    private TextArea loadingLogArea;


    private Race race;


    private Circuit circuit;


    private List<Player> players;


    private List<RaceHandler> handlers;


    private ParsedFileData parsedData;


    /**
     * Method that takes the file chose by the user and try to parse it to set up a race
     * @param event - The event generated when clicking the button
     */
    @FXML
    private void onOpenFile(Event event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Txt Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if(selectedFile == null) throw new FileNotFoundException("The file configuration is not valid for a race");

        initializeRulesData();
        tryParseFile(selectedFile);
        updatePlayersData();

    }


    /**
     * Method called when start button is pressed
     */
    @FXML
    private void onStartRace() {
        RaceSetup raceSetup = new RaceSetup(this.parsedData, extractHandlers());
        this.race = raceSetup.setup();
        if(isRaceValid())
            loadRaceScene();
    }


    /**
     * Method that parses and validates the data supplied and sets up a new race
     * @param file - The supplied file
     */
    public void tryParseFile(File file) {

        FileParser fileParser = FileParserFactory.getParser(file);
        this.parsedData = fileParser.parseFile(file);
        FileValidator fileValidator = fileParser.getFileValidator();

        validateFile(this.parsedData, fileValidator);
        logToTextArea("Successfully parsed the file.");
    }


    /**
     * Method that writes in the choice boxes all the options currently available
     */
    private void initializeRulesData() {
        this.allowCollisionsChoiceBox.getItems().addAll("true", "false");
        this.onCrashChoiceBox.getItems().addAll("leave_race", "continue_with_penalty");
    }


    /**
     * Method that creates the handlers based on rules value.
     * !!IMPORTANT: the order is essential
     * First handler is a crash handler, second one is a collision handler, the next ones do not need to be in order
     * @return List<RaceHandler> the list of the handlers
     */
    private List<RaceHandler> extractHandlers() {
        List<RaceHandler> handlers = new ArrayList<>();
        handlers.add(MoveHandlerFactory.getCrashHandler(this.onCrashChoiceBox.getValue()));
        handlers.add(MoveHandlerFactory.getCollisionHandler(this.allowCollisionsChoiceBox.getValue()));

        return handlers;
    }


    /**
     * Function that loads the race scene when start button is pressed
     */
    private void loadRaceScene() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RaceScene.fxml"));

        RaceController raceView = new RaceController(this.race);
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


    /**
     * Method that uses a validator to validate the parsed data
     * @param parsedData - The data parsed from the file
     * @param fileValidator - The validator for that specific file type
     */
    private void validateFile(ParsedFileData parsedData, FileValidator fileValidator) {
        if (!fileValidator.isFileValid(parsedData)) throw new IllegalArgumentException("File not valid for a race");
    }


    /**
     * Method that is called whenever the file is correctly loaded, displaying all player's data
     */
    private void updatePlayersData() {
        this.numBotsText.setText(String.valueOf(this.race.getNumberOfBotPlayers()));
        this.numHumansText.setText(String.valueOf(this.race.getNumberOfHumanPlayers()));
    }


    public boolean isRaceValid() {return this.race != null;}


    private void logToTextArea(String text) {this.loadingLogArea.setText(text + "\n");}


}
