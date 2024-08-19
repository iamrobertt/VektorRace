package it.unicam.cs.NeculaRobertGabriel123390.api.model.log.collision;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLog;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import javafx.scene.paint.Color;


/**
 * Represents a log entry for a COLLISION event in the race. This log is used to
 * provide information about a player who is about to collide with another player
 * if they make a particular move.
 *
 * <p>This class implements the {@link RaceLog} interface and encapsulates details
 * about the COLLISION, including the player involved and the position where the
 * COLLISION is expected to occur.</p>
 *
 * <p>The log entry includes a descriptive message and a color code used to visually
 * represent the COLLISION event in the logging system.</p>
 *
 */
public final class CollisionLog implements RaceLog {

    private final Position position;
    private final Player player;


    /**
     * Constructs a {@code CollisionLog} instance with the specified player and position.
     *
     * @param player The player who is about to collide with another player.
     * @param position The position where the COLLISION is expected to occur.
     */
    public CollisionLog(Player player, Position position) {
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
     * Provides a message describing the COLLISION event. The message indicates that
     * the specified player will collide at the given position and informs that the
     * move has not been saved.
     *
     * @return A string message describing the COLLISION event.
     */
    @Override
    public String getMessage() {return this.player.getName() + " will collide going at " + this.position + ".\nThe move has not been saved, try again.";}


    /**
     * Provides the color associated with the COLLISION event. This color can be used
     * for visual representation in the logging system or UI to indicate the nature of
     * the event.
     *
     * @return The color associated with the COLLISION event.
     */
    @Override
    public Color getColor() {return Color.GOLDENROD;}
}