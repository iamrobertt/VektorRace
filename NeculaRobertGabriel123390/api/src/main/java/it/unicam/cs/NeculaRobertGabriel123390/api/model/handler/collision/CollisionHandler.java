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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.collision;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;

/**
 * Abstract class that represents a COLLISION handler in the race management system.
 * This class extends the {@link RaceHandler} interface and provides a base
 * implementation for handling COLLISION-related logic during a race.
 *
 * <p>Concrete subclasses of {@code CollisionHandler} should implement specific
 * logic for handling collisions and logging related events. This class is used
 * to enforce a common structure for all COLLISION handlers, ensuring that they
 * follow a consistent interface and adhere to the expected behavior for COLLISION
 * management.</p>
 *
 * @see RaceHandler
 */
public abstract class CollisionHandler implements RaceHandler {


    /**
     * The raceManager that manages the current race.
     */
    protected RaceManager raceManager;


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
