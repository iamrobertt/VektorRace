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
