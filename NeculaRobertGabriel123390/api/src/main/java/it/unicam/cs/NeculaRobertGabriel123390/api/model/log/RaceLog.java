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
