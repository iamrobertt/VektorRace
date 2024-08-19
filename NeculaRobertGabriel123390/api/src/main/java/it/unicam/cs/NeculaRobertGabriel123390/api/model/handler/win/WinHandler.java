package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.win;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;

/**
 * Abstract class representing a handler for win conditions in a racing game.
 * This class implements the {@link RaceHandler} interface and serves as a base
 * for specific win condition handlers.
 *
 * <p>Concrete subclasses of {@code WinHandler} should implement the logic for
 * determining and managing win conditions based on the rules of the race.</p>
 *
 * @see RaceHandler
 */
public abstract class WinHandler implements RaceHandler{


    /**
     * The raceManager that manages the current race.
     */
    protected RaceManager raceManager;


    /**
     * Updates the state of the race in response to a win event.
     * This method must be implemented by concrete subclasses to specify
     * what the race manager should do in case of a win move.
     * @param prevPosition The position of the player before the move.
     * @param movePosition The move done by the player.
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
