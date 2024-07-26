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


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Enum that gives information about the type of the node
 */
public enum CircuitNodeState {


    /**
     * The node is part of the track
     */
    trackNode(Color.GREY),


    /**
     * The node isn't part of the track
     */
    nonTrackNode(Color.WHITE),


    /**
     * The node is part of the track and the startLine
     */
    startNode(Color.YELLOW),


    /**
     * The node is part of the track and the endline
     */
    endNode(Color.RED),


    /**
     * The node is part of the track, and it's a wall, resulting in a crash when hit
     */
    wallNode(Color.BLACK);

    private final Color color;


    CircuitNodeState(Color color) {
        this.color = color;
    }


    public void applyStyle(Rectangle tile) {
        tile.setFill(this.color);
    }


}
