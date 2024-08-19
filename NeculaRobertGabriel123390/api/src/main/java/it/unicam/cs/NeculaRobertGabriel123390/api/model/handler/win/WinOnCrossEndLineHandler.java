package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.win;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLogger;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.win.WinLog;


/**
 * Handles the scenario where a player wins the race by crossing the end line.
 * <p>
 * This class extends {@link WinHandler} and provides the logic for determining
 * if a player has won by crossing the end line. If a win is detected, it logs
 * the victory and returns a {@link MoveResult} with the win result type.
 * </p>
 */
public final class WinOnCrossEndLineHandler extends WinHandler {


    /**
     * Handles the win condition by checking if the player has crossed the end line.
     * <p>
     * If the player has crossed the end line, it logs the win event and returns
     * a {@link MoveResult} indicating a win. If the player has not won, it returns {@code null}.
     * </p>
     *
     * @param prevPosition The position of the player before the move.
     * @param newPosition The position of the player after the move.
     * @return A {@link MoveResult} with the win result type if the player has won,
     *         {@code null} otherwise.
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition) {
        validateData(prevPosition,newPosition,this.raceManager);

        if(!this.raceManager.getCircuitManager().hasWon(prevPosition, newPosition)) return null;
        logEvent(newPosition);

        Position movePosition = new Position(newPosition.getX() - prevPosition.getX(), newPosition.getY() - prevPosition.getY());
        updateState(prevPosition, movePosition);
        return new MoveResult(this.raceManager.getCurrentPlayer(), prevPosition, MoveResultType.WIN);

    }


    /**
     * Logs the information about the player who has won the race.
     * <p>
     * This method creates a {@link WinLog} with the current player's information
     * and logs it using the {@link RaceLogger}.
     * </p>
     *
     * @param newPosition The position of the player after the move.
     */
    @Override
    public void logEvent(Position newPosition) {
        RaceLogger.log(new WinLog(this.raceManager.getCurrentPlayer()));
    }


    /**
     * Updates the circuit nodes and the player position. No need to update the next player because the race has ended.
     * @param prevPosition The previous position of the player.
     * @param movePosition The move done by the player
     */
    @Override
    public void updateState(Position prevPosition, Position movePosition) {
        this.raceManager.getPlayersManager().makeMove(movePosition);
    }
}
