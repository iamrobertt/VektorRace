package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.crash;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;

/**
 * Abstract class that represents a crash handler in the race management system.
 * This class extends the {@link RaceHandler} interface and serves as a base
 * implementation for handling crash-related logic during a race.
 *
 * <p>Concrete subclasses of {@code CrashHandler} should implement specific
 * logic for handling crashes and logging related events. This class ensures
 * that all crash handlers follow a consistent interface and provides a common
 * base for implementing crash management functionality.</p>
 *
 * @see RaceHandler
 */
public abstract class CrashHandler implements RaceHandler{


    /**
     * The raceManager that manages the current race.
     */
    protected RaceManager raceManager;


    /**
     * Updates the state of the race in response to a crash event.
     * This method must be implemented by concrete subclasses to specify
     * how the race state should be modified when a crash occurs.
     */
    public abstract void updateState();


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
