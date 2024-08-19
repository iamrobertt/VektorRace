package it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;


/**
 * Interface responsible for building a {@link Circuit} based on parsed file data.
 * Implementations of this interface should define how the circuit is constructed
 * from the provided data.
 */
public interface CircuitSetup {


    /**
     * The maximum number of nodes allowed in the X-axis of the circuit.
     */
    int MAX_NODES_X = 63;

    /**
     * The maximum number of nodes allowed in the Y-axis of the circuit.
     */
    int MAX_NODES_Y = 35;

    /**
     * The minimum width required for the circuit, in terms of nodes.
     */
    int MIN_CIRCUIT_WIDTH = 2;

    /**
     * The dimension of the rectangular grid used in the circuit.
     */
    int DIM_RECT = 20;


    /**
     * Constructs a {@link Circuit} based on the given parsed file data.
     * The implementation should interpret the data and create a corresponding
     * circuit map, start line, and end line.
     *
     * @param fileData the parsed data from which the circuit is to be built.
     * @return a {@link Circuit} object representing the circuit described by the file data.
     * @throws IllegalArgumentException if the data provided is invalid or insufficient to build a circuit.
     */
    Circuit setup(ParsedData<?> fileData);


}
