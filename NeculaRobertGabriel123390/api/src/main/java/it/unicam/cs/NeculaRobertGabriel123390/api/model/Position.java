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
 * Class that represents the position of a node or a player on the track.
 * <p>
 * The `Position` class encapsulates the coordinates (x, y) of a point on the track,
 * allowing for precise location tracking of nodes and players.
 * </p>
 */
public class Position {


    private int x;

    private int y;


    /**
     * Constructs a `Position` object with the specified coordinates.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Returns the x-coordinate of the position.
     *
     * @return The x-coordinate.
     */
    public int getX() {return x;}


    /**
     * Returns the y-coordinate of the position.
     *
     * @return The y-coordinate.
     */
    public int getY() {return y;}


    /**
     * Sets the x-coordinate of the position.
     *
     * @param x The new x-coordinate.
     */
    public void setX(int x) {this.x = x;}


    /**
     * Sets the y-coordinate of the position.
     *
     * @param y The new y-coordinate.
     */
    public void setY(int y) {this.y = y;}


    /**
     * Checks if this `Position` is equal to another object.
     * <p>
     * Two `Position` objects are considered equal if their x and y coordinates are the same.
     * </p>
     *
     * @param o The object to compare with this position.
     * @return {@code true} if this position is equal to the specified object; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }


    @Override
    public int hashCode() {
        return 31 * (x + y);
    }


    @Override
    public String toString() {
        return "Pos: " + x + ", " + y;
    }

}
