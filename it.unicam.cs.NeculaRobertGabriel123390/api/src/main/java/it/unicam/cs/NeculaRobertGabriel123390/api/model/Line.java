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

package it.unicam.cs.NeculaRobertGabriel123390.api.model;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitNodeUtils;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitNode;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that represents a line, which is a union of multiple adjacent nodes
 */
public class Line {

    private final List<CircuitNode> nodesOfLine;

    public Line(List<CircuitNode> nodesOfLine) {
        validateLine(nodesOfLine);
        this.nodesOfLine = new ArrayList<CircuitNode>(nodesOfLine);
    }

    public Line() {
        this.nodesOfLine = new ArrayList<>();
    }


    /**
     * Method that checks if the nodes composing the line are valid
     * @param nodesOfLine - The nodes that are part of the line
     */
    private void validateLine(List<CircuitNode> nodesOfLine) {
        if (nodesOfLine == null)
            throw new NullPointerException("Nodes of line is null");
        if (nodesOfLine.size() < CircuitSetup.MIN_CIRCUIT_WIDTH)
            throw new IllegalArgumentException("A line needs to have at least 2 nodes");
        if (!CircuitNodeUtils.areNodesAdjacent(nodesOfLine))
            throw new IllegalArgumentException("The nodes of the line need to be adjacent");
    }


    /**
     * Method that adds a node to the line
     * @param node - New node to add to the line
     */
    //todo validate node
    public void addNodeToLine(CircuitNode node) {
        if (node == null) throw new NullPointerException("CircuitNode is null");
        this.nodesOfLine.add(node);
    }



    public boolean isNodePartOfLine(CircuitNode node) {
        return this.nodesOfLine.contains(node);
    }


    public List<CircuitNode> getNodesOfLine() {return this.nodesOfLine;}

}
