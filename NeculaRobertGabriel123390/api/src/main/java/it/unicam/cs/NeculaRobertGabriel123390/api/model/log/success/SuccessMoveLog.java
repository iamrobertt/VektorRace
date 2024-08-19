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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.log.success;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import javafx.scene.paint.Color;


/**
 * Represents a log entry for a successful move made by a player in the race.
 *
 * <p>This class implements the {@link RaceLog} interface to provide information about a player
 * who has successfully moved to a new position in the circuit without encountering a COLLISION
 * or crash. It includes details about the player's new position and uses a neutral color to indicate
 * a successful move.</p>
 *
 * <p>The log entry message provides a clear indication of the player's progress in the race.</p>
 */
public final class SuccessMoveLog implements RaceLog {


    private final Position position;


    private final Player player;


    /**
     * Constructs a {@code SuccessMoveLog} instance with the specified player and position.
     *
     * @param player The player who made the successful move.
     * @param position The position where the player has moved to.
     */
    public SuccessMoveLog(Player player, Position position) {
        validateData(player, position);
        this.player = player;
        this.position = position;
    }


    /**
     * Validates the player and position data to ensure they are not null and that the position is valid.
     *
     * @param player The player to validate.
     * @param position The position to validate.
     * @throws NullPointerException if {@code player} or {@code position} is null.
     * @throws IllegalArgumentException if {@code position} is invalid.
     */
    private void validateData(Player player, Position position) {
        PlayerValidator.validate(player);
        PositionUtils.validateCircuitNodePosition(position);
    }


    /**
     * Provides a message describing the successful move made by the player.
     *
     * <p>The message includes the player's name and their new position on the circuit.</p>
     *
     * @return A string message indicating the successful move.
     */
    @Override
    public String getMessage() {
        return "Player " + this.player.getName() + " moved at : " + this.position;
    }


    /**
     * Provides the color associated with a successful move event.
     *
     * <p>This method returns {@code Color.BLACK} to indicate a neutral event, signifying that the move was successful
     * without any issues.</p>
     *
     * @return The color {@code Color.BLACK}.
     */
    @Override
    public Color getColor() {return Color.BLACK;}
}
