package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.CollisionLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;


/**
 * Class that provides checks for collisions, supplying a result if the handler detected a collision
 */
public class CollisionHandler implements RaceHandler {


    /**
     *
     * @param prevPosition - The position of the player before the move
     * @param newPosition - The position of the player after the move
     * @param raceManager - The race manager for that race
     * @return Null if handler has not detected a collision, MoveResult if the handler handled that move
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition, RaceManager raceManager) {
        if(!raceManager.getCircuitManager().isColliding(newPosition)) return null;

        logEvent(raceManager, newPosition);
        return new MoveResult(raceManager.getCurrentPlayer(), prevPosition, MoveResultType.collision);
    }


    /**
     * Method that logs info about the collision
     * @param raceManager - The race manager for that race
     * @param newPosition - The position of the player after the move
     */
    @Override
    public void logEvent(RaceManager raceManager, Position newPosition) {
        raceManager.getEventLogger().logRaceEvent(new CollisionLog(raceManager.getCurrentPlayer(), newPosition));
    }


}
