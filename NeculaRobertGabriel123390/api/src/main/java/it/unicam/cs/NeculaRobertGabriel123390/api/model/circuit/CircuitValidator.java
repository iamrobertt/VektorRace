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

import it.unicam.cs.NeculaRobertGabriel123390.api.model.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.FileFormatError;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitUtils;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;

import java.util.List;
import java.util.Map;


/**
 * Provides validation methods for circuits and their components.
 * Ensures that the circuit map, start line, and end line meet the required criteria.
 */
public final class CircuitValidator {


    /**
     * Don't allow user to instantiate a CircuitValidator object because that's not how this class is meant to work.
     */
    private CircuitValidator() {
    }


    /**
     * Validates the given circuit.
     * Ensures that the circuit is not null and that its map, start line, and end line are valid.
     *
     * @param circuit the circuit to validate.
     * @throws NullPointerException if the circuit is null or invalid.
     */
    public static void validate(Circuit circuit) {
        if (circuit == null)
            throw new NullPointerException("Circuit is null");
        validate(circuit.getCircuitMap(), circuit.getStartLine(), circuit.getEndLine());
    }


    /**
     * Validates the given circuit map, start line, and end line.
     * Ensures that none of the parameters are null and that the circuit map contains the start and end lines
     * with the required number of nodes.
     *
     * @param circuitMap the map of circuit nodes.
     * @param startLine  the starting line of the circuit.
     * @param endLine    the ending line of the circuit.
     * @throws NullPointerException     if any of the parameters are null.
     * @throws IllegalArgumentException if the circuit map is empty or the lines are invalid.
     */
    public static void validate(Map<Position, CircuitNode> circuitMap, CircuitLine startLine, CircuitLine endLine) {
        if (circuitMap == null)
            throw new NullPointerException("CircuitNodes is null");
        if (circuitMap.isEmpty())
            throw new IllegalArgumentException("No circuit node was provided for the circuit.");
        validateStartLine(circuitMap, startLine);
        validateEndLine(circuitMap, endLine);
        validateStartEndLinePositioning(circuitMap, startLine, endLine);
        checkForNarrowZones(circuitMap);

    }


    /**
     * Validates the starting line of the circuit.
     * Ensures that the start line is not null, is not empty, and that the circuit map contains all positions of the start line.
     * Also checks that the start line has at least the minimum required number of nodes.
     *
     * @param circuitMap the map of circuit nodes.
     * @param startLine  the starting line of the circuit.
     * @throws NullPointerException     if the start line is null.
     * @throws IllegalArgumentException if the start line is empty, contains positions not in the circuit map,
     *                                  or does not meet the minimum width requirement.
     */
    private static void validateStartLine(Map<Position, CircuitNode> circuitMap, CircuitLine startLine) {
        if (startLine == null)
            throw new NullPointerException("start line is null");
        if (startLine.isEmpty())
            throw new IllegalArgumentException("start line does not have any node");
        if (!circuitMap.keySet().containsAll(startLine.getPositions()))
            throw new IllegalArgumentException("The circuit map does not contain the start line");
        if (startLine.getPositions().size() < CircuitSetup.MIN_CIRCUIT_WIDTH)
            throw new IllegalArgumentException("start line has less nodes than minimum circuit width");
        if (!(startLine instanceof StartEndCircuitLine))
            throw new IllegalArgumentException("start line given is not a StartEndCircuitLine");
    }


    /**
     * Validates the ending line of the circuit.
     * Ensures that the end line is not null, is not empty, and that the circuit map contains all positions of the end line.
     * Also checks that the end line has at least the minimum required number of nodes.
     *
     * @param circuitMap the map of circuit nodes.
     * @param endLine    the ending line of the circuit.
     * @throws NullPointerException     if the end line is null.
     * @throws IllegalArgumentException if the end line is empty, contains positions not in the circuit map,
     *                                  or does not meet the minimum width requirement.
     */
    private static void validateEndLine(Map<Position, CircuitNode> circuitMap, CircuitLine endLine) {
        if (endLine == null)
            throw new NullPointerException("end line is null");
        if (endLine.isEmpty())
            throw new IllegalArgumentException("end line does not have any node");
        if (!circuitMap.keySet().containsAll(endLine.getPositions()))
            throw new IllegalArgumentException("The circuit map does not contain the end line");
        if (endLine.getPositions().size() < CircuitSetup.MIN_CIRCUIT_WIDTH)
            throw new IllegalArgumentException("end line has less nodes than minimum circuit width");
        if (!(endLine instanceof StartEndCircuitLine))
            throw new IllegalArgumentException("end line given is not a StartEndCircuitLine");
    }


