package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;


/**
 * Interface that represents a race handler, providing some methods to manage different cases in a race
 */
public interface RaceHandler {


    /**
     * Method that handles a player move
     * @param prevPosition - The position of the player before the move
     * @param newPosition - The position of the player after the move
     * @param raceManager - The race manager for that race
     * @return Null if handler does not need to handle the move, MoveResult if the handler handled that move
     */
    MoveResult handle(Position prevPosition, Position newPosition, RaceManager raceManager);


    /**
     *
     * @param raceManager - The race manager for that race
     * @param newPosition - The position of the player after the move
     */
    void logEvent(RaceManager raceManager, Position newPosition);
}
