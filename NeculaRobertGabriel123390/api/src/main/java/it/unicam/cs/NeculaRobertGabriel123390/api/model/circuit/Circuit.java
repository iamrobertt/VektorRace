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


import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitLine;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitNode;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitUtils;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;

import java.util.*;


/**
 * Represents a circuit with nodes, start line, and end line.
 * The circuit consists of a map of positions to nodes and lines that define the start and end of the circuit.
 */
public class Circuit {


    /**
     * A map of all nodes in the circuit, where each key is a position and the value is the corresponding node.
     */
    private final Map<Position, CircuitNode> circuitMap;


    /**
     * The starting line of the circuit.
     */
    private final CircuitLine startLine;


    /**
     * The ending line of the circuit.
     */
    private final CircuitLine endLine;


    /**
     * Constructs a new Circuit with the given nodes, start line, and end line.
     * Validates the integrity of the circuit map and the lines.
     *
     * @param circuitMap a map of circuit nodes keyed by their position.
     * @param startLine the starting line of the circuit.
     * @param endLine the ending line of the circuit.
     * @throws NullPointerException if any of the parameters are null.
     * @throws IllegalArgumentException if the circuit map is empty or the lines do not meet the minimum requirements.
     */
    public Circuit(Map<Position, CircuitNode> circuitMap, CircuitLine startLine, CircuitLine endLine) {
        CircuitValidator.validate(circuitMap, startLine, endLine);
        this.circuitMap = new LinkedHashMap<>(circuitMap);
        this.startLine = startLine;
        this.endLine = endLine;
    }


    /**
     * Retrieves the node at the specified position in the circuit.
     *
     * @param position the position of the node to retrieve.
     * @return the circuit node at the specified position, or {@code null} if no node exists at that position.
     */
    public CircuitNode getCircuitNode(Position position){
        PositionUtils.validateCircuitNodePosition(position);
        return this.circuitMap.get(position);
    }


    /**
     * Retrieves the starting line of the circuit.
     *
     * @return the starting line of the circuit.
     */
    public CircuitLine getStartLine() {return startLine;}


    /**
     * Retrieves the ending line of the circuit.
     *
     * @return the ending line of the circuit.
     */
    public CircuitLine getEndLine() {return endLine;}


    /**
     * Retrieves the set of positions of all nodes in the circuit.
     *
     * @return a set of positions representing all the points that are part of the circuit.
     */
    public Set<Position> getPositions() {return this.circuitMap.keySet();}


    /**
     * Checks if the node at the specified position is an external node.
     * A node is considered external if it has fewer than 8 neighbors.
     *
     * @param nodePosition the position of the node to check.
     * @return {@code true} if the node is an external node, {@code false} otherwise.
     */
    public boolean isCircuitNodeExtern(Position nodePosition) {
        PositionUtils.validateCircuitNodePosition(nodePosition);
        return CircuitUtils.get8Neighbours(this.circuitMap, nodePosition).size() != 8;
    }


    /**
     * Retrieves the map of all nodes in the circuit.
     *
     * @return a map of positions to circuit nodes.
     */
    public Map<Position, CircuitNode> getCircuitMap() {return this.circuitMap;}


    /**
     * Checks if the position given is part of the circuit or not.
     * @param position The position to check.
     * @return true if the position is present in the circuit, false otherwise.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isPresent(Position position) {
        PositionUtils.validateCircuitNodePosition(position);
        return this.circuitMap.containsKey(position);
    }

}
