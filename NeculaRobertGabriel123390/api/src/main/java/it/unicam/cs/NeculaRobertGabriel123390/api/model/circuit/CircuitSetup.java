/*
 * Copyright (c) 2024.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


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
