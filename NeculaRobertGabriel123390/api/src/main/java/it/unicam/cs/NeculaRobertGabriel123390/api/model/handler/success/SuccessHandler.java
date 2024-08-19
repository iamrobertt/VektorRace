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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.success;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;

/**
 * Abstract class representing a handler for successful moves in the race management system.
 * This class extends {@link RaceHandler} and serves as a base implementation for handling
 * moves that have been successfully completed, assuming no other handlers (such as COLLISION
 * or crash handlers) have been triggered.
 *
 * <p>Concrete subclasses of {@code SuccessHandler} should implement specific logic for
 * handling successful moves, including updating the race state and logging relevant information.
 * If there are new implementation of race logic (like assigning points or other things), a new {@link SuccessHandler}
 * can be created to manage the new features.</p>
 *
 * @see RaceHandler
 */
public abstract class SuccessHandler implements RaceHandler{


    /**
     * The raceManager that manages the current race.
     */
    protected RaceManager raceManager;


    /**
     * Updates the state of the race after a successful move. The implementation can vary based on the success handler.
     * @param prevPosition The position of the player before the move.
     * @param movePosition The move made by player
     */
    public abstract void updateState(Position prevPosition, Position movePosition);


    /**
     * Sets the race manager for the handler. Checks also if the manager is already initialized
     * @param raceManager The {@link RaceManager} to set to the handler
     */
    @Override
    public void setRaceManager(RaceManager raceManager) {
        if(this.raceManager != null)
            return;
        this.raceManager = raceManager;
    }
}
