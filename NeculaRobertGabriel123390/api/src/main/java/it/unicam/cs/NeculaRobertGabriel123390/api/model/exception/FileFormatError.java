package it.unicam.cs.NeculaRobertGabriel123390.api.model.exception;


/**
 * Custom exception to be thrown when there is a format error in a file.
 * This exception is used to indicate that the file's format does not meet the required specifications.
 */
public class FileFormatError extends RuntimeException {


    /**
     * Constructs a new FileFormatError with the specified detail message.
     *
     * @param message The detail message which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public FileFormatError(String message) {
        super(message);
    }

}