    /**
     * Validates the positioning of the start and end lines in the circuit.
     * This method checks that:
     * <ul>
     *     <li>Neither line is null or empty.</li>
     *     <li>The start and end lines are not adjacent and have at least one non-circuit node line between them.</li>
     * </ul>
     *
     * @param startLine the start line of the circuit.
     * @param endLine   the end line of the circuit.
     * @throws NullPointerException     if either the start or end line is null.
     * @throws IllegalArgumentException if either the start or end line is empty.
     * @throws FileFormatError          if the start and end lines are adjacent or incorrectly positioned.
     */
    private static void validateStartEndLinePositioning(Map<Position, CircuitNode> circuitMap, CircuitLine startLine, CircuitLine endLine) {

        List<Position> startLineNodes = startLine.getPositions();
        List<Position> endLineNodes = endLine.getPositions();

        for (int i = 0; i < Math.min(startLineNodes.size(), endLineNodes.size()); i++) {

            List<Position> nodesBetween = BresenhamLineAlgorithm.getPositionsBetween(startLineNodes.get(i), endLineNodes.get(i));

            if (nodesBetween.size() == 2) //no line between start and end line
                throw new FileFormatError("Start and end line cannot be adjacent, they need to be at least 1 node distant");

            if (nodesBetween.size() == 3) //there is a line between them, check if all nodes are nonTrackNode
                validateMidPosition(circuitMap, startLineNodes.get(i), endLineNodes.get(i));
        }
    }


    /**
     * Validates the middle position between the start and end lines.
     * Ensures that the middle position is a non-circuit node, which is necessary for correct circuit layout.
     *
     * @param startNode the starting position of the line segment.
     * @param endNode   the ending position of the line segment.
     * @throws FileFormatError if the middle position is not a non-circuit node.
     */
    private static void validateMidPosition(Map<Position, CircuitNode> circuitMap, Position startNode, Position endNode) {

        PositionUtils.validateCircuitNodePosition(startNode);
        PositionUtils.validateCircuitNodePosition(endNode);

        int midX = (startNode.getX() + endNode.getX()) / 2;
        int midY = (startNode.getY() + endNode.getY()) / 2;
        Position midPosition = new Position(midX, midY);

        if (circuitMap.get(midPosition) != null)
            throw new FileFormatError("The start and end line need to have a non-circuit node line between them");
    }


    /**
     * Checks for narrow zones in the circuit map by ensuring that each node has sufficient neighboring nodes.
     * A narrow zone is identified if a node has fewer than 2 neighbors or does not have valid neighbors
     * in both horizontal and vertical directions, as defined by the presence of neighboring nodes in the
     * left-right and up-down directions.
     *
     * @param circuitMap the map of positions to circuit nodes representing the circuit.
     * @throws FileFormatError if a zone with insufficient neighboring nodes is detected.
     */
    private static void checkForNarrowZones(Map<Position, CircuitNode> circuitMap) {
        for (Position position : circuitMap.keySet()) {
            List<Position> neighbours = CircuitUtils.get4Neighbours(circuitMap, position);
            if (neighbours.size() < 2 || !hasValidNeighbours(neighbours, position))
                throw new FileFormatError("The circuit map contains a zone at " + position + " where the width is less then 2");
        }
    }


    /**
     * Determines if the given list of neighboring positions includes at least two valid neighbors
     * such that there are at least one neighbor to the left or right and at least one neighbor above or below
     * the given node position. This ensures that the node is part of a valid circuit with sufficient width.
     *
     * @param neighbours a list of {@link Position} objects representing the neighboring positions of a node.
     * @param position the position of the node whose neighbors are being checked.
     * @return {@code true} if the node has valid neighbors in both horizontal and vertical directions;
     *         {@code false} otherwise.
     */
    private static boolean hasValidNeighbours(List<Position> neighbours, Position position) {
        boolean hasLeft = false, hasRight = false;
        boolean hasUp = false, hasDown = false;

        for (Position neighbour : neighbours) {
            // Check horizontal neighbors
            if (neighbour.getX() == position.getX() - 1 && neighbour.getY() == position.getY())
                hasLeft = true;
            if (neighbour.getX() == position.getX() + 1 && neighbour.getY() == position.getY())
                hasRight = true;

            // Check vertical neighbors
            if (neighbour.getX() == position.getX() && neighbour.getY() == position.getY() - 1)
                hasDown = true;
            if (neighbour.getX() == position.getX() && neighbour.getY() == position.getY() + 1)
                hasUp = true;
        }

        return (hasLeft || hasRight) && (hasUp || hasDown);

    }
}
