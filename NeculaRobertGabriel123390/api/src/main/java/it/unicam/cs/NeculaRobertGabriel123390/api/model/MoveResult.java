package it.unicam.cs.NeculaRobertGabriel123390.api.model;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;


/**
 * A record representing the result of a player's move within the game.
 * <p>
 * This record encapsulates information about a player's movement, including the player
 * involved, their previous/new position, and the type of move made. This information is used
 * for updating and displaying the player's state on the view.
 * The {@code prevOrNewPosition} is a value that can be assigned based on the situation.
 * For example, if a move succeeds, the current player position is updated by the manager, so i can store its previous
 * position to update the view. If the player crashes, I may want to store the new position in the variable because its current
 * position is not updated in the manager.
 * </p>
 *
 *
 * @param player The player who made the move.
 * @param prevOrNewPosition The previous/new {@link Position} of the player before or after the move.
 * @param moveType The type of move made by the player, represented by {@link MoveResultType}.
 */
public record MoveResult(Player player, Position prevOrNewPosition, MoveResultType moveType) {


    /**
     * Constructs a {@code MoveResult} instance with the specified player, previous position, and move type.
     *
     * @param player The player who made the move.
     * @param prevOrNewPosition The previous/new {@link Position} of the player before the move.
     * @param moveType The type of move made by the player.
     * @throws NullPointerException if any of the parameters are {@code null}.
     */
    public MoveResult {
        validate(player, prevOrNewPosition, moveType);
    }


    /**
     * Validates the provided player, previous position, and move type for the move result.
     *
     * @param player The player to validate. Must not be {@code null}.
     * @param prevOrNewPosition The previous/new {@link Position} to validate. Must not be {@code null}.
     * @param moveType The move type to validate. Must not be {@code null}.
     * @throws NullPointerException if any of the parameters are {@code null}.
     */
    private void validate(Player player, Position prevOrNewPosition, MoveResultType moveType){
        PlayerValidator.validate(player);
        PositionUtils.validateCircuitNodePosition(prevOrNewPosition);

        if(moveType == null)
            throw new NullPointerException("Move Type is null");
    }
}
