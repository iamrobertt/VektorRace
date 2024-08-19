/*
 * Copyright (c) 2024.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.LoadingLogger;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.TXTCircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.FileFormatError;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PlayerUtils;


import java.util.List;
import java.util.ArrayList;


/**
 * Implementation of the {@link ParsedDataValidator} interface for validating TXT file data.
 * <p>
 * This class provides methods to validate the format and content of a TXT file used
 * for creating a circuit. It ensures that the file matches the expected format,
 * including checking the circuit layout, player data, and file structure.
 * </p>
 */
public final class TXTParsedDataValidator implements ParsedDataValidator {


    private List<String> dataToList;


    private static final String SYMBOL_REGEX = "[@*#+-]*";


    /**
     * Validates the given file data to ensure it meets the requirements for setting up a race.
     * This method performs various checks including:
     * <ul>
     *     <li>File format verification</li>
     *     <li>Player data format validation</li>
     *     <li>Circuit data format validation</li>
     * </ul>
     * If any validation fails, an appropriate exception is thrown.
     * @param fileData the parsed file data to be validated
     */
    @Override
    public void validateData(ParsedData<?> fileData) {
        validateFileData(fileData);
        this.dataToList = extractDataToList(fileData);
        checkFileFormat();
        checkPlayersFormat();
        checkCircuitFormat();
    }


    /**
     * Validates that the given file data is an instance of {@link TXTParsedData}.
     * <p>
     * This check ensures that the data format is compatible with TXT file parsing.
     * </p>
     *
     * @param fileData the parsed file data to be validated.
     * @throws NullPointerException if the file data is null.
     * @throws IllegalArgumentException if the file data is not of type {@link TXTParsedData}
     */
    private void validateFileData(ParsedData<?> fileData) {
        if(fileData == null)
            throw new NullPointerException("fileData is null");
        if (!(fileData instanceof TXTParsedData))
            throw new IllegalArgumentException("FileData is not a TXTParsedFileData");
    }


    /**
     * Converts the given file data into a list of strings, where each string represents a line from the file.
     * <p>
     * This transformation allows for easier processing and validation of the file content.
     * </p>
     *
     * @param fileData the parsed file data
     * @return a list of strings representing the file content
     */
    public List<String> extractDataToList(ParsedData<?> fileData) {
        return new ArrayList<>(((TXTParsedData) fileData).getData());
    }


    /**
     * Checks the overall format of the TXT file, including header lines and file length.
     * <p>
     * This method verifies:
     * <ul>
     *     <li>The presence of the "::CIRCUIT" and "::PLAYERS" markers</li>
     *     <li>The correct number of lines in the file</li>
     * </ul>
     * </p>
     */
    private void checkFileFormat() {
        if (this.dataToList.isEmpty())
            LoadingLogger.logErrorAndThrow("Circuit cannot be empty", FileFormatError.class);
        if (!this.dataToList.getFirst().equals("::CIRCUIT"))
            LoadingLogger.logErrorAndThrow("Circuit does not have the first line equal to ::CIRCUIT", FileFormatError.class);
        if (!this.dataToList.get(CircuitSetup.MAX_NODES_Y + 1).equals("::PLAYERS"))
            LoadingLogger.logErrorAndThrow("The " + CircuitSetup.MAX_NODES_Y + " line in the file is not like ::PLAYERS", FileFormatError.class);
        if (this.dataToList.size() - 1 != TXTCircuitSetup.LAST_ROW)
            LoadingLogger.logErrorAndThrow("The file provided is too long. The last row needs to be only populated by [name:color],[name:color] ... for human players", FileFormatError.class);

    }


    /**
     * Validates the player data format within the TXT file.
     * <p>
     * This method ensures that there are enough players specified and that the format
     * of the player data matches the expected pattern.
     * </p>
     */
    private void checkPlayersFormat() {
        String playersString = this.dataToList.get(TXTCircuitSetup.PLAYERS_COUNT_ROW);
        if (PlayerUtils.getBotCount(playersString) <= 0
                && PlayerUtils.getHumanCount(playersString) <= 0)
            LoadingLogger.logErrorAndThrow("Insufficient player count given. At least 2 player's are needed to create a race.", FileFormatError.class);

        if (!this.dataToList.get(TXTCircuitSetup.PLAYERS_COUNT_ROW).matches(PlayerUtils.PLAYER_COUNT_REGEX))
            LoadingLogger.logErrorAndThrow("The format of number of player needs to be xB yH where x is a non-negative number (>=0).", FileFormatError.class);

    }


    /**
     * Validates the circuit data format within the TXT file.
     * <p>
     * This method ensures that each line of the circuit data has the correct length
     * and contains only valid symbols.
     * </p>
     */
    private void checkCircuitFormat() {

        for (int i = 1; i <= CircuitSetup.MAX_NODES_Y; i++) {

            String line = this.dataToList.get(i);

            if (!checkLineLength(line))
                LoadingLogger.logErrorAndThrow("Circuit length is not valid at line " + i + ".\n" +
                        "Expected " + CircuitSetup.MAX_NODES_X + " characters, found " + line.length() + ".", FileFormatError.class);

            if (!checkLineSymbols(line))
                LoadingLogger.logErrorAndThrow("Invalid symbol found at line " + i + ".\n" +
                        "Only valid symbols are " + SYMBOL_REGEX + ".", FileFormatError.class);
        }

    }


    /**
     * Checks if the given line has the exact length required for the circuit.
     *
     * @param line a line from the circuit data
     * @return true if the length of the line matches {@link CircuitSetup#MAX_NODES_X}, false otherwise
     */
    private boolean checkLineLength(String line) {
        return line.length() == CircuitSetup.MAX_NODES_X;
    }


    /**
     * Checks if the given line contains only valid symbols for the circuit.
     *
     * @param line a line from the circuit data
     * @return true if the line matches the allowed symbols pattern, false otherwise
     */
    private boolean checkLineSymbols(String line) {
        return line.matches(SYMBOL_REGEX);
    }

}
