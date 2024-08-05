package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.SuccessMoveLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;


/**
 * Class that handles a successful move, meaning that none of the handlers before have been triggered
 */
public class SuccessHandler implements RaceHandler {


    private final Position movePosition;


    public SuccessHandler(Position movePosition){
        this.movePosition = movePosition;
    }


    /**
     * Method that supplies the information about the successful move made by the player
     * @param prevPosition - The position of the player before the move
     * @param newPosition - The position of the player after the move
     * @param raceManager - The race manager for that race
     * @return MoveResult the result of the move
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition, RaceManager raceManager) {

        logEvent(raceManager, newPosition);
        MoveResult moveResult = new MoveResult(raceManager.getTurnManager().getCurrentPlayer(), prevPosition, MoveResultType.success);

        updateState(raceManager, prevPosition);
        return moveResult;
    }


    /**
     * Method that logs info the new position of the user after the move
     * @param raceManager - The race manager for that race
     * @param newPosition - The position of the player after the move
     */
    @Override
    public void logEvent(RaceManager raceManager, Position newPosition) {
        raceManager.getEventLogger().logRaceEvent(new SuccessMoveLog(raceManager.getTurnManager().getCurrentPlayer(), newPosition));
    }


    /**
     * Method that makes the move, updates the circuit nodes occupied before and after the move and continues the race
     * @param raceManager - The race manager
     * @param prevPosition - The position before the move
     */
    private void updateState(RaceManager raceManager, Position prevPosition){
        raceManager.getPlayersManager().makeMove(raceManager.getTurnManager().getCurrentPlayer(), this.movePosition);
        raceManager.getTurnManager().updateCurrentPlayer();
        raceManager.getCircuitManager().updateCircuitAfterMove(prevPosition, this.movePosition);
    }
}
