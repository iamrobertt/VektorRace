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

import it.unicam.cs.NeculaRobertGabriel123390.api.model.PlayerMoves;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import javafx.scene.paint.Color;


/**
 * Abstract base class representing a player in the game.
 * <p>
 * This class defines common properties and behaviors for all players in the game.
 * Each player has a name, color, position, and a record of moves made.
 * </p>
 */
public abstract class Player {

    private final String name;


    private final Color color;


    private Position position;


    private final PlayerMoves possibleMoves;


    /**
     * Constructs a Player with the specified name and color.
     *
     * @param name The name of the player.
     * @param color The color associated with the player.
     */
    public Player(String name, Color color) {
        PlayerValidator.validate(name, color);
        this.name = name;
        this.color = color;
        this.possibleMoves = new PlayerMoves();
    }


    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {return this.name;}


    /**
     * Returns the color associated with the player.
     *
     * @return The color of the player.
     */
    public Color getColor() {return this.color;}


    /**
     * Returns the current position of the player.
     *
     * @return The current position of the player.
     * @throws NullPointerException if the current position is null;
     * @throws IllegalArgumentException if the position is not valid into a circuit.
     */
    public Position getPosition() {
        PositionUtils.validateCircuitNodePosition(this.position);
        return this.position;
    }


    /**
     * Sets the position of the player.
     *
     * @param newPosition The new position of the player.
     */
    public void setPosition(Position newPosition) {
        PositionUtils.validateCircuitNodePosition(newPosition);
        this.position = newPosition;
    }


    /**
     * Returns the possible moves for the player.
     *
     * @return The possible moves for the player.
     */
    public PlayerMoves getPossibleMoves() {return this.possibleMoves;}


    /**
     * Checks is the instance of a player is a bot.
     * @return true if player is a BotPlayer, false otherwise
     */
    public abstract boolean isBot();


    /**
     * Compares this {@code Player} to the specified object for equality. Two {@code Player} instances are
     * considered equal if they have the same name, color, position, and possible moves.
     *
     * @param o the object to compare this {@code Player} against
     * @return {@code true} if the specified object is equal to this {@code Player}; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return this.name.equals(player.name)
                && this.color.equals(player.color)
                && this.position.equals(player.position)
                && this.possibleMoves.equals(player.possibleMoves);
    }


    @Override
    public int hashCode() {
        return 31*this.name.hashCode() + this.color.hashCode() + this.position.hashCode() + this.possibleMoves.hashCode();
    }


    @Override
    public String toString() {
        return "Player = name: " + this.name + ", color: " + this.color + ", " + this.position;
    }

}
