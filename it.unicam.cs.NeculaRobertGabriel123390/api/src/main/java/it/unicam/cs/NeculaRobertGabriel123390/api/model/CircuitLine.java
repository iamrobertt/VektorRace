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


import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that represents a line, which is a union of multiple adjacent nodes
 */
public class CircuitLine {


    private final List<CircuitNode> nodesOfLine;


    public CircuitLine() {
        this.nodesOfLine = new ArrayList<>();
    }


    /**
     * Method that adds a node to the line
     * @param node - New node to add to the line
     */
    //todo validate node
    public void addNode(CircuitNode node) {
        if (node == null) throw new NullPointerException("CircuitNode is null");
        PositionUtils.validateCircuitNodePosition(node.getPosition());
        this.nodesOfLine.add(node);
    }


    /**
     * Method that checks if the supplied node is part of the line
     * @param node - The node to check
     * @return true if the node is part of the line, false otherwise
     */
    public boolean isNodePartOfLine(CircuitNode node) {
        return this.nodesOfLine.contains(node);
    }


    public List<CircuitNode> getNodesOfLine() {return this.nodesOfLine;}


    public CircuitNode getNode(int index) {return this.nodesOfLine.get(index);}


    public boolean isEmpty() {return this.nodesOfLine.isEmpty();}

}
