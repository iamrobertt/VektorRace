package it.unicam.cs.NeculaRobertGabriel123390.api.model.log;

import javafx.scene.paint.Color;


/**
 * Represents a log entry for a race event, detailing the result of a move or other race-related events.
 * <p>
 * Implementations of this interface define how to format and color the log messages for display
 * in a {@link RaceLogger}. Each log entry includes a message and an associated color for visualization.
 * </p>
 */
public interface RaceLog {


    /**
     * Provides the message to be logged for a race event.
     * <p>
     * This method returns a string that represents the details of the race event, such as the result of a move,
     * COLLISION, or crash.
     * </p>
     *
     * @return The message describing the race event.
     */
    String getMessage();


    /**
     * Provides the color associated with the log message for visualization purposes.
     * <p>
     * This method returns a {@link Color} that represents the text color for the log message,
     * allowing different types of events to be visually distinguished.
     * </p>
     *
     * @return The color associated with the log message.
     */
    Color getColor();
}
