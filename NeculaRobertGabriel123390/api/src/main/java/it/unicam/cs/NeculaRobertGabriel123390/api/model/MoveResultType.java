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
 * Enumeration representing the possible outcomes of a player's move in the game.
 * <p>
 * This enum categorizes the result of a move into several types, which helps in
 * determining how the game should respond to the move and what messages or
 * actions should be triggered.
 * </p>
 */
public enum MoveResultType {


    /**
     * Indicates that the player collided with another player, but the collision are allowed in the race.
     */
    COLLISION_ALLOWED,


    /**
     * Indicates that the player collided with another player.
     */
    COLLISION_NOT_ALLOWED,


    /**
     * Indicates that the player crashed, meaning they went off the track or hit a wall.
     * <p>
     * This outcome signifies the player being removed from the race or facing other penalties.
     * </p>
     */
    CRASH_LEAVE_RACE,


    /**
     * Indicates that the player crashed but will continue in the race with a speed penalty.
     */
    CRASH_CONTINUE_WITH_PENALTY,


    /**
     * Indicates that the player successfully completed the move without encountering any issues.
     * <p>
     * This result signifies that the player's move was executed as intended, without any other events.
     * </p>
     */
    SUCCESS,


    /**
     * Indicates that the player has won the race.
     */
    WIN
}
