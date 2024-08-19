package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.crash;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.crash.LeaveRaceCrashLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLogger;


/**
 * Class that handles crash events where a player crashes and is removed from the race.
 * This class extends {@link CrashHandler} and implements specific logic for handling crashes that
 * result in a player leaving the race. It logs the crash event and updates the race state accordingly.
 *
 * <p>When a crash is detected, the player is removed from the race, and the race continues with the next player.</p>
 */
public final class LeaveRaceCrashHandler extends CrashHandler {


    /**
     * Logs the details of the crash event, including the player involved and the position where the crash occurred.
     *
     * @param newPosition - The position of the player after the move where the crash occurred.
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition) {
        validateData(prevPosition,newPosition,this.raceManager);

        if(!this.raceManager.getCircuitManager().isCrashing(prevPosition, newPosition)) return null;

        logEvent(newPosition);
        MoveResult moveResult = new MoveResult(this.raceManager.getCurrentPlayer(), newPosition, MoveResultType.CRASH_LEAVE_RACE);
        updateState();

        return moveResult;
    }


    /**
     * Updates the state of the race by removing the current player from the race.
     *
     */
    @Override
    public void logEvent(Position newPosition) {
        RaceLogger.log(new LeaveRaceCrashLog(this.raceManager.getCurrentPlayer(), newPosition));
    }


    /**
     * Updates the state of the race by removing the current player from the race.
     * The function updateCurrentPlayer(); in race manager can't be used due to the fact
     * that the current player is already being removed from the queue, making the head if the queue shift automatically.
     */
    @Override
    public void updateState() {
        this.raceManager.getPlayersManager().removePlayer(this.raceManager.getCurrentPlayer());
    }
}
