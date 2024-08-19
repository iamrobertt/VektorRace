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

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.collision.CollisionLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLogger;


/**
 * The {@code DoNotAllowCollisionHandler} class is a specialized {@link CollisionHandler}
 * that provides functionality for detecting and handling collisions between players
 * during a race. This handler checks if a move results in a COLLISION and generates
 * an appropriate {@link MoveResult} if a COLLISION is detected.
 *
 * <p>The {@link #handle(Position, Position)} method checks if the new
 * position of a player after a move collides with another player. If a COLLISION
 * is detected, it logs the COLLISION event and returns a {@link MoveResult} indicating
 * a COLLISION. If no COLLISION is detected, it returns {@code null}.</p>
 */
public final class DoNotAllowCollisionHandler extends CollisionHandler {




    /**
     * Checks if the move results in a COLLISION with another player. If a COLLISION is detected,
     * it logs the event and returns a {@link MoveResult} indicating a COLLISION.
     *
     * @param prevPosition the player's position before the move
     * @param newPosition the player's position after the move
     * @return a {@link MoveResult} indicating a COLLISION if a COLLISION is detected,
     *         {@code null} otherwise
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition) {
        validateData(prevPosition,newPosition,this.raceManager);

        if(!this.raceManager.getCircuitManager().isColliding(newPosition)) return null;

        logEvent(newPosition);
        return new MoveResult(this.raceManager.getCurrentPlayer(), prevPosition, MoveResultType.COLLISION_NOT_ALLOWED);
    }


    /**
     * Logs information about a COLLISION event.
     *
     * @param newPosition the player's position after the move
     */
    @Override
    public void logEvent(Position newPosition) {
        RaceLogger.log(new CollisionLog(this.raceManager.getCurrentPlayer(), newPosition));
    }

}
