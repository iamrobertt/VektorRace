package it.unicam.cs.NeculaRobertGabriel123390.app;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;


public class RaceController {


    @FXML
    private Pane circuitGrid;


    @FXML
    private GridPane playersGrid;


    @FXML
    private Text currentPlayerPlayingText;


    @FXML
    private GridPane inputMovesGrid;


    @FXML
    private TextFlow logArea;


    private MovesGrid movesGrid;


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

        this.raceManager = new RaceManager(this.race);
        this.raceManager.setEventLogger(this::logRaceEvent);
        drawInitialPlayerPosition();
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
        MoveResult moveResult = this.raceManager.onPlayerMove(buttonPosition);

        switch (moveResult.moveType()) {
            case MoveResultType.crash, MoveResultType.success -> {
                updateView(moveResult);
                updateTurn();
            }
            case MoveResultType.win -> handleWin(moveResult);
            case MoveResultType.collision -> updateTurn();
        }

    }


    /**
     * Method that displays the win scene with player that has won
     * @param moveResult - The result of the move to update the last time the track
     */
    private void handleWin(MoveResult moveResult) {
        updateView(moveResult);
        //todo
    }


    /**
     * Method that updates the view with the new data, drawing the line that connects the previous position to the new position and a circle representing
     * current player position
     * @param moveResult - the updated data of the player
     */
    private void updateView(MoveResult moveResult) {
        createPlayerCircle(moveResult.player().getPosition(), moveResult.player().getColor());
        drawLine(moveResult.prevPosition(), moveResult.player().getPosition(), moveResult.player().getColor());
        displayCurrentPlayer();
    }


    /**
     * Method that updates the grid data for the next player
     */
    private void updateTurn() {
        PlayerMoves nextPlayerMoves = this.raceManager.getNextPlayerPossibleMoves();
        this.movesGrid.updateGrid(nextPlayerMoves);
    }


    /**
     * Method that takes the initial position from the model and creates colored circles representing players
     */
    private void drawInitialPlayerPosition() {

        List<Position> initialPlayerPositions = this.raceManager.initializePlayersPosition();

        for(int i = 0; i < this.race.players().size(); i++) {
            Position playerPosition = initialPlayerPositions.get(i);
            Color playerColor = this.race.players().get(i).getColor();
            createPlayerCircle(playerPosition, playerColor);
        }

    }


    /**
     * Method that creates a single circle representing a player
     * @param playerPosition - The position of the player
     * @param playerColor - The color of the player
     */
    private void createPlayerCircle(Position playerPosition, Color playerColor){
        Circle playerPoint = new Circle();
        Position scaledToDrawPlayerPosition = PositionUtils.scalePositionToDraw(playerPosition);
        playerPoint.setCenterX(scaledToDrawPlayerPosition.getX());
        playerPoint.setCenterY(scaledToDrawPlayerPosition.getY());
        playerPoint.setRadius(4);
        playerPoint.setFill(playerColor);
        this.circuitGrid.getChildren().add(playerPoint);
    }


    /**
     * Method that takes from the race manager the current player and displays it
     */
    private void displayCurrentPlayer(){
        String currentPlayerName = this.raceManager.getCurrentPlayer().getName();
        this.currentPlayerPlayingText.setText(currentPlayerName);
    }


    /**
     * Method that initialize a new MoveGrid
     */
    private void initializeMoveGrid(){
        this.movesGrid = new MovesGrid(this.inputMovesGrid, new PlayerMoves());
        this.movesGrid.setupGrid();
    }


    /**
     *  Method that draws a line from the node position to the neighbour position
     * @param nodePosition1 - The node position
     * @param nodePosition2 - A neighbour position
     */
    private void drawLine(Position nodePosition1, Position nodePosition2, Color lineColor) {
        Line line = new Line();

        line.setStartX(PositionUtils.scalePositionToDraw(nodePosition1).getX());
        line.setStartY(PositionUtils.scalePositionToDraw(nodePosition1).getY());

        line.setEndX(PositionUtils.scalePositionToDraw(nodePosition2).getX());
        line.setEndY(PositionUtils.scalePositionToDraw(nodePosition2).getY());

        line.setStroke(lineColor);
        line.setStrokeWidth(2);
        this.circuitGrid.getChildren().add(line);
    }


    private void logRaceEvent(RaceLog event) {
        Text logText = new Text(event.getMessage() + "\n");
        logText.setFill(event.getColor());
        logText.setFont(Font.font("Segoe UI", 14));
        this.logArea.getChildren().add(logText);
    }

}
