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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Class that represents the data of a circuit, including circuit points, start line and end line
 */
public class Circuit {


    /**
     * A map of all the track nodes created on the environment build
     */
    private final Map<Position, CircuitNode> circuitMap;


    private final CircuitLine startLine;


    private final CircuitLine endLine;


    public Circuit(Map<Position, CircuitNode> circuitNodes, CircuitLine startLine, CircuitLine endLine) {
        validateCircuit(circuitNodes, startLine, endLine);
        this.circuitMap = new HashMap<>(circuitNodes);
        this.startLine = startLine;
        this.endLine = endLine;
    }


    /**
     * Method that checks if circuitMap is a valid map for a circuit
     *
     * @param circuitMap - the circuit map
     * @param startLine - the start line
     * @param endLine - the end line
     */
    private void validateCircuit(Map<Position, CircuitNode> circuitMap, CircuitLine startLine, CircuitLine endLine) {
        if (circuitMap == null) throw new NullPointerException("CircuitNodes is null");
        if (circuitMap.isEmpty()) throw new IllegalArgumentException("CircuitNodes is empty");
        if (startLine == null) throw new NullPointerException("startLine is null");
        if (endLine == null) throw new NullPointerException("endLine is null");
        if (startLine.isEmpty()) throw new IllegalArgumentException("startLine does not have any node");
        if (endLine.isEmpty()) throw new IllegalArgumentException("endLine does not have any node");
        if( startLine.getNodesOfLine().size() < CircuitSetup.MIN_CIRCUIT_WIDTH)
            throw new IllegalArgumentException("startLine has less nodes than minimum circuit width");
        if( endLine.getNodesOfLine().size() < CircuitSetup.MIN_CIRCUIT_WIDTH)
            throw new IllegalArgumentException("endLine has less nodes than minimum circuit width");
    }


    public boolean isNodePartOfEndLine(CircuitNode node) {
        return endLine.isNodePartOfLine(node);
    }


    public CircuitNode getCircuitNode(Position position){return this.circuitMap.get(position);}


    public CircuitLine getStartLine() {return startLine;}


    public CircuitLine getEndLine() {return endLine;}


    /**
     * Method that retrieves all the positions of the circuit nodes
     * @return Set<Position> the list of the points that are part of the circuit
     */
    public Set<Position> getPositions() {return this.circuitMap.keySet();}

}
