package it.unicam.cs.NeculaRobertGabriel123390.api.model;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetupFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerSetupFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;

import java.util.List;


/**
 * Class that creates a race(circuit and players) from the parsed file data
 */
public class RaceSetup {


    private final ParsedFileData parsedFileData;
    private List<RaceHandler> handlers;


    public RaceSetup(ParsedFileData parsedFileData, List<RaceHandler> handlers) {
        //todo validate
        this.parsedFileData = parsedFileData;
        this.handlers = handlers;
    }



    /**
     * Method that takes that build a race based on the given file
     *
     * @return Race - A new race
     */
    public Race setup() {
        Circuit circuit = createCircuit();
        List<Player> players = createPlayers();
        return new Race(circuit, players, this.handlers);
    }


    /**
     * Method that creates the circuit from the given file excluding the non parts of the track(#symbol)
     * @return Circuit - A new circuit
     */
    private Circuit createCircuit() {
        CircuitSetup circuitSetup = CircuitSetupFactory.getCircuitSetup(this.parsedFileData);
        return circuitSetup.setup(this.parsedFileData);
    }


    /**
     * Method that creates a list of players from the given file
     * @return List<Player> The list of players
     */
    private List<Player> createPlayers() {
        PlayerSetup playerSetup = PlayerSetupFactory.getPlayerSetup(this.parsedFileData);
        return playerSetup.setup(this.parsedFileData);
    }


}
