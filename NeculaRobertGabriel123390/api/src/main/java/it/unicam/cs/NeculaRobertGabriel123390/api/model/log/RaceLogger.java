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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.log;


import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


/**
 * Provides logging functionality for race events.
 *
 * <p>The {@code RaceLogger} class is responsible for displaying log messages related
 * to race events. It uses a {@link TextFlow} to present messages in a user interface.
 * Log messages are associated with specific colors to indicate different types of events.</p>
 *
 * <p>To use this logger, initialize it with a {@code TextFlow} instance, and then
 * log events using {@code RaceLog} implementations. Each log message will be added to
 * the {@code TextFlow} with the appropriate color.</p>
 *
 */
public class RaceLogger {


    private static TextFlow raceLogArea;


    private static ScrollPane raceLogScrollPane;


    /**
     * Variable that is used to check if the Logger is already Initialized
     */
    public static boolean isInitialized = false;


    /**
     * Don't allow user to instantiate a RaceLogger object because that's not how this class is meant to work.
     */
    private RaceLogger() {}


    /**
     * Initializes the {@code RaceLogger} with a {@code TextFlow} instance.
     *
     * <p>This method sets the {@code TextFlow} where log messages will be displayed.
     * It must be called before logging any events to ensure that messages are properly
     * displayed.</p>
     *
     * @param raceLogArea           The {@code TextFlow} instance used to display log messages.
     * @param raceLogScrollPane The {@code ScrollPane} instance used to make the raceLogArea scrollable
     */
    public static void initialize(TextFlow raceLogArea, ScrollPane raceLogScrollPane) {
        if(isInitialized)
            throw new IllegalStateException("RaceLogger has already been initialized.");

        validateAreas(raceLogArea, raceLogScrollPane);
        RaceLogger.raceLogArea = raceLogArea;
        RaceLogger.raceLogScrollPane = raceLogScrollPane;
        isInitialized = true;
    }


    /**
     * Validates the {@code TextFlow} and {@code ScrollPane} instances.
     *
     * <p>This method ensures that both the {@code TextFlow} and {@code ScrollPane} are not null
     * and that the {@code ScrollPane} contains the {@code TextFlow} as its content.</p>
     *
     * @param raceLogArea The {@code TextFlow} to validate.
     * @param raceLogScrollPane The {@code ScrollPane} to validate.
     * @throws NullPointerException if {@code raceLogArea} or {@code raceLogScrollPane} is null.
     * @throws IllegalArgumentException if {@code raceLogScrollPane} does not contain the {@code TextFlow}.
     */
    private static void validateAreas(TextFlow raceLogArea, ScrollPane raceLogScrollPane){

        if(raceLogArea == null)
            throw new NullPointerException("the log area for the race is null");

        if(raceLogScrollPane == null)
            throw new NullPointerException("Scroll pane is null");

        if(raceLogScrollPane.getContent() == null)
            throw new IllegalArgumentException("The scroll pane does not contain the log area");
    }


    /**
     * Validates the {@code RaceLog} instance.
     *
     * @param raceLog The {@code RaceLog} instance to validate.
     * @throws NullPointerException if {@code raceLog} or its message or color is null.
     */
    private static void validateRaceLog(RaceLog raceLog) {
        if(raceLog == null)
            throw new NullPointerException("the log area for the race is null");
        if(raceLog.getMessage() == null)
            throw new NullPointerException("The message for the race is null");
        if(raceLog.getColor() == null)
            throw new NullPointerException("The color for the race is null");
    }


    /**
     * Logs a {@code RaceLog} message to the {@code TextFlow}.
     *
     * <p>This method creates a {@code Text} element for the provided {@code RaceLog}
     * and adds it to the {@code TextFlow}. The message is styled with the color specified
     * by the {@code RaceLog} implementation.</p>
     *
     * @param raceLog The {@code RaceLog} instance containing the message and color to log.
     */
    public static void log(RaceLog raceLog) {
        validateAreas(raceLogArea, raceLogScrollPane);
        validateRaceLog(raceLog);

        Text text = createText(raceLog);
        raceLogArea.getChildren().add(text);

        scrollToEnd();
    }


    /**
     * Creates a {@code Text} object from a {@code RaceLog} instance.
     *
     * <p>This method configures the {@code Text} object with the message and color specified
     * by the {@code RaceLog} implementation.</p>
     *
     * @param raceLog The {@code RaceLog} instance containing the message and color for the {@code Text}.
     * @return The configured {@code Text} object.
     */
    private static Text createText(RaceLog raceLog) {
        Text text = new Text();
        text.setText(raceLog.getMessage() + "\n");
        text.setFill(raceLog.getColor());
        return text;
    }


    /**
     * Scrolls the {@code ScrollPane} to the end.
     *
     * <p>This method ensures that the {@code ScrollPane} is scrolled to the bottom,
     * displaying the most recent log message.</p>
     */
    private static void scrollToEnd() {
        raceLogScrollPane.setVvalue(1.0);}
}
