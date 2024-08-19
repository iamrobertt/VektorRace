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

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides an implementation of Bresenham's line drawing algorithm.
 * The algorithm is used to determine the points of a line between two given positions
 * on a 2D grid. It generates a list of positions representing the circuit nodes
 * that form the line from the start position to the end position.
 * Example usage:
 * <pre>
 * Position start = new Position(0, 0);
 * Position end = new Position(6, 4);
 * List&lt;Position&gt; linePositions = BresenhamLine.getPositionsBetween(start, end);
 * </pre>
 * The above code will produce a list of positions representing a line from (0,0) to (6,4).
 */
public final class BresenhamLineAlgorithm {


    /**
     * Generates a list of {@link Position} objects representing the points of a line
     * between two given positions on a 2D grid using Bresenham's algorithm.
     *
     * <p>Bresenham's algorithm is an efficient way to calculate the points of a line
     * between two positions in a grid without using floating-point arithmetic.</p>
     *
     * @param start The starting {@link Position} of the line.
     * @param end The ending {@link Position} of the line.
     * @return A list of {@link Position} objects representing the points of the line
     *         from the start to the end position, inclusive.
     */
    public static List<Position> getPositionsBetween(Position start, Position end) {
        PositionUtils.validateCircuitNodePosition(start);
        PositionUtils.validateCircuitNodePosition(end);

        List<Position> positions = new ArrayList<>();

        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            positions.add(new Position(x1, y1)); //adding current position

            if (x1 == x2 && y1 == y2) //if I've reached the last node, exit
                break;

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }

        }

        return positions;
    }

}
