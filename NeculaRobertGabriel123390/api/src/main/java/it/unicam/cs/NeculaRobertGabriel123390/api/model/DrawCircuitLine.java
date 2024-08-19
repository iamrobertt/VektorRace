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

import java.util.List;


/**
 * Represents a specific type of {@link CircuitLine} that allows adding nodes to form a line.
 *
 * <p>The {@code DrawCircuitLine} class extends {@code CircuitLine} and provides an implementation
 * for managing a line with a maximum of two nodes. This class enforces constraints on the number of nodes
 * that can be added to the line and validates the position of each node.</p>
 *
 * <p>Use this class to create a line with exactly two nodes, where each node represents a position
 * in the circuit. This is useful for drawing lines or segments of a circuit with specific constraints.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     DrawCircuitLine line = new DrawCircuitLine();
 *     Position node1 = new Position(0, 0);
 *     Position node2 = new Position(1, 1);
 *     line.addNode(node1);
 *     line.addNode(node2);
 *     // line now has exactly two nodes
 * </pre>
 */
public final class DrawCircuitLine extends CircuitLine{


    /**
     * Constructs a {@code DrawCircuitLine} instance with an empty list of nodes and no color.
     */
    public DrawCircuitLine() {
        super();
    }


    /**
     * Adds a node to the line. This implementation enforces a maximum of two nodes.
     * The method performs the following checks:
     * <ul>
     *   <li>Ensures the node is not {@code null}.</li>
     *   <li>Ensures that no more than two nodes can be added to the line.</li>
     *   <li>Validates the position of the node using {@link PositionUtils#validateCircuitNodePosition(Position)}.</li>
     * </ul>
     * @param node The node to add to the line. Must not be {@code null}.
     * @throws NullPointerException if the node is {@code null}.
     * @throws IllegalArgumentException if trying to add more than two nodes, if the node is invalid or if the node already exists in the line.
     */
    @Override
    public void addNode(Position node) {
        PositionUtils.validateCircuitNodePosition(node);
        validateDrawCircuitLine();

        if(this.nodesOfLine.contains(node))
            throw new IllegalArgumentException("Position " + node + " already exists");

        this.nodesOfLine.add(node);
    }


    /**
     * Validates the draw circuit line for constraints specific to this class.
     *
     * <p>This method checks if the line already contains two nodes, and if so, throws an exception
     * as no more nodes can be added to a {@code DrawCircuitLine}.</p>
     *
     * @throws IllegalArgumentException if trying to add more than two nodes to the line.
     */
    public void validateDrawCircuitLine() {
        if(this.nodesOfLine.size() == 2)
            throw new IllegalArgumentException("Can't add more nodes to this line");
    }


    /**
     * Returns the list of positions (nodes) that define this line.
     *
     * @return A list of positions that form the line.
     * @throws IllegalStateException if the user wants to use the line but the line has not 2 nodes.
     */
    @Override
    public List<Position> getPositions(){
        if(this.nodesOfLine.size() != 2)
            throw new IllegalStateException("To be used the draw line needs to have 2 positions");

        return super.getPositions();
    }

}
