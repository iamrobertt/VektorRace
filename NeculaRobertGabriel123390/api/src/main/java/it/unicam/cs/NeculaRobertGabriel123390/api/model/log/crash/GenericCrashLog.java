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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.log.crash;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import javafx.scene.paint.Color;


/**
 * Provides a generic log entry for a crash event in a race, where a player has gone off the track.
 *
 * <p>This class implements the {@link RaceLog} interface and is intended to be extended by other classes
 * that require more specific information about crash events. It offers a basic log message and color
 * representation for crashes.</p>
 *
 * <p>The log entry contains information about the player involved in the crash and the position
 * where the crash occurred.</p>
 *
 * <p>Subclasses can extend this class to provide additional details or specific messages related to
 * different types of crash events.</p>
 *
 */
public class GenericCrashLog implements RaceLog {


    /**
     * The position where the crash happened.
     */
    protected final Position position;


    /**
     * The player that crashed.
     */
    protected final Player player;


    /**
     * Constructs a {@code GenericCrashLog} instance with the specified player and position.
     *
     * @param player The player who has crashed.
     * @param position The position where the crash occurred.
     */
    public GenericCrashLog(Player player, Position position) {
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
     * Provides a generic message describing the crash event. The message includes
     * information about the player and the position where the crash occurred.
     *
     * @return A string message describing the crash event.
     */
    @Override
    public String getMessage() {
        return "Player " + this.player.getName() + " crashed at : " + this.position;
    }


    /**
     * Provides the color associated with crash events.
     *
     * @return The color {@code Color.ORANGE}, which represents a crash.
     */
    @Override
    public Color getColor() {return Color.ORANGE;}


}