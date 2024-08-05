package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.TXTCircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.FileFormatError;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PlayerUtils;


import java.util.List;
import java.util.ArrayList;


/**
 * Class that implements the FileValidator interface for a txt file
 */
public class TXTFileValidator implements FileValidator {

    private List<String> dataToList;


    /**
     * Method that checks if the given file is valid to create a race
     *
     * @return True if the file is valid, false otherwise
     */
    @Override
    public boolean isFileValid(ParsedFileData fileData) {

        validateFileData(fileData);
        this.dataToList = extractDataToList(fileData);
        checkFileFormat();
        if (!checkPlayersFormat()) return false;
        if (!checkCircuitFormat()) return false;

        return true;
    }


    /**
     * Method that checks if fileData is TXTParsedFileData type
     *
     * @param fileData - The given file
     */

    private void validateFileData(ParsedFileData fileData) {
        if (!(fileData instanceof TXTParsedFileData)) throw new IllegalArgumentException("FileData is not a TXTParsedFileData");
    }


    /**
     * Method that converts the fileData into a list
     *
     * @param fileData - The given file
     * @return - The file data in list format
     */
    public List<String> extractDataToList(ParsedFileData fileData) {
        return new ArrayList<String>(((TXTParsedFileData) fileData).getData());
    }


    /**
     * Method that checks the format of the txt file
     */
    private void checkFileFormat() {
        if (this.dataToList.isEmpty())
            throw new FileFormatError("Circuit cannot be empty");
        if (!this.dataToList.getFirst().equals("::CIRCUIT"))
            throw new FileFormatError("Circuit does not have the first line like ::CIRCUIT");
        if (!this.dataToList.get(CircuitSetup.MAX_NODES_Y + 1).equals("::PLAYERS"))
            throw new FileFormatError("The " + CircuitSetup.MAX_NODES_Y + " line in the file is not like ::PLAYERS");
        if (this.dataToList.size() - 1 != TXTCircuitSetup.LAST_ROW)
            throw new FileFormatError("The file provided is too long. The last row needs to be only populated by [name:color],[name:color] ... for human players");

    }


    /**
     * Method that checks if the player data is correctly inserted into the txt file
     *
     * @return - True if the player data is correctly inserted, False otherwise
     */
    private boolean checkPlayersFormat() {
        String playersString = this.dataToList.get(TXTCircuitSetup.PLAYERS_NUMBER_ROW);
        if (PlayerUtils.getBotCount(playersString) <= 0
                && PlayerUtils.getHumanCount(playersString) <= 0)
            return false;

        return this.dataToList.get(TXTCircuitSetup.PLAYERS_NUMBER_ROW).matches(getRegexForPlayers());

    }


    /**
     * Method that checks if the circuit data is correctly inserted into the txt file
     *
     * @return - True if the circuit data is correctly inserted, False otherwise
     */
    private boolean checkCircuitFormat() {
        for (int i = 1; i <= CircuitSetup.MAX_NODES_Y; i++) {

            String line = this.dataToList.get(i);

            if (!checkLineLength(line) ||
                    !checkLineSymbols(line))
                return false;
        }

        return true;
    }


    /**
     * Method that checks if the line passed has exactly CircuitSetup.MAX_NODES_X character (the max)
     *
     * @param line - A line of the file
     * @return True if length is exactly CircuitSetup.MAX_NODES_X
     */
    private boolean checkLineLength(String line) {
        return line.length() == CircuitSetup.MAX_NODES_X;
    }


    /**
     * Method that checks if the line passed has only @#+- symbols (passed through regExp)
     *
     * @param line - A line of the file
     * @return - True if the line contains only these symbols, false otherwise
     */
    private boolean checkLineSymbols(String line) {
        return line.matches(getRegexForNode());
    }


    /**
     * Method that supply a regex for characters that are accepted in the file
     * @return String the regex
     */
    public static String getRegexForNode(){
        return "[@*#+-]*";
    }


    /**
     * Method that supply a regex for players number in the file
     * @return String the regex
     */
    public static String getRegexForPlayers() {
        return "\\d+B \\d+H";
    }

}
