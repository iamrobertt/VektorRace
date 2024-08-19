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


package it.unicam.cs.NeculaRobertGabriel123390.api.utils;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitNode;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * A utility class that provides methods for retrieving neighboring positions of a circuit node.
 * This class supports the retrieval of both 8-directional (diagonal + orthogonal) neighbors and 4-directional (only orthogonal) neighbors.
 */
public final class CircuitUtils {


    /**
     * // Offsets for calculating 8 neighboring positions (includes diagonals)
     */
    private static final int[][] offsets8Neighbours = {
        { -1, 1},  { 0, 1},  { 1, 1},
        { -1, 0},            { 1, 0},
        {-1, -1},  {0, -1},  {1, -1}
    };


    /**
     * Offsets for calculating 4 neighboring positions (only orthogonal: left, right, top, down)
     */
    private static final int[][] offsets4Neighbours = {
            { -1, 0},   // left
            { 1, 0},    // right
            { 0, -1},   // down
            { 0, 1}     // top
    };


    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private CircuitUtils() {}


    /**
     * Retrieves a list of positions representing the 8 neighbors (orthogonal and diagonal) of the given circuit node.
     * The neighboring positions are determined by applying a set of predefined offsets.
     *
     * @param circuitMap the map of positions to circuit nodes that represents the circuit.
     * @param nodePosition the position of the node whose neighbors are to be retrieved.
     * @return a list of {@link Position} objects representing the 8 neighbors of the node.
     *         If a neighbor is not present in the circuit, it will not be included in the list.
     * @throws IllegalArgumentException if the provided position is invalid or null.
     */
    public static List<Position> get8Neighbours(Map<Position, CircuitNode> circuitMap, Position nodePosition) {
        PositionUtils.validateCircuitNodePosition(nodePosition);
        return getNeighbours(circuitMap, nodePosition, offsets8Neighbours);
    }


    /**
     * Retrieves a list of positions representing the 4 orthogonal neighbors (left, right, top, down) of the given circuit node.
     * The neighboring positions are determined by applying a set of predefined offsets.
     *
     * @param circuitMap the map of positions to circuit nodes that represents the circuit.
     * @param nodePosition the position of the node whose neighbors are to be retrieved.
     * @return a list of {@link Position} objects representing the 4 orthogonal neighbors of the node.
     *         If a neighbor is not present in the circuit, it will not be included in the list.
     * @throws IllegalArgumentException if the provided position is invalid or null.
     */
    public static List<Position> get4Neighbours(Map<Position, CircuitNode> circuitMap, Position nodePosition) {
        PositionUtils.validateCircuitNodePosition(nodePosition);
        return getNeighbours(circuitMap, nodePosition, offsets4Neighbours);
    }


    /**
     * A private helper method that retrieves a list of positions representing the neighbors of a given node in the circuit,
     * based on the provided offsets. The neighbors are calculated by adding the offsets to the node's current position.
     *
     * @param circuitMap the map of positions to circuit nodes that represents the circuit.
     * @param nodePosition the position of the node whose neighbors are to be retrieved.
     * @param offsets a 2D array of integer offsets that define the neighboring positions to check.
     * @return a list of {@link Position} objects representing the neighboring positions that exist in the circuit.
     *         If a neighbor is not present in the circuit, it will not be included in the list.
     * @throws IllegalArgumentException if the provided position is invalid or null.
     */
    private static List<Position> getNeighbours(Map<Position, CircuitNode> circuitMap, Position nodePosition, int[][] offsets) {
        PositionUtils.validateCircuitNodePosition(nodePosition);
        List<Position> neighbours = new ArrayList<>();

        for (int[] offset : offsets) {

            int newX = nodePosition.getX() + offset[0];
            int newY = nodePosition.getY() + offset[1];

            Position neighbourPosition = new Position(newX, newY);
            if (circuitMap.get(neighbourPosition) != null)
                neighbours.add(neighbourPosition);
        }

        return neighbours;
    }
}
