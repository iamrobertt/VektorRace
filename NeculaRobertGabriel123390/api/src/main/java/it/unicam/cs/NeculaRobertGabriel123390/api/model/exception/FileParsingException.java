package it.unicam.cs.NeculaRobertGabriel123390.api.model.exception;

/**
 * Custom exception for handling errors related to file parsing.
 */
public class FileParsingException extends RuntimeException {

    /**
     * Constructs a new FileParsingException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public FileParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}