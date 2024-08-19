package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;

import javafx.scene.paint.Color;


/**
 * A concrete implementation of {@link Player} representing a bot player in the game.
 * <p>
 * This class extends {@link Player} and provides an implementation for a bot player,
 * which is always considered a bot.
 * </p>
 */
public final class BotPlayer extends Player{


    /**
     * Constructs a {@code BotPlayer} with the specified name and color.
     *
     * @param playerName The name of the bot player.
     * @param color The color associated with the bot player.
     */
    public BotPlayer(String playerName, Color color){
        super(playerName, color);
    }


    /**
     * Returns whether this player is a bot.
     * <p>
     * For {@code BotPlayer}, this method always returns {@code true} as it represents
     * a bot player.
     * </p>
     *
     * @return {@code true}, indicating that this player is a bot.
     */
    @Override
    public boolean isBot() {
        return true;
    }


}
