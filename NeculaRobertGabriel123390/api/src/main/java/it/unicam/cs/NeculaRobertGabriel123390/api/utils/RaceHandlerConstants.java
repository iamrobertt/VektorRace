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


/**
 * Class containing constant values for race handling configurations.
 * <p>
 * This class provides predefined constant values used to specify various race handling behaviors such as
 * COLLISION rules, crash outcomes, and win conditions.
 * </p>
 */
public final class RaceHandlerConstants {


    /**
     * Don't allow user to instantiate a RaceHandlerConstants object because that's not how this class is meant to work.
     */
    private RaceHandlerConstants() {}


    /**
     * Constant for allowing collisions between race participants.
     */
    public static final String COLLISION_ALLOWED = "true";


    /**
     * Constant for not allowing collisions between race participants.
     */
    public static final String COLLISION_NOT_ALLOWED = "false";


    /**
     * Constant indicating that a player should leave the race if a crash occurs.
     */
    public static final String CRASH_LEAVE_RACE = "leave race";


    /**
     * Constant indicating that a player should continue the race with a penalty if a crash occurs.
     */
    public static final String CRASH_CONTINUE_WITH_PENALTY = "continue with penalty";


    /**
     * Constant indicating that the win condition is the first player to cross the finish line.
     */
    public static final String WIN_FIRST_CROSSING_LINE = "first crossing line";
}
