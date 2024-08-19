package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.crash;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.crash.ContinueWithPenaltyCrashLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLogger;


/**
 * Class responsible for handling crashes during the race, allowing the player to continue with a penalty.
 * This handler detects if a player has crashed and manages the crash event by resetting the player's moves
 * and allowing them to continue the race.
 * <p>
 * When a crash is detected, this handler logs the event and resets the player's possible moves to reflect
 * the penalty imposed for the crash. The player's turn is then updated to continue the race.
 * </p>
 */
public final class ContinueWithPenaltyCrashHandler extends CrashHandler {


    /**
     * Handles a player's move by checking if a crash has occurred. If a crash is detected,
     * it logs the event, updates the player's state to reflect the penalty, and returns the result.
     * If no crash is detected, it returns {@code null}.
     *
     * @param prevPosition The position of the player before the move.
     * @param newPosition  The position of the player after the move.
     * @return A {@link MoveResult} indicating the crash, or {@code null} if no crash is detected.
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition) {
        validateData(prevPosition,newPosition, this.raceManager);

        if(!this.raceManager.getCircuitManager().isCrashing(prevPosition, newPosition)) return null;

        logEvent(newPosition);
        MoveResult moveResult = new MoveResult(this.raceManager.getCurrentPlayer(), newPosition, MoveResultType.CRASH_CONTINUE_WITH_PENALTY);
        updateState();
        return moveResult;
    }


    /**
     * Logs the details of the crash event, including the player's identity and their position after the crash.
     * * @param newPosition The position of the player after the crash.
     */
    @Override
    public void logEvent(Position newPosition) {
        RaceLogger.log(new ContinueWithPenaltyCrashLog(this.raceManager.getCurrentPlayer(), newPosition));
    }


    /**
     * Updates the state of the race following a crash. This includes resetting the player's moves
     * to reflect the penalty and updating the player's turn in the race.
     *
     */
    @Override
    public void updateState() {
        this.raceManager.getPlayersManager().getCurrentPlayerMoves().resetMoves();
        this.raceManager.updateCurrentPlayer();
    }
}
