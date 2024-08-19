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

import java.util.List;


/**
 * Utility class for validating {@link Player} objects and related data.
 * <p>
 * This class provides static methods to ensure that {@code Player} instances, player names, colors,
 * and lists of players are valid. It helps in maintaining the integrity of player-related data.
 * </p>
 */
public final class PlayerValidator {


    /**
     * Don't allow user to instantiate a PlayerValidator object because that's not how this class is meant to work.
     */
    private PlayerValidator() {}


    /**
     * Validates the given {@code Player} object.
     * <p>
     * This method checks that the {@code Player} is not {@code null} and then validates
     * its name and color using the {@link #validate(String, Color)} method.
     * </p>
     *
     * @param player The {@code Player} object to validate. Must not be {@code null}.
     * @throws NullPointerException if {@code player} is {@code null} or if its name or color is invalid.
     */
    public static void validate(Player player) {
        if(player == null)
            throw new NullPointerException("player is null");

        validate(player.getName(), player.getColor());
    }


    /**
     * Validates the provided player name and color.
     * <p>
     * This method checks that the player name is not {@code null} or empty and that the color is not {@code null}.
     * </p>
     *
     * @param name The name of the player. Must not be {@code null} or empty.
     * @param color The color associated with the player. Must not be {@code null}.
     * @throws IllegalArgumentException if {@code name} is {@code null} or empty, or if {@code color} is {@code null}.
     */
    public static void validate(String name, Color color){
        if(name == null)
            throw new NullPointerException("Player name is null.");
        if(name.isEmpty())
            throw new IllegalArgumentException("Player name is empty");
        if(color == null)
            throw new NullPointerException("Player color is null");
    }


    /**
     * Validates a list of {@code Player} objects.
     * <p>
     * This method checks that the list of players is not {@code null} and not empty. It then validates each
     * individual player in the list using the {@link #validate(Player)} method.
     * </p>
     *
     * @param players The list of {@code Player} objects to validate. Must not be {@code null} or empty.
     * @throws NullPointerException if {@code players} is {@code null}.
     * @throws IllegalArgumentException if {@code players} is empty or if any player in the list is invalid.
     */
    public static void validate(List<Player> players){
        if(players == null)
            throw new NullPointerException("Players is null");

        if(players.isEmpty())
            throw new IllegalArgumentException("Players is empty");

        for(Player player : players)
            validate(player);
    }

}
