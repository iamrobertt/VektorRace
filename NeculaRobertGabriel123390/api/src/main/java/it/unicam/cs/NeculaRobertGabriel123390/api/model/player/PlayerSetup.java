package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;

import java.util.List;


/**
 * Interface for setting up players from file data.
 * <p>
 * This interface defines a method to create a list of players from the provided parsed file data.
 * Implementations of this interface should handle the specifics of extracting player information
 * from different file formats.
 * </p>
 */
public interface PlayerSetup {

    /**
     * Sets up players based on the provided file data.
     *
     * @param fileData The data from the file, which must be of a format supported by the implementation.
     * @return A list of {@link Player} objects created based on the file data.
     * @throws IllegalArgumentException If the fileData cannot be processed by this setup implementation.
     */
    List<Player> setup(ParsedData<?> fileData);
}
