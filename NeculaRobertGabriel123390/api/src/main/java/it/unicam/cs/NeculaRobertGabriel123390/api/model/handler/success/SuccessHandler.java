package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.success;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;

/**
 * Abstract class representing a handler for successful moves in the race management system.
 * This class extends {@link RaceHandler} and serves as a base implementation for handling
 * moves that have been successfully completed, assuming no other handlers (such as COLLISION
 * or crash handlers) have been triggered.
 *
 * <p>Concrete subclasses of {@code SuccessHandler} should implement specific logic for
 * handling successful moves, including updating the race state and logging relevant information.
 * If there are new implementation of race logic (like assigning points or other things), a new {@link SuccessHandler}
 * can be created to manage the new features.</p>
 *
 * @see RaceHandler
 */
public abstract class SuccessHandler implements RaceHandler{


    /**
     * The raceManager that manages the current race.
     */
    protected RaceManager raceManager;


    /**
     * Updates the state of the race after a successful move. The implementation can vary based on the success handler.
     * @param prevPosition The position of the player before the move.
     * @param movePosition The move made by player
     */
    public abstract void updateState(Position prevPosition, Position movePosition);


    /**
     * Sets the race manager for the handler. Checks also if the manager is already initialized
     * @param raceManager The {@link RaceManager} to set to the handler
     */
    @Override
    public void setRaceManager(RaceManager raceManager) {
        if(this.raceManager != null)
            return;
        this.raceManager = raceManager;
    }
}
