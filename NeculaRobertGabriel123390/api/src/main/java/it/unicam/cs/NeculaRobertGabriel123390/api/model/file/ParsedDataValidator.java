package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.UnsupportedFileException;

/**
 * Interface used to validate a file based on the parsed data format.
 * Implementations of this interface should ensure that the provided
 * {@link ParsedData} meets all necessary conditions for setting up a race.
 */
public interface ParsedDataValidator {


    /**
     * Validates the given {@link ParsedData} to ensure it is suitable for setting up a race.
     * Implementations should check all relevant conditions specific to the file format, and
     * throw an appropriate exception if the data is invalid or does not meet the required criteria.
     *
     * @param fileData the parsed file data to validate
     * @throws IllegalArgumentException if the file data does not meet the required conditions
     * @throws UnsupportedFileException if the file format is not supported
     */
    void validateData(ParsedData<?> fileData);


}
