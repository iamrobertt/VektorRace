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


    /**
     * Method that builds a circuit from a given data
     * @return Circuit a circuit built from the data
     */
    Circuit setup(ParsedFileData fileData);


}
