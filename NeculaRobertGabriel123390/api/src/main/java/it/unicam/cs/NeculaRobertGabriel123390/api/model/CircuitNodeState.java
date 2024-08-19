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
 * Enumeration representing the various states a node can have in the racing circuit.
 * <p>
 * Each state indicates a different type of node on the circuit, affecting how it interacts
 * with players and other elements of the track.
 * </p>
 */
public enum CircuitNodeState {


    /**
     * Indicates that the node is a standard part of the racing track.
     * <p>
     * Players can move through this node as part of the normal track layout.
     * </p>
     */
    trackNode,


    /**
     * Indicates that the node is not part of the racing track.
     * <p>
     * This node is used for areas outside the valid track or regions that are not
     * traversable.
     * </p>
     */
    nonTrackNode,


    /**
     * Indicates that the node is part of the track and specifically marks the start line.
     * <p>
     * This node represents a starting point of the race.
     * </p>
     */
    startNode,


    /**
     * Indicates that the node is part of the track and specifically marks the end line.
     * <p>
     * This node represents a finishing point of the race.
     * </p>
     */
    endNode,


    /**
     * Indicates that the node is part of the track and represents a wall or barrier.
     * <p>
     * If a player collides with this node, it results in a crash.
     * </p>
     */
    wallNode,


    /**
     * Indicates that the node is part of the track but is currently occupied by another player.
     * <p>
     * This state is used to track which nodes are occupied and prevent multiple players from
     * occupying the same node simultaneously.
     * </p>
     */
    occupied

}
