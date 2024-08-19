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

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;

import java.util.List;


/**
 * Utility class for handling operations related to positions in a circuit or grid.
 * <p>
 * This class provides methods to perform arithmetic operations on positions, scale positions for display,
 * validate positions, and check the adjacency of positions in the context of a circuit layout.
 * </p>
 */
public final class PositionUtils {


    /**
     * Don't allow user to instantiate a PositionUtils object because that's not how this class is meant to work.
     */
    private PositionUtils() {}


    /**
     * Adds the x and y coordinates of two positions to produce a new position.
     *
     * @param pos1 The first position.
     * @param pos2 The second position.
     * @return A new position representing the sum of the two positions.
     */
    public static Position addPositions(Position pos1, Position pos2){
        return new Position(pos1.getX() + pos2.getX(), pos1.getY() + pos2.getY());
    }


    /**
     * Scales a position to match the dimensions used for display purposes.
     *
     * @param position The position to scale.
     * @return A new position scaled according to the display dimensions.
     */
    public static Position scalePositionToDraw(Position position) {
        return new Position(position.getX() * CircuitSetup.DIM_RECT, position.getY() * CircuitSetup.DIM_RECT);
    }


    /**
     * Validates if a position is within the bounds of the circuit grid.
     *
     * @param position The position to validate.
     * @throws NullPointerException if the position is null
     * @throws IllegalArgumentException if the position is out of bounds.
     */
    public static void validateCircuitNodePosition(Position position) {
        validatePosition(position);
        if(position.getX() < 0 || position.getX() > CircuitSetup.MAX_NODES_X ||
                position.getY() < 0 || position.getY() > CircuitSetup.MAX_NODES_Y)
            throw new IllegalArgumentException(position + " is out of bounds");

    }


    /**
     * Validates the given position to ensure it is not null.
     *
     * <p>This method checks if the provided {@code Position} object is {@code null}.
     * If it is {@code null}, a {@link NullPointerException} is thrown with the message
     * "Position is null". This method is used to ensure that the position parameter
     * is valid before performing operations that require a non-null position.</p>
     *
     * @param position The {@code Position} object to validate.
     * @throws NullPointerException if {@code position} is {@code null}.
     */
    public static void validatePosition(Position position) {
        if(position == null)
            throw new NullPointerException("Position is null");
    }


    /**
     * Checks if a list of positions are adjacent to each other.
     *
     * @param nodesPosition The list of positions to check.
     * @throws NullPointerException     if the list of positions is null.
     * @throws IllegalArgumentException if the list of positions is empty or the positions are not adjacent.
     */
    public static void checkNodesAdjacency(List<Position> nodesPosition) {
        if (nodesPosition == null)
            throw new NullPointerException("nodesPosition is null");
        if(nodesPosition.isEmpty())
            throw new IllegalArgumentException("nodesPositions is empty");


        boolean allXSame = nodesPosition.stream().allMatch(node -> node.getX() == nodesPosition.getFirst().getX());

        boolean allYSame = nodesPosition.stream().allMatch(node -> node.getY() == nodesPosition.getFirst().getY());

        if(!allXSame && !allYSame) {
            nodesPosition.removeLast();
            throw new IllegalArgumentException("nodes in the line are not adjacent");
        }

    }

}
