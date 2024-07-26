package it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;


/**
 * Interface that builds a circuit based on file format
 */
public interface CircuitSetup {


    int MAX_NODES_X = 63;
    int MAX_NODES_Y = 35;
    int MIN_CIRCUIT_WIDTH = 2;
    int DIM_RECT = 20;
    int LAST_ROW = 38;
    int PLAYERS_NUMBER_ROW = 37;
    int HUMAN_PLAYERS_DATA_ROW = 38;


    /**
     * Method that builds a circuit from a given data
     * @return Circuit a circuit built from the data
     */
    Circuit setup(ParsedFileData fileData);


    /**
     * Method that validates a circuit based on file format
     * @param fileData - The file data
     */
    void validateCircuit(ParsedFileData fileData);
}
