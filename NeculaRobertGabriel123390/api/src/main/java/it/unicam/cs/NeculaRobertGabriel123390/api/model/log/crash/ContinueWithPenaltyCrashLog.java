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


/**
 * Represents a log entry for a crash event where the player continues the race with a penalty.
 * This log is used to provide information about a player who has crashed, meaning they went off the track,
 * and will restart from their previous position with a penalty.
 *
 * <p>This class extends {@link GenericCrashLog} and adds specific details related to the player's
 * continuation from their previous position after a crash.</p>
 *
 * <p>The log entry includes a descriptive message about the crash and the player's penalty, which
 * indicates that the player will resume from their last valid position.</p>
 */
public final class ContinueWithPenaltyCrashLog extends GenericCrashLog {


    /**
     * Constructs a {@code ContinueWithPenaltyCrashLog} instance with the specified player and position.
     *
     * @param player The player who has crashed and will continue the race with a penalty.
     * @param position The position where the crash occurred.
     */
    public ContinueWithPenaltyCrashLog(Player player, Position position) {
        super(player, position);
    }


    /**
     * Provides a message describing the crash event and the player's penalty. The message includes
     * information about the player's crash, the position where it occurred, and indicates that the player
     * will restart from their previous position.
     *
     * @return A string message describing the crash event and penalty.
     */
    @Override
    public String getMessage() {return super.getMessage() + "\n" + this.player.getName() + " will start again from its previous position " + this.player.getPosition();}

}
