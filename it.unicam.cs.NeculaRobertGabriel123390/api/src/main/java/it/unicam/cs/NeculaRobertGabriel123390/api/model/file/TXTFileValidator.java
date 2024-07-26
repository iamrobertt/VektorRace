package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitNodeUtils;
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
        if (!checkFileFormat()) return false;
        if (!checkPlayersFormat()) return false;
        if (!checkCircuitFormat()) return false;

        return true;
    }


    /**
     * Method that checks if filedata is TXTParsedFileData type
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
     *
     * @return True if the format is correct, False otherwise
     */
    private boolean checkFileFormat() {
        if (this.dataToList.isEmpty()) return false;
        if (!this.dataToList.getFirst().equals("::CIRCUIT")) return false;
        if (!this.dataToList.get(CircuitSetup.MAX_NODES_Y + 1).equals("::PLAYERS")) return false;
        if (this.dataToList.size() - 1 != CircuitSetup.LAST_ROW) return false;
        return true;
    }


    /**
     * Method that checks if the player data is correctly inserted into the txt file
     *
     * @return - True if the player data is correctly inserted, False otherwise
     */
    private boolean checkPlayersFormat() {
        String playersString = this.dataToList.get(CircuitSetup.PLAYERS_NUMBER_ROW);
        if (PlayerUtils.getNumberOfBotPlayers(playersString) <= 0
                && PlayerUtils.getNumberOfHumanPlayers(playersString) <= 0)
            return false;

        return this.dataToList.get(CircuitSetup.PLAYERS_NUMBER_ROW).matches(PlayerUtils.getRegexForPlayers());

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
     * Method that checks if the line passed has exactly Circuit.MAX_NODES_X character (the max)
     *
     * @param line - A line of the file
     * @return True if length is exactly Circuit.MAX_NODES_X
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
        return line.matches(CircuitNodeUtils.getRegexForNode());
    }

}
