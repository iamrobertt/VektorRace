package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedFileData;


/**
 * Class that supplies the player set upper based on fileData format
 */
public class PlayerSetupFactory {


    /**
     * Method that returns a PLayerSetup instance based on fileData
     * @param fileData - The parsed file data
     * @return PlayerSetup - The Player setup
     */
    public static PlayerSetup getPlayerSetup(ParsedFileData fileData) {

        if(fileData instanceof TXTParsedFileData)
            return new TXTPlayerSetup();

        throw new IllegalArgumentException("type of filedata not supported yet");
    }
}
