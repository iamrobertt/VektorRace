package it.unicam.cs.NeculaRobertGabriel123390.api.model.race;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetupFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerSetupFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;

import java.util.List;


/**
 * Creates a {@link Race} by setting up the circuit with {@link CircuitSetup} implementations and the players with {@link PlayerSetup} implementations
 * based on given parsed data.
 * <p>
 * This class uses the provided file data to configure the circuit and players for a new race.
 * It also validates the handlers to ensure they are in the correct order and of the correct type.
 * </p>
 */
public class RaceSetup {


    private final ParsedData<?> parsedFileData;


    private final List<RaceHandler> handlers;


    /**
     * Constructs a `RaceSetup` instance with the given parsed file data and handlers.
     *
     * @param parsedFileData The data parsed from a file, used to configure the race.
     * @param handlers The list of race handlers to manage race events.
     * @throws NullPointerException if {@code parsedFileData} or {@code handlers} is null.
     */
    public RaceSetup(ParsedData<?> parsedFileData, List<RaceHandler> handlers) {
        validateSetup(parsedFileData, handlers);
        this.parsedFileData = parsedFileData;
        this.handlers = handlers;
    }


    /**
     * Validates that the parsed file data and handlers are not null and that handlers are in the correct order.
     *
     * @param parsedFileData The parsed file data.
     * @param handlers The list of handlers.
     * @throws NullPointerException if {@code parsedFileData} or {@code handlers} is null.
     * @throws IllegalArgumentException if handlers are not in the expected order or are of incorrect types.
     */
    private void validateSetup(ParsedData<?> parsedFileData, List<RaceHandler> handlers) {
        if(parsedFileData == null) throw new NullPointerException("ParsedFileData is null");
        validateHandlers(handlers);
    }


    /**
     * Validates the order and types of the handlers.
     *
     * @param handlers The list of handlers.
     * @throws IllegalArgumentException if handlers are not in the expected order or are of incorrect types.
     */
    private void validateHandlers(List<RaceHandler> handlers) {
        HandlerValidator.validateHandlersOrder(handlers);
    }


    /**
     * Creates and sets up a new race based on the parsed file data and handlers.
     *
     * @return A new {@link Race} instance.
     */
    public Race setup() {
        Circuit circuit = createCircuit();
        List<Player> players = createPlayers();
        return new Race(circuit, players, this.handlers);
    }


    /**
     * Creates the circuit from the parsed file data, excluding parts of the track that are not included (e.g., marked with '#').
     *
     * @return A new {@link Circuit} instance.
     */
    private Circuit createCircuit() {
        CircuitSetup circuitSetup = CircuitSetupFactory.getCircuitSetup(this.parsedFileData);
        return circuitSetup.setup(this.parsedFileData);
    }


    /**
     * Creates a list of players based on the parsed file data.
     *
     * @return A list of {@link Player} instances.
     */
    private List<Player> createPlayers() {
        PlayerSetup playerSetup = PlayerSetupFactory.getPlayerSetup(this.parsedFileData);
        return playerSetup.setup(this.parsedFileData);
    }


}
