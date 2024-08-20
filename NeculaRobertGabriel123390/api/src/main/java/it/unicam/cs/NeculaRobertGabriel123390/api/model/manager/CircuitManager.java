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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.manager;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitUtils;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;

import java.util.List;

/**
 * The {@code CircuitManager} class is responsible for managing and updating the state of a circuit
 * during a race. It handles the validation of player moves, checks for collisions, crashes, and
 * determines when a player has won the race by crossing the finish line.
 *
 * <p>This class encapsulates the logic for interacting with the {@link Circuit} and updates the state
 * of circuit nodes based on player actions. It ensures that the circuit is correctly maintained and
 * provides necessary checks to determine the outcome of player moves.</p>
 *
 */
public final class CircuitManager {


    private final Circuit circuit;


    /**
     * Constructs a {@code CircuitManager} with the specified circuit.
     *
     * @param circuit the circuit to be managed
     * @throws NullPointerException if the circuit is null
     */
    public CircuitManager(Circuit circuit) {
        CircuitValidator.validate(circuit);
        this.circuit = circuit;
    }


    /**
     * Updates the circuit after a player has moved by setting the previous position as free and
     * the new position as occupied.
     *
     * @param prevPosition the previous position of the player
     * @param movePosition the move chosen by the player
     */
    public void updateCircuitAfterMove(Position prevPosition, Position movePosition) {
        PositionUtils.validateCircuitNodePosition(prevPosition);
        PositionUtils.validatePosition(movePosition);
        checkPositionInCircuit(prevPosition);

        Position updatedPosition = PositionUtils.addPositions(prevPosition, movePosition);
        setFree(prevPosition);
        setOccupied(updatedPosition);
    }


    /**
     * Checks if a player, after making a move, will collide with another player.
     *
     * @param newPosition the position reached after the move
     * @return true if another player is present at that position, false otherwise
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isColliding(Position newPosition) {
        PositionUtils.validateCircuitNodePosition(newPosition);
        checkPositionInCircuit(newPosition);
        return isOccupied(newPosition);
    }


    /**
     * Checks if a player's move will result in a crash by verifying that all nodes between the
     * start and end positions are part of the track.
     *
     * @param prevPosition the previous position of the player
     * @param newPosition the position of the player after the move
     * @return true if the player crashes, false otherwise
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isCrashing(Position prevPosition, Position newPosition) {
        PositionUtils.validateCircuitNodePosition(prevPosition);
        PositionUtils.validateCircuitNodePosition(newPosition);
        checkPositionInCircuit(prevPosition);

        if (this.circuit.getCircuitNode(newPosition) == null) return true;

        List<Position> positionsBetween = BresenhamLineAlgorithm.getPositionsBetween(prevPosition, newPosition);
        for (Position position : positionsBetween)
            if (this.circuit.getCircuitNode(position) == null)
                return true;

        return false;
    }


    /**
     * Determines if a player has won by crossing the finish line.
     *
     * @param prevPosition the previous position of the player
     * @param newPosition the position of the player after the move
     * @return true if the player has won, false otherwise
     */
    public boolean hasWon(Position prevPosition, Position newPosition) {
        PositionUtils.validateCircuitNodePosition(prevPosition);
        PositionUtils.validateCircuitNodePosition(newPosition);

        return hasCrossedEndLine(prevPosition, newPosition);
    }


    /**
     * Checks if the end line has been crossed by the player.
     *
     * @param prevPosition the previous position of the player
     * @param newPosition the position of the player after the move
     * @throws IllegalArgumentException if the {@code prevPosition} or {@code newPosition} do not exist in the circuit
     * @return true if the player has crossed the end line, false otherwise
     */
    private boolean hasCrossedEndLine(Position prevPosition, Position newPosition) {
        PositionUtils.validateCircuitNodePosition(prevPosition);
        PositionUtils.validateCircuitNodePosition(newPosition);
        checkPositionInCircuit(prevPosition);

        List<Position> positionsBetween = BresenhamLineAlgorithm.getPositionsBetween(prevPosition, newPosition);

        for (Position position : positionsBetween)
            if (this.circuit.getEndLine().getPositions().contains(position))
                return true;

        return false;

    }


    /**
     * Checks if the position is already occupied by another player.
     * @param position The position to check if it's occupied.
     * @return true if the position is occupied, false otherwise.
     */
    public boolean isOccupied(Position position) {
        PositionUtils.validateCircuitNodePosition(position);
        checkPositionInCircuit(position);

        return this.circuit.getCircuitNode(position).getState().equals(CircuitNodeState.occupied);
    }


    /**
     * Sets the specified position as occupied.
     *
     * @param position the position to set as occupied
     * @throws IllegalArgumentException if the position is already occupied
     */
    public void setOccupied(Position position) {
        PositionUtils.validateCircuitNodePosition(position);
        checkPositionInCircuit(position);

        this.circuit.getCircuitNode(position).setState(CircuitNodeState.occupied);
    }


    /**
     * Sets the specified position as free, indicating that the node is now not occupied by a player.
     *
     * @param position the position to set as free
     */
    public void setFree(Position position) {
        PositionUtils.validateCircuitNodePosition(position);
        checkPositionInCircuit(position);

        this.circuit.getCircuitNode(position).setState(CircuitNodeState.trackNode);
    }


    /**
     * Retrieves the start line of the circuit.
     *
     * @return the start line of the circuit
     */
    public CircuitLine getStartLine() {return this.circuit.getStartLine();}


    /**
     * Finds the first free neighbor position around the given position.
     *
     * @param position The position to find a free neighboring position from.
     * @return a free neighboring {@link Position}, or {@code null} if none are available.
     */
    public Position getFreeNeighbour(Position position) {
        PositionUtils.validateCircuitNodePosition(position);
        checkPositionInCircuit(position);

        List<Position> neighboursPosition = CircuitUtils.get8Neighbours(this.circuit.getCircuitMap(), position);

        for(Position neighbour : neighboursPosition)
            if(!isOccupied(neighbour))
                return neighbour;

        return null;
    }


    /** Checks if the position is present in the circuit, otherwise throws an {@link IllegalArgumentException}
     * @throws IllegalArgumentException if the {@code position} does not exist in the circuit
     * @param position The position to check
     */
    private void checkPositionInCircuit(Position position) {
        PositionUtils.validateCircuitNodePosition(position);
        if(!this.circuit.isPresent(position))
            throw new IllegalArgumentException(position + " is not part of the circuit.");
    }

}
