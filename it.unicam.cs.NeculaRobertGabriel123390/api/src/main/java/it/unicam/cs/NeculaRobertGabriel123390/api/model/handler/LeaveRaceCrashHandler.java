package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.LeaveRaceCrashLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;


/**
 * Class that provides checks for crashes, supplying a result if the handler detected a crash.
 * This handler deletes a player from the race if it has crashed
 */
public class LeaveRaceCrashHandler implements RaceHandler {


    /**
     * Method that checks if the user has crashed with the move and, if that's the case, it handles it
     * @param prevPosition - The position of the player before the move
     * @param newPosition - The position of the player after the move
     * @param raceManager - The race manager for that race
     * @return Null if handler has not detected a crash, MoveResult if the handler handled that move
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition, RaceManager raceManager) {
        if(!raceManager.getCircuitManager().isCrashing(prevPosition, newPosition)) return null;

        logEvent(raceManager, newPosition);
        MoveResult moveResult = new MoveResult(raceManager.getCurrentPlayer(), prevPosition, MoveResultType.crash);

        updateState(raceManager);

        return moveResult;
    }


    /**
     * Method that logs the crash info
     * @param raceManager - The race manager for that race
     * @param newPosition - The position of the player after the move
     */
    @Override
    public void logEvent(RaceManager raceManager, Position newPosition) {
        raceManager.getEventLogger().logRaceEvent(new LeaveRaceCrashLog(raceManager.getCurrentPlayer(), newPosition));
    }


    /**
     * Method that updates the state of the player, deleting him from the race and continuing the race
     * @param raceManager - The race manager
     */
    private void updateState(RaceManager raceManager) {
        raceManager.getPlayersManager().removePlayer(raceManager.getCurrentPlayer());
        raceManager.getTurnManager().updateCurrentPlayer();
    }
}
