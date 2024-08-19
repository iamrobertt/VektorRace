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
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import javafx.scene.paint.Color;


/**
 * Provides a log entry for a crash event where a player is banned from the race after going off the track.
 *
 * <p>This class extends {@link GenericCrashLog} and adds specific information indicating that the player
 * has been disqualified from the race following the crash. It includes details about the player's position
 * and indicates that the player is banned from continuing in the race.</p>
 *
 * <p>The log entry message includes information about the crash location and specifies that the player
 * is banned from the race, providing a clear and urgent color to indicate this critical event.</p>
 *
 */
public final class LeaveRaceCrashLog extends GenericCrashLog {


    /**
     * Constructs a {@code LeaveRaceCrashLog} instance with the specified player and position.
     *
     * @param player The player who has crashed and is banned from the race.
     * @param position The position where the crash occurred.
     */
    public LeaveRaceCrashLog(Player player, Position position) {
        super(player, position);
    }


    /**
     * Provides a message describing the crash event where the player is banned from the race.
     *
     * <p>The message includes the crash location and indicates that the player is banned from continuing in the race.</p>
     *
     * @return A string message that provides details about the crash and the player's disqualification.
     */
    @Override
    public String getMessage(){return super.getMessage() + "\nPlayer is banned from the race";}


    /**
     * Provides the color associated with a critical crash event where the player is banned from the race.
     *
     * @return The color {@code Color.RED}, which represents the severity of the event.
     */
    @Override
    public Color getColor(){return Color.RED;}
}
