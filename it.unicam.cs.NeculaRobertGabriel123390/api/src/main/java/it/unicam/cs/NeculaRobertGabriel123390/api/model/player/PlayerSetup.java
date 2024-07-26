package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;

import java.util.List;


/**
 * Interface that sets up players based on the file extension given
 */
public interface PlayerSetup {

    /**
     * Method that setups players list from given data
     * @return List<Player> - A list of players
     */
    List<Player> setup(ParsedFileData fileData);
}
