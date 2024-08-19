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

import javafx.scene.control.TextArea;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * Provides logging functionality for loading events and errors.
 *
 * <p>The {@code LoadingLogger} class is designed for logging messages related to loading
 * operations. It uses a {@link TextArea} to display messages in a user interface, including
 * error messages. The class supports basic message logging, error logging, and throwing
 * exceptions based on error messages.</p>
 *
 * <p>To use this logger, initialize it with a {@code TextArea} instance, and then log
 * messages or errors as needed. The class offers methods to append messages to the {@code TextArea},
 * log errors with a specific prefix, and throw exceptions after logging errors.</p>
 */
public class LoadingLogger {


    private static TextArea logArea;


    /**
     * Variable that is used to check if the Logger is already Initialized
     */
    public static boolean isInitialized = false;


    /**
     * Don't allow user to instantiate a LoadingLogger object because that's not how this class is meant to work.
     */
    private LoadingLogger() {}


    /**
     * Initializes the {@code LoadingLogger} with a {@code TextArea} instance.
     *
     * <p>This method sets the {@code TextArea} where log messages will be displayed.
     * It must be called before logging any messages or errors to ensure that they
     * are properly appended to the {@code TextArea}.</p>
     *
     * @param logArea The {@code TextArea} instance used to display log messages.
     * @throws IllegalStateException if the {@code TextArea} has been already initialed.
     */
    public static void initialize(TextArea logArea) {
        if(isInitialized)
            throw new IllegalStateException("Loading Logger has already been initialized.");
        validateLogArea(logArea);
        LoadingLogger.logArea = logArea;
        isInitialized = true;
    }


    /**
     * Validates the log area to ensure it's not null.
     *
     * @param logArea The log area to validate.
     * @throws NullPointerException if {@code logArea} is null.
     */
    private static void validateLogArea(TextArea logArea){
        if(logArea == null)
            throw new NullPointerException("logArea cannot be null");
    }


    /**
     * Validates that the provided message is neither null nor empty.
     *
     * @param message The message to validate.
     * @throws IllegalArgumentException if {@code message} is empty.
     * @throws NullPointerException if {@code message} is null.
     */
    private static void validateMessage(String message){
        if(message == null)
            throw new NullPointerException("message cannot be null");

        if(message.isEmpty())
            throw new IllegalArgumentException("message cannot be empty");

    }


    /**
     * Logs a general message to the {@code TextArea}.
     *
     * <p>This method appends the provided message to the {@code TextArea}, followed by a newline.</p>
     *
     * @param message The message to log.
     */
    public static void log(String message) {
        validateMessage(message);
        validateLogArea(logArea);
        logArea.appendText(message + "\n");
    }


    /**
     * Logs an error message to the {@code TextArea} with an "ERROR:" prefix.
     *
     * <p>This method prepends "ERROR: " to the provided message and appends it to the
     * {@code TextArea}, followed by a newline.</p>
     *
     * @param message The error message to log.
     */
    public static void logError(String message) {
        validateMessage(message);
        log("ERROR: " + message);
    }

    /**
     * Logs an error message and throws an exception to the specified type.
     *
     * <p>This method logs the error message using {@code logError}, then creates and throws
     * an exception of the specified class with the message. The exception class must have
     * a constructor that accepts a {@code String} parameter.</p>
     *
     * @param message The error message to log and include in the exception.
     * @param exceptionClass The class of the exception to throw.
     * @param <T> The type of the exception, which must extend {@link RuntimeException}.
     * @throws RuntimeException If the exception cannot be instantiated.
     */
    public static <T extends RuntimeException> void logErrorAndThrow(String message, Class<T> exceptionClass) {
        validateMessage(message);
        logError(message);

        try {
            Constructor<T> constructor = exceptionClass.getConstructor(String.class);
            throw constructor.newInstance(message);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Exception class does not have a constructor with a String argument", e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create exception", e);
        }
    }
}