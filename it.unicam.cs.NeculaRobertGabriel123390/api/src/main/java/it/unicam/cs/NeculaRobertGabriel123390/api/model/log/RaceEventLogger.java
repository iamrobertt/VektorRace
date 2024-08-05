package it.unicam.cs.NeculaRobertGabriel123390.api.model.log;


/**
 * Interface that provides the possibility to log a RaceEvent in the RaceManager, updating the user on the state of the race
 */
public interface RaceEventLogger {
    void logRaceEvent(RaceLog event);
}