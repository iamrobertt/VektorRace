package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.SuccessMoveLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;


/**
 * Class that does not check for collisions, used when the user wants to allow collisions between players
 */
public class NoCollisionHandler implements RaceHandler {


    /**
     * Method that returns always null, used when the user wants to allow collisions between players
     * @param prevPosition - The position of the player before the move
     * @param newPosition - The position of the player after the move
     * @param raceManager - The race manager for that race
     * @return Null does not check for a collision
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition, RaceManager raceManager) {
        return null;
    }


    /**
     * Method is never being called cause no collision will be detected
     * @param raceManager - The race manager for that race
     * @param newPosition - The position of the player after the move
     */
    @Override
    public void logEvent(RaceManager raceManager, Position newPosition) {
        raceManager.getEventLogger().logRaceEvent(new SuccessMoveLog(raceManager.getCurrentPlayer(), newPosition));
    }
}
