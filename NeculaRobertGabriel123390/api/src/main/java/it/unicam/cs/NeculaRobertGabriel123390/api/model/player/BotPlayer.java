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
