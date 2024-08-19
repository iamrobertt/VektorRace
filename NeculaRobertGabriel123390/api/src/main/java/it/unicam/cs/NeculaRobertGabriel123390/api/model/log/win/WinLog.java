package it.unicam.cs.NeculaRobertGabriel123390.api.model.log.win;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import javafx.scene.paint.Color;


/**
 * Represents a log entry for a player who has won the race.
 *
 * <p>This class implements the {@link RaceLog} interface to provide information about a player
 * who has achieved victory in the race. It includes details about the winning player and uses
 * a color to indicate a win.</p>
 *
 * <p>The log entry message clearly announces the winner of the race.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     // Create a player instance
 *     Player player = new Player("Jane Doe");
 *
 *     // Create a WinLog instance
 *     WinLog winLog = new WinLog(player);
 *
 *     // Retrieve the log message
 *     String message = winLog.getMessage();
 *
 *     // Use the log information in the RaceLogger or UI
 *     RaceLogger.log(message);  // hypothetical logging method
 * </pre>
 */
public final class WinLog implements RaceLog {


    private final Player player;


    /**
     * Constructs a {@code SuccessMoveLog} instance with the specified player and position.
     *
     * @param player The player who made the successful move.
     */
    public WinLog(Player player) {
        validateData(player);
        this.player = player;
    }


    /**
     * Validates the player and position data to ensure they are not null and that the position is valid.
     *
     * @param player The player to validate.
     * @throws NullPointerException if {@code player} is null.
     */
    private void validateData(Player player) {
        PlayerValidator.validate(player);
    }

    /**
     * Provides a message announcing the winner of the race.
     *
     * <p>The message includes the name of the winning player.</p>
     *
     * @return A string message indicating that the player has won the race.
     */
    @Override
    public String getMessage() {return this.player.getName() + " has won the race.";}


    /**
     * Provides the color associated with a win event.
     *
     * <p>This method returns {@code Color.GREEN} to indicate a successful and positive outcome,
     * signifying that the player has won the race.</p>
     *
     * @return The color {@code Color.GREEN}.
     */
    @Override
    public Color getColor() {return Color.GREEN;}

}
