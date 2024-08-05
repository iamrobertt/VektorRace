package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.WinLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;


/**
 * Class that handles the win of a player in the race
 */
public class WinHandler implements RaceHandler {


    /**
     * Method that handles the win of a player, returning a MoveResultType of win that will display the victory scene
     * @param prevPosition - The position of the player before the move
     * @param newPosition - The position of the player after the move
     * @param raceManager - The race manager for that race
     * @return Null if handler has not detected a win, MoveResult if the handler handled that move
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition, RaceManager raceManager) {
        raceManager.setRaceFinished(true);
        logEvent(raceManager, newPosition);
        return new MoveResult(raceManager.getTurnManager().getCurrentPlayer(), prevPosition, MoveResultType.win);

    }


    /**
     * Method that logs the winner info
     * @param raceManager - The race manager for that race
     * @param newPosition - The position of the player after the move
     */
    @Override
    public void logEvent(RaceManager raceManager, Position newPosition) {
        raceManager.getEventLogger().logRaceEvent(new WinLog(raceManager.getCurrentPlayer()));
    }


}
