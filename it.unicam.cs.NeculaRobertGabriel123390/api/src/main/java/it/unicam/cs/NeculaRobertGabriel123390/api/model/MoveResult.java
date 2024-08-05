package it.unicam.cs.NeculaRobertGabriel123390.api.model;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;


/**
 * Record that merge some player data, used to display players moves on the view
 * @param player - the player
 * @param prevPosition - the new position of the player
 */
public record MoveResult(Player player, Position prevPosition, MoveResultType moveType) {

    public MoveResult(Player player, Position prevPosition, MoveResultType moveType) {
        this.player = player;
        this.prevPosition = prevPosition;
        this.moveType = moveType;
    }
}
