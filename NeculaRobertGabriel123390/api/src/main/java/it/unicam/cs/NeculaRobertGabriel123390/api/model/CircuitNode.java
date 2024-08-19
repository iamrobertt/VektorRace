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
 * Represents a specific point on the track.
 *
 * <p>The {@code CircuitNode} class defines a point on a racing track with a specified position
 * and a state that indicates its role in the track layout (e.g., start, end, or regular track node).</p>
 *
 * <p>Each {@code CircuitNode} is immutable regarding its position and state once created, although
 * its state can be changed through the setter method.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     Position pos = new Position(10, 20);
 *     CircuitNode node = new CircuitNode(pos, CircuitNodeState.type);
 * </pre>
 */
public final class CircuitNode {


    private final Position position;


    private CircuitNodeState state;


    /**
     * Constructs a {@code CircuitNode} with the specified position and state.
     *
     * @param nodePosition The exact position of the node on the track. Must not be {@code null}.
     * @param type The state of the node, indicating its role (e.g., start, end, or regular track node). Must not be {@code null}.
     * @throws NullPointerException if {@code nodePosition} or {@code type} is {@code null}.
     */
    public CircuitNode(Position nodePosition, CircuitNodeState type) {
        validateNode(nodePosition, type);
        this.position = nodePosition;
        this.state = type;
    }


    /**
     * Validates the provided position and state for the node.
     *
     * @param nodePosition The position of the node.
     * @param state The state of the node.
     * @throws NullPointerException if {@code nodePosition} or {@code state} is {@code null}.
     */
    private void validateNode(Position nodePosition, CircuitNodeState state) {
        PositionUtils.validateCircuitNodePosition(nodePosition);
        if(state == null) throw new NullPointerException("State is null");
    }


    /**
     * Returns the position of the node.
     *
     * @return The position of the node.
     */
    public Position getPosition() {
        return position;
    }


    /**
     * Returns the state of the node.
     *
     * @return The state of the node.
     */
    public CircuitNodeState getState() {
        return state;
    }


    /**
     * Sets the state of the node.
     *
     * @param state The new state of the node. Must not be {@code null}.
     * @throws NullPointerException if {@code state} is {@code null}.
     */
    public void setState(CircuitNodeState state) {
        if(state == null)
            throw new NullPointerException("State is null");
        this.state = state;
    }


    /**
     * Compares this {@code CircuitNode} to the specified object for equality.
     *
     * <p>This method returns {@code true} if the given object is an instance of {@code CircuitNode}
     * and has the same position and state as this node. Otherwise, it returns {@code false}.</p>
     *
     * <p>The comparison is based on the values of the position and state of the nodes. If either of these
     * is different, the nodes are considered not equal.</p>
     *
     * @param o The object to compare with this {@code CircuitNode}.
     * @return {@code true} if the specified object is equal to this {@code CircuitNode}; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircuitNode node = (CircuitNode) o;
        return this.position.equals(node.getPosition()) && this.state.equals(node.getState());

    }


    @Override
    public int hashCode() {
        return this.position.hashCode() + this.state.hashCode();
    }


    @Override
    public String toString() {
        return "CircuitNode: (" + position.getX() + ", " + position.getY() + ")";
    }

}
