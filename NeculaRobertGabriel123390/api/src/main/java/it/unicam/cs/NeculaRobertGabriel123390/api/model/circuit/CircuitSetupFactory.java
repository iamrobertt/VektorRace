package it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.UnsupportedFileException;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;

import java.util.HashMap;
import java.util.Map;


/**
 * Factory class that provides a {@link CircuitSetup} instance based on the format of the provided parsed file data.
 * It supports various file formats by associating them with specific {@link CircuitSetup} implementations.
 */
public final class CircuitSetupFactory {


    private static final Map<Class<? extends ParsedData<?>>, CircuitSetup> setups = new HashMap<>();


    static {
        setups.put(TXTParsedData.class, new TXTCircuitSetup());
    }

    /**
     * Provides a {@link CircuitSetup} instance based on the format of the given parsed file data.
     *
     * @param fileData the parsed file data.
     * @return the appropriate {@link CircuitSetup} for the file data format.
     * @throws UnsupportedFileException if the file data format is not supported.
     * @throws IllegalArgumentException if the provided file data is null.
     */
    public static CircuitSetup getCircuitSetup(ParsedData<?> fileData) {
        validateFileData(fileData);

        CircuitSetup setup = setups.get(fileData.getClass());
        if (setup == null)
            throw new UnsupportedFileException("Unsupported fileData format " + fileData.getClass().getName());

        return setup;
    }


    /**
     * Validates the provided parsed file data.
     *
     * @param fileData the parsed file data to validate.
     * @throws IllegalArgumentException if the provided file data is null.
     */
    private static void validateFileData(ParsedData<?> fileData) {
        if(fileData == null)
            throw new NullPointerException("Given ParsedFileData is null");
    }
}
