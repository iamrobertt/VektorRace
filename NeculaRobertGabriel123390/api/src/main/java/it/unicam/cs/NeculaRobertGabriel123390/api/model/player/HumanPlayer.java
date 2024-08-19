package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;


import javafx.scene.paint.Color;


/**
 * A concrete implementation of {@link Player} representing a human player in the game.
 * <p>
 * This class extends {@link Player} and provides an implementation for a human player,
 * which is not considered a bot.
 * </p>
 */
public final class HumanPlayer extends Player {


    /**
     * Constructs a {@code HumanPlayer} with the specified name and color.
     *
     * @param playerName The name of the human player.
     * @param color The color associated with the human player.
     */
    public HumanPlayer(String playerName, Color color){
        super(playerName, color);
    }


    /**
     * Returns whether this player is a bot.
     * <p>
     * For {@code HumanPlayer}, this method always returns {@code false} as it represents
     * a human player.
     * </p>
     *
     * @return {@code false}, indicating that this player is not a bot.
     */
    @Override
    public boolean isBot() {
        return false;
    }

}
