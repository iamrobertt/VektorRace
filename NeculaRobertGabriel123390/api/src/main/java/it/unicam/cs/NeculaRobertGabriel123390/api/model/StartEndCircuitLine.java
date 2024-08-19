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


/**
 * Represents a line segment in a circuit for start and end lines.
 *
 * <p>The {@code StartEndCircuitLine} class extends {@code CircuitLine} and is designed to manage
 * a line segment where the first node added represents the start and the subsequent nodes represent
 * the end of the line. This class enforces adjacency checks for nodes added to ensure they are correctly
 * connected in the circuit.</p>
 *
 * <p>Use this class to define circuit lines that have specific start and end positions, ensuring
 * that each added node maintains proper adjacency within the circuit.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     StartEndCircuitLine line = new StartEndCircuitLine();
 *     Position node1 = new Position(0, 0);
 *     Position node2 = new Position(0, 1);
 *     Position nodeN = new Position(x, y);
 *     line.addNode(node1);
 *     line.addNode(node2);
 *     line.addNode(nodeN)
 * </pre>
 */
public final class StartEndCircuitLine extends CircuitLine{


    /**
     * Constructs a {@code StartEndCircuitLine} instance with an empty list of nodes and no color.
     */
    public StartEndCircuitLine() {
        super();
    }


    /**
     * Adds a node to the line. The first node added will be treated as the start, and subsequent nodes
     * are validated for adjacency with the existing nodes.
     *The method performs the following checks:
     * <ul>
     *   <li>Ensures the node is not {@code null}.</li>
     *   <li>If the line is empty, the node is added as the start node.</li>
     *   <li>If the line is not empty, the method validates the node's position and checks that it is
     *       adjacent to the last node.</li>
     * </ul>
     * @param node The node to add to the line. Must not be {@code null}.
     * @throws NullPointerException if the node is {@code null}.
     * @throws IllegalArgumentException if the node is not adjacent to the last node in the line or the node already exists in the line.
     */
    @Override
    public void addNode(Position node) {
        if(node == null) throw new NullPointerException("node is null");

        if(this.nodesOfLine.contains(node))
            throw new IllegalArgumentException("Position " + node + " already exists");


        this.nodesOfLine.add(node);
        PositionUtils.validateCircuitNodePosition(node);
        PositionUtils.checkNodesAdjacency(getPositions());
    }
}
