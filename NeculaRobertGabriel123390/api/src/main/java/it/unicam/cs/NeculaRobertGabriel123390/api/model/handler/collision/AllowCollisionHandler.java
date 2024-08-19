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
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLogger;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.success.SuccessMoveLog;


/**
 * The {@code AllowCollisionHandler} class is a specialized {@link CollisionHandler}
 * that disables COLLISION detection between players during a race. This class is
 * intended to be used when the rules of the race permit players to move through
 * each other without triggering a COLLISION event.
 *
 * <p>In this implementation, the {@link #handle(Position, Position)}
 * method always returns {@code null}, indicating that no COLLISION check should be
 * performed. Consequently, the {@link #logEvent(Position)} method
 * will log a successful move without any COLLISION-related event.</p>
 */
public final class AllowCollisionHandler extends CollisionHandler {


    /**
     * Disables COLLISION detection by always returning {@code null}. This method
     * is invoked when the race rules allow players to move without colliding with
     * each other.
     *
     * @param prevPosition the player's position before the move
     * @param newPosition the player's position after the move
     * @return {@link MoveResult} the move result with COLLISION_ALLOWED movetype
     */
    @Override
    public MoveResult handle(Position prevPosition, Position newPosition) {
        validateData(prevPosition, newPosition, this.raceManager);
        if(!this.raceManager.getCircuitManager().isColliding(newPosition)) return null;

        Position movePosition = new Position(newPosition.getX() - prevPosition.getX(), newPosition.getY() - prevPosition.getY());
        logEvent(newPosition);
        MoveResult moveResult = new MoveResult(this.raceManager.getCurrentPlayer(), prevPosition, MoveResultType.COLLISION_ALLOWED);
        updateState(prevPosition, movePosition);

        return moveResult;
    }


    /**
     * Logs a successful move event. Since collisions are not detected by this handler,
     * this method will log the move as successful without any COLLISION-related details.
     *
     * @param newPosition the player's position after the move
     */
    @Override
    public void logEvent(Position newPosition) {
        RaceLogger.log(new SuccessMoveLog(this.raceManager.getCurrentPlayer(), newPosition));
    }


    /**
     * Updates the state of the race after a successful move. This includes making the move
     * for the current player, updating the circuit nodes, and continuing to the next player.
     *
     * @param prevPosition The position of the player before the move.
     */
    private void updateState(Position prevPosition, Position movePosition){
        this.raceManager.getPlayersManager().makeMove(movePosition);
        this.raceManager.getCircuitManager().updateCircuitAfterMove(prevPosition, movePosition);
        this.raceManager.updateCurrentPlayer();
    }

}
