package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;


/**
 * The {@code RaceHandler} interface defines the contract for handling various events
 * that can occur during a race. Implementations of this interface are responsible
 * for managing specific cases that may arise when a player makes a move in the race.
 *
 * <p>This interface supports a chain-of-responsibility pattern, where multiple
 * handlers can be linked together, each one responsible for handling a specific
 * type of event. If a handler does not process the event, it should return null,
 * allowing the next handler in the chain to attempt to handle it.</p>
 */
public interface RaceHandler {


    /**
     * Handles a player's move during the race. This method is responsible for determining
     * whether the move results in a specific event (such as a COLLISION, crash, or victory)
     * and returning the appropriate {@link MoveResult}.
     *
     * <p>If the handler does not need to process the move, it should return {@code null}
     * so that other handlers in the chain can attempt to handle it.</p>
     *
     * @param prevPosition the player's position before the move
     * @param newPosition the player's position after the move
     * @return a {@code MoveResult} if the handler processes the move, or {@code null} if it does not
     */
    MoveResult handle(Position prevPosition, Position newPosition);


    /**
     * Logs an event related to a player's move. Implementations of this method can
     * provide logging functionality to record significant events during the race,
     * such as collisions, crashes, or victories.
     * @param newPosition the player's position after the move
     */
    void logEvent(Position newPosition);


    /**
     * Validates the data provided to the handler, ensuring that the positions and race manager
     * are valid. This method checks the validity of the positions using {@link PositionUtils#validateCircuitNodePosition(Position)}
     * and ensures that the {@code raceManager} is not null.
     *
     * @param prevPosition the player's position before the move.
     * @param newPosition the player's position after the move.
     * @param raceManager the race manager overseeing the current race.
     * @throws NullPointerException if {@code prevPosition}, {@code newPosition}, or {@code raceManager} is null.
     * @throws IllegalArgumentException if {@code prevPosition} or {@code newPosition} is invalid.
     */
    default void validateData(Position prevPosition, Position newPosition, RaceManager raceManager) {
        PositionUtils.validateCircuitNodePosition(prevPosition);
        PositionUtils.validateCircuitNodePosition(newPosition);
        if(raceManager == null)
            throw new NullPointerException("race manager is not initialized for the " + this.getClass().getName() + " handler.");
    }

    /**
     * Assign the race manager given to the handler, that will use it to perform different actions based on the purpose of the handler.
     * @param raceManager The race manager to assign to the handler
     */
    void setRaceManager(RaceManager raceManager);
}
