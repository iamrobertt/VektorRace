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


import it.unicam.cs.NeculaRobertGabriel123390.api.model.Line;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;

import java.util.HashMap;
import java.util.Map;


/**
 * Class that represents the data of a circuit, including track points, start line and end line
 */
public class Circuit {


    /**
     * A map of all the track nodes created on the environment build
     */
    private final Map<Position, CircuitNode> circuitMap;
    private final Line startLine;
    private final Line endLine;


    public Circuit(Map<Position, CircuitNode> circuitNodes, Line startLine, Line endLine) {
        validateCircuit(circuitNodes);
        this.circuitMap = new HashMap<>(circuitNodes);
        this.startLine = startLine;
        this.endLine = endLine;
    }


    /**
     * Method that checks if circuitNodes is a valid map for a circuit
     * @param circuitNodes - the nodes of the circuit
     */
    private void validateCircuit(Map<Position, CircuitNode> circuitNodes) {
        if (circuitNodes == null) throw new NullPointerException("CircuitNodes is null");
        if (circuitNodes.isEmpty()) throw new IllegalArgumentException("CircuitNodes is empty");

    }


    public boolean isNodePresent(CircuitNode node) {return circuitMap.containsKey(node.getPosition());}


    public boolean isNodePartOfStartLine(CircuitNode node) {
        return startLine.isNodePartOfLine(node);
    }


    public boolean isNodePartOfEndLine(CircuitNode node) {
        return endLine.isNodePartOfLine(node);
    }


    public boolean isEmpty() {return circuitMap.isEmpty(); }


    public CircuitNode getCircuitNode(Position position){return this.circuitMap.get(position);}


    public Map<Position, CircuitNode> getCircuitMap(){
        return this.circuitMap;
    }


    public Line getStartLine() {return startLine;}


    public Line getEndLine() {return endLine;}

}
