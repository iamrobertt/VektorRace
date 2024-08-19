package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.UnsupportedFileException;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedData;

import java.util.HashMap;
import java.util.Map;


/**
 * Factory class for creating instances of {@link PlayerSetup}.
 * <p>
 * This factory provides {@link PlayerSetup} implementations based on the format of the provided
 * parsed file data.
 * </p>
 */
public final class PlayerSetupFactory {


    private static final Map<Class<? extends ParsedData<?>>, PlayerSetup> setups = new HashMap<>();

    static {
        setups.put(TXTParsedData.class, new TXTPlayerSetup());
    }


    /**
     * Returns a {@link PlayerSetup} instance based on the provided file data.
     *
     * @param fileData The parsed file data.
     * @return A {@link PlayerSetup} instance for the given file data format.
     * @throws IllegalArgumentException If the file data format is not supported.
     */
    public static PlayerSetup getPlayerSetup(ParsedData<?> fileData) {

        PlayerSetup setup = setups.get(fileData.getClass());
        if (setup == null)
            throw new UnsupportedFileException("Unsupported fileData format " + fileData.getClass().getName());

        return setup;
    }
}
