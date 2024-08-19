package it.unicam.cs.NeculaRobertGabriel123390.api.model.exception;

/**
 * Exception thrown when an unsupported file format is encountered.
 * This exception indicates that the file format does not match any of the supported formats.
 */
public class UnsupportedFileException extends RuntimeException {


    /**
     * Constructs a new UnsupportedFileFormatException with the specified detail message.
     *
     * @param message The detail message which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public UnsupportedFileException(String message) {
        super(message);
    }
}
