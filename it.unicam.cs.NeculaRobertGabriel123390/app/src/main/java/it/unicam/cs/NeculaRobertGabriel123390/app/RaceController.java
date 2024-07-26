package it.unicam.cs.NeculaRobertGabriel123390.app;

import it.unicam.cs.NeculaRobertGabriel123390.api.RaceManager;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerPossibleMoves;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.Race;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.scenebuilder.CircuitSceneBuilder;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.scenebuilder.PlayersSceneBuilder;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class RaceController {


    @FXML
    private Pane circuitGrid;


    @FXML
    private GridPane playersGrid;


    @FXML
    private Text currentPlayerPlayingText;


    @FXML
    private GridPane inputMovesGrid;


    private MoveGrid moveGrid;


    private final Race race;


    private RaceManager raceManager;



    public RaceController(Race race) {
        validateRace(race);
        this.race = race;

    }


    /**
     * Method that validates a race
     * @param race - The race
     */
    private void validateRace(Race race) {
        if(race == null) throw new NullPointerException("race is null");
        if(race.players() == null) throw new NullPointerException("players is null");
        if(race.circuit() == null) throw new NullPointerException("Circuit is null");
    }


    /**
     * Method that takes the provided data for the race and builds the entire scene
     */
    public void build() {

        CircuitSceneBuilder circuitSceneBuilder = new CircuitSceneBuilder(this.race.circuit(), this.circuitGrid);
        PlayersSceneBuilder playersSceneBuilder = new PlayersSceneBuilder(this.race.players(), this.playersGrid);
        initializeMoveGrid();

        circuitSceneBuilder.build();
        playersSceneBuilder.build();


        this.raceManager = new RaceManager(circuitSceneBuilder.getMappedCircuit(),
                this.race.circuit().getStartLine(),
                this.race.circuit().getEndLine(),
                playersSceneBuilder.getPlayers());

        displayCurrentPlayer();
    }


    /**
     * Method called when a button is pressed that takes its data and pass it to the race manager, that will handle correctly the move
     * @param event - The button that triggered the action event
     */
    @FXML
    public void handlePlayerMove(Event event){
        Button button = (Button) event.getSource();
        Position buttonPosition = (Position) button.getUserData();
        this.raceManager.handlePlayerMove(buttonPosition);

        PlayerPossibleMoves nextPlayerMoves = this.raceManager.getNextPlayerPossibleMoves();
        this.moveGrid.updateGrid(nextPlayerMoves);
        displayCurrentPlayer();
    }


    /**
     * Method that takes from the race manager the current player and displays it
     */
    private void displayCurrentPlayer(){
        String currentPlayerName = this.raceManager.getCurrentPlayerName();
        this.currentPlayerPlayingText.setText(currentPlayerName);
    }


    /**
     * Method that initialize a new MoveGrid
     */
    private void initializeMoveGrid(){
        this.moveGrid = new MoveGrid(this.inputMovesGrid, new PlayerPossibleMoves());
        this.moveGrid.setupGrid();
    }

}
