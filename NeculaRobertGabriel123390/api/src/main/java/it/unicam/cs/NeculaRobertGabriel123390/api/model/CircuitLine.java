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


import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a line in a circuit, consisting of multiple adjacent nodes.
 *
 * <p>The {@code CircuitLine} class is an abstract base class that models a line made up of a sequence
 * of nodes (positions) within a circuit. Each line has a color that can be set to visually distinguish it.
 * Concrete implementations of this class will define how nodes are added to the line.</p>
 *
 * <p>Subclasses should provide specific implementations of the {@code addNode} method to manage how nodes
 * are added to the line, including any necessary validation.</p>
 */
public abstract class CircuitLine {


    /** List of nodes that define the line. */
    protected final List<Position> nodesOfLine;


    /** Color of the line. */
    protected Color color;


    /**
     * Constructs a {@code CircuitLine} instance with an empty list of nodes and no color.
     */
    public CircuitLine() {
        this.nodesOfLine = new ArrayList<>();
        this.color = null;
    }


    /**
     * Adds a node to the line.
     *
     * <p>Subclasses must provide an implementation for this method that specifies how nodes
     * are added to the line. This might include validating the node before adding it.</p>
     *
     * @param node The node to add to the line. Must not be {@code null}.
     * @throws IllegalArgumentException if the node is invalid or if adding the node violates any constraints.
     */
    public abstract void addNode(Position node);


    /**
     * Returns the list of positions (nodes) that define this line.
     *
     * @return A list of positions that form the line.
     */
    public List<Position> getPositions() {return this.nodesOfLine;}


    /**
     * Returns the node at the specified index in the line.
     *
     * @param index The index of the node to retrieve.
     * @return The node at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Position getNode(int index) {return this.nodesOfLine.get(index);}


    /**
     * Returns the color of the line.
     *
     * @return The color of the line, or {@code null} if no color has been set.
     * @throws NullPointerException if the color is null
     */
    public Color getColor() {
        if(this.color == null)
            throw new NullPointerException("Color is null");

        return this.color;}


    /**
     * Checks if the line is empty (i.e., contains no nodes).
     *
     * @return {@code true} if the line is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {return this.nodesOfLine.isEmpty();}


    /**
     * Sets the color of the line.
     *
     * @param color The color to set. May be {@code null} to remove the color.
     */
    public void setColor(Color color) {this.color = color;}
}
