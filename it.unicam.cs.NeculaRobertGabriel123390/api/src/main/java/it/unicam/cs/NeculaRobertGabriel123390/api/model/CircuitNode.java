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


/**
 * CLass that represent a specific point into the track
 */


public class CircuitNode {


    private final Position position;
    private CircuitNodeState state;


    /**
     * Initialize a point of the track
     *
     * @param nodePosition - The exact position of the point
     * @param type         - The state of the node (either trackNode, start
     */
    public CircuitNode(Position nodePosition, CircuitNodeState type) {
        validateNode(nodePosition, type);
        this.position = nodePosition;
        this.state = type;
    }


    /**
     * Method that checks if the supplied data is valid for a node
     * @param nodePosition - the position of the node
     * @param state - the state of the node
     */
    private void validateNode(Position nodePosition, CircuitNodeState state) {
        if(nodePosition == null) throw new NullPointerException("Position is null");
        if(state == null) throw new NullPointerException("State is null");
    }


    public Position getPosition() {
        return position;
    }


    public CircuitNodeState getState() {
        return state;
    }


    public void setState(CircuitNodeState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircuitNode node = (CircuitNode) o;
        return this.position == node.getPosition() && this.state == node.getState();

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
