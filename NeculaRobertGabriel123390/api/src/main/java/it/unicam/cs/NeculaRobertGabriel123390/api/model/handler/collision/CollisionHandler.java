package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.collision;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;

/**
 * Abstract class that represents a COLLISION handler in the race management system.
 * This class extends the {@link RaceHandler} interface and provides a base
 * implementation for handling COLLISION-related logic during a race.
 *
 * <p>Concrete subclasses of {@code CollisionHandler} should implement specific
 * logic for handling collisions and logging related events. This class is used
 * to enforce a common structure for all COLLISION handlers, ensuring that they
 * follow a consistent interface and adhere to the expected behavior for COLLISION
 * management.</p>
 *
 * @see RaceHandler
 */
public abstract class CollisionHandler implements RaceHandler {


    /**
     * The raceManager that manages the current race.
     */
    protected RaceManager raceManager;


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
