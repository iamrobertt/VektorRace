package it.unicam.cs.NeculaRobertGabriel123390.api.model.manager;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceEventLogger;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.SuccessHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Race;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that manages the race, letting each player make its move and handle it checking every case
 * Provides also some methods that the controller takes to update the view
 */
public class RaceManager{


    private final PlayersManager playersManager;


    private final CircuitManager circuitManager;


    private boolean isRaceFinished;


    private final TurnManager turnManager;


    private RaceEventLogger eventLogger;


    private final List<RaceHandler> handlers;


    public RaceManager(Race race) {
        //todo validate
        this.playersManager = new PlayersManager(race.players());
        this.circuitManager = new CircuitManager(race.circuit());
        this.handlers = new ArrayList<>(race.handlers());
        this.turnManager = new TurnManager(this.playersManager);
        this.isRaceFinished = false;
    }


    /**
     * Method that handles the move action for every player, going through all the handlers in the race to check for collision, crashes ecc
     * @param movePosition - The move chose by the user
     * @return MoveResult the move result of the move, that includes player data and type of move
     */
    public MoveResult onPlayerMove(Position movePosition) {
        Position prevPosition = this.turnManager.getCurrentPlayer().getPosition();
        Position newPosition = PositionUtils.addPositions(prevPosition, movePosition);


        for(RaceHandler moveHandler : this.handlers) {
            MoveResult result = moveHandler.handle(prevPosition, newPosition, this);
            if(result != null) return result;
        }

        return new SuccessHandler(movePosition).handle(prevPosition, newPosition, this);
    }


    /**
     * Method that initializes the players position, assigning a different position for every player from the start line
     * @return List<Position> A list of the player's assigned positions
     */
    public List<Position> initializePlayersPosition(){
        CircuitLine startLine = this.circuitManager.getStartLine();
        List<Position> initPlayerPositions = new ArrayList<>();
        //todo da finire, se troppe persone aggiungerle dietro la linea
        for(int i = 0; i < this.playersManager.playersCount(); i++) {
            Position playerPosition = startLine.getNode(i).getPosition();
            initPlayerPositions.add(playerPosition);
            this.playersManager.updatePosition(this.playersManager.getPlayer(i), playerPosition);
            this.circuitManager.setOccupied(playerPosition);
        }

        return initPlayerPositions;
    }



    /**
     * Method that returns for the GUI the next player's moves
     * @return PlayerMoves The next player's move
     */
    public PlayerMoves getNextPlayerPossibleMoves() {return this.turnManager.getNextPlayerPossibleMoves();}


    public boolean hasRaceFinished() {return this.isRaceFinished;}


    public void setRaceFinished(boolean isRaceFinished) {this.isRaceFinished = isRaceFinished;}


    public void setEventLogger(RaceEventLogger raceEventLogger) {this.eventLogger = raceEventLogger;}


    public RaceEventLogger getEventLogger() {return this.eventLogger;}


    public Player getCurrentPlayer() {return this.turnManager.getCurrentPlayer();}


    public CircuitManager getCircuitManager() {return this.circuitManager;}


    public PlayersManager getPlayersManager() {return this.playersManager;}


    public TurnManager getTurnManager() {return this.turnManager;}
}
