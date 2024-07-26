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


import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitNodeState;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;

/**
 * CLass that represent a specific point into the track
 */


public class CircuitNode {


    private final Position position;
    private final CircuitNodeState type;


    /**
     * Initialize a point of the track
     *
     * @param nodePosition - The exact position of the point
     * @param type         - The type of the node (either trackNode, start
     */
    public CircuitNode(Position nodePosition, CircuitNodeState type) {
        this.position = nodePosition;
        this.type = type;
    }


    public Position getPosition() {
        return position;
    }


    public CircuitNodeState getType() {
        return type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircuitNode node = (CircuitNode) o;
        return this.position == node.getPosition() && this.type == node.getType();

    }


    @Override
    public int hashCode() {
        return this.position.hashCode() + this.type.hashCode();
    }


    @Override
    public String toString() {
        return "CircuitNode: (" + position.getX() + ", " + position.getY() + ")";
    }

}
