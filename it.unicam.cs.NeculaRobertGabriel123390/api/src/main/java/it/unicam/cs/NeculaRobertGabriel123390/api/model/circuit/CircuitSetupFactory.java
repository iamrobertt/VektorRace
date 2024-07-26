package it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;


/**
 * Class that supplies a CircuitSetup object based on fileData format
 */
public class CircuitSetupFactory {

    /**
     * Method that supplies a CircuitSetup based on fileData format
     * @param fileData - The parsed file data
     * @return CircuitSetup - The right circuit setup for the format of fileData
     */
    public static CircuitSetup getCircuitSetup(ParsedFileData fileData) {

        if(fileData instanceof TXTParsedFileData)
            return new TXTCircuitSetup();

        return null;
    }
}
