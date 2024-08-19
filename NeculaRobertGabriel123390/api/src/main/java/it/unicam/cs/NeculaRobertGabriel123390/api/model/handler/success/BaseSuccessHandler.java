package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.success;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLogger;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.success.SuccessMoveLog;


/**
 * Handles a successful move in the race, which occurs when no other handlers
 * (such as COLLISION or crash handlers) have been triggered.
 *
 * <p>This class manages the logic for logging a successful move and updating
 * the state of the race after a player has successfully moved to a new position.</p>
 *
 * <p>It extends the {@link SuccessHandler} class, providing the concrete
 * implementation required to process a successful move.</p>
 *
 * @see SuccessHandler
 */
public final class BaseSuccessHandler extends SuccessHandler {


    /**
     * Handles a successful move by logging the move, updating the state of the race,
     * and returning the result of the move.
     *
     * @param prevPosition The position of the player before the move.
     * @param newPosition The position of the player after the move.
     * @return The result of the move, which indicates success.
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition) {
        validateData(prevPosition,newPosition,this.raceManager);

        logEvent(newPosition);
        MoveResult moveResult = new MoveResult(this.raceManager.getCurrentPlayer(), prevPosition, MoveResultType.SUCCESS);

        Position movePosition = new Position(newPosition.getX() - prevPosition.getX(), newPosition.getY() - prevPosition.getY());
        updateState(prevPosition, movePosition);
        return moveResult;
    }


    /**
     * Logs information about the successful move, including the player and the new position.
     * @param newPosition The position of the player after the move.
     */
    @Override
    public void logEvent(Position newPosition) {
        RaceLogger.log(new SuccessMoveLog(this.raceManager.getCurrentPlayer(), newPosition));
    }


    /**
     * Updates the state of the race after a successful move. This includes making the move
     * for the current player, updating the circuit nodes, and continuing to the next player.
     *
     * @param prevPosition The position of the player before the move.
     * @param movePosition The move made by player
     */
    @Override
    public void updateState(Position prevPosition, Position movePosition){
        this.raceManager.getPlayersManager().makeMove(movePosition);
        this.raceManager.getCircuitManager().updateCircuitAfterMove(prevPosition, movePosition);
        this.raceManager.updateCurrentPlayer();
    }
}
