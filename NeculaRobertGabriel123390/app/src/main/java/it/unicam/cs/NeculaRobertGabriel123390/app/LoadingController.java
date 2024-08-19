package it.unicam.cs.NeculaRobertGabriel123390.app;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.LoadingLogger;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.RaceSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParser;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParserFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandlerFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.Race;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.RaceValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.RaceHandlerConstants;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller for the loading scene of the racing application.
 * Handles file selection, parsing, and setting up the race based on user-defined rules.
 */
public class LoadingController {


    /**
     * Default width of the scene.
     */
    public static final int WIDTH = 1400;


    /**
     * Default height of the scene.
     */
    public static final int HEIGHT = 800;


    /**
     * The ChoiceBox with the collision handling option.
     */
    @FXML
    private ChoiceBox<String> allowCollisionsChoiceBox;


    /**
     * The ChoiceBox with the crash handling option.
     */
    @FXML
    private ChoiceBox<String> onCrashChoiceBox;


    /**
     * The ChoiceBox with the win handling option.
     */
    @FXML
    private ChoiceBox<String> winConditionChoiceBox;


    /**
     * The area where errors in the file will be logged.
     */
    @FXML
    public TextArea loadingLogArea;


    private Race race;


    private ParsedData<?> parsedData;


    private boolean areHandlersInitialized = false;


    public void initialize(){
        if(!LoadingLogger.isInitialized)
            LoadingLogger.initialize(this.loadingLogArea);
    }


    /**
     * Handles the file selection event and attempts to parse the chosen file
     * to set up the race.
     *
     * @param event The event generated when clicking the button.
     * @throws NullPointerException If the event is null
     */
    @FXML
    private void onOpenFile(Event event) {
        if(event == null)
            throw new NullPointerException("Event is null");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Txt Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if(selectedFile == null)
            LoadingLogger.logErrorAndThrow("No file has been received", NullPointerException.class);

        initializeHandlersData();
        tryParseFile(selectedFile);

    }


    /**
     * Starts the race setup based on the parsed data and user-defined rules.
     * Loads the race scene if the race setup is valid.
     */
    @FXML
    private void onStartRace() {
        RaceSetup raceSetup = new RaceSetup(this.parsedData, extractHandlers());
        this.race = raceSetup.setup();

        RaceValidator.validate(this.race);
        loadRaceScene();
    }


    /**
     * Parses and validates the supplied file to set up a new race.
     * @throws NullPointerException If the supplied file is null
     * @param file The file to be parsed.
     */
    private void tryParseFile(File file) {

        FileParser fileParser = FileParserFactory.getParser(file);
        this.parsedData = fileParser.parseFile(file);

        LoadingLogger.log("Successfully parsed the file.");
    }


    /**
     * Initializes the available options in the choice boxes for race configuration.
     */
    private void initializeHandlersData() {

        if(this.areHandlersInitialized) return;


        this.allowCollisionsChoiceBox.getItems().addAll(RaceHandlerConstants.COLLISION_ALLOWED, RaceHandlerConstants.COLLISION_NOT_ALLOWED);
        this.onCrashChoiceBox.getItems().addAll(RaceHandlerConstants.CRASH_LEAVE_RACE, RaceHandlerConstants.CRASH_CONTINUE_WITH_PENALTY);
        this.winConditionChoiceBox.getItems().addAll(RaceHandlerConstants.WIN_FIRST_CROSSING_LINE);

        this.allowCollisionsChoiceBox.setValue(RaceHandlerConstants.COLLISION_NOT_ALLOWED);
        this.onCrashChoiceBox.setValue(RaceHandlerConstants.CRASH_LEAVE_RACE);
        this.winConditionChoiceBox.setValue(RaceHandlerConstants.WIN_FIRST_CROSSING_LINE);

        this.areHandlersInitialized = true;
    }


    /**
     * Creates a list of race handlers based on the selected rules.
     * The order of handlers is important:
     * 1. Win handler
     * 2. Crash handlerCollision handler
     * 3. Collision handler
     * 4. Any additional handlers
     * @return A list of race handlers.
     */
    private List<RaceHandler> extractHandlers() {
        List<RaceHandler> handlers = new ArrayList<>();
        handlers.add(RaceHandlerFactory.getWinHandler(this.winConditionChoiceBox.getValue()));
        handlers.add(RaceHandlerFactory.getCrashHandler(this.onCrashChoiceBox.getValue()));
        handlers.add(RaceHandlerFactory.getCollisionHandler(this.allowCollisionsChoiceBox.getValue()));
        return handlers;
    }


    /**
     * Loads the race scene when the start button is pressed.
     */
    private void loadRaceScene() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RaceScene.fxml"));
        RaceController raceController = new RaceController(this.race);
        fxmlLoader.setController(raceController);
        Stage stage = (Stage) this.onCrashChoiceBox.getScene().getWindow();

        SceneLoader.loadNewScene(fxmlLoader, stage);
        raceController.build();
    }

}
