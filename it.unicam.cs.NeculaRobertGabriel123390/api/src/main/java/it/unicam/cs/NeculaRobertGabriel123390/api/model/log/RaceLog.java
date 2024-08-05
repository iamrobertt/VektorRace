package it.unicam.cs.NeculaRobertGabriel123390.api.model.log;

import javafx.scene.paint.Color;


/**
 * Interface that represents a race log, which is the result of the moves chosen by the user
 */
public interface RaceLog {


    /**
     * Method that provides the message for a logger
     *
     * @return String the message
     */
    String getMessage();


    /**
     * Method that provides the text color for a logger
     * @return Color the color associated with the event
     */
    Color getColor();
}
