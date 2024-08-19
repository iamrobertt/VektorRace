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
