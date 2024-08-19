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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.TXTCircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.FileFormatError;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedDataValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedDataValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.LoadingLogger;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PlayerUtils;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Implementation of {@link PlayerSetup} for TXT file format.
 * <p>
 * This class reads player data from a TXT file, validates the data, and creates a list of players
 * based on the file contents. It supports both human and bot players.
 * </p>
 */
public final class TXTPlayerSetup implements PlayerSetup {


    /**
     * Sets up players based on the provided TXT file data.
     *
     * @param parsedFileData The data from the TXT file, which must be an instance of {@link TXTParsedData}.
     * @return A list of {@link Player} objects created based on the TXT file data.
     */
    @Override
    public List<Player> setup(ParsedData<?> parsedFileData) {
        validateParsedFileData(parsedFileData);

        List<String> fileData = extractData(parsedFileData);
        String playersCountInfo = getPlayersCountInfo(fileData);
        int numBotsPlayer = PlayerUtils.getBotCount(playersCountInfo);

        List<Player> players = new ArrayList<>();

        String[] humanData = getHumanPlayerData(fileData, playersCountInfo);
        players.addAll(createHumanPlayers(humanData));
        players.addAll(createBotPlayers(numBotsPlayer));

        return players;

    }


    /**
     * Validates that the provided file data is valid to be processed with the {@link TXTParsedDataValidator}.
     *
     * @param fileData The parsed file data to validate.
     */
    private void validateParsedFileData(ParsedData<?> fileData) {
        ParsedDataValidator fileValidator = new TXTParsedDataValidator();
        fileValidator.validateData(fileData);
    }


    /**
     * Validates that the number of players (bots and humans combined) is at least 2.
     * @param playersCountInfo The string containing the player count information in the format "xB yH".
     * @throws IllegalArgumentException If the total number of players is less than 2, if the counts are invalid or the format of th string is invalid.
     */
    private void validatePlayersCount(String playersCountInfo) {

        int totalPlayers = PlayerUtils.getBotCount(playersCountInfo) + PlayerUtils.getHumanCount(playersCountInfo);

        if(PlayerUtils.getBotCount(playersCountInfo) < 0)
            LoadingLogger.logErrorAndThrow("Number of bots needs to be minimum 0", FileFormatError.class);

        if(PlayerUtils.getHumanCount(playersCountInfo) < 0)
            LoadingLogger.logErrorAndThrow("Number of humans needs to be minimum 0", FileFormatError.class);

        if (totalPlayers < 2)
            LoadingLogger.logErrorAndThrow("A minimum of 2 players is required.", FileFormatError.class);

    }


    /**
     * Validates that the number of human player data entries matches the number declared in the file.
     *
     * @param humanData The array of strings representing human player data.
     * @param humanPlayers The number of human players declared in the file.
     * @throws FileFormatError If the number of human player data entries does not match the declared number.
     */
    private void validateHumanPlayerData(String[] humanData, int humanPlayers) {
        if (humanData.length != humanPlayers)
            LoadingLogger.logErrorAndThrow("Mismatch between declared and supplied human player data.", FileFormatError.class);

        for(String data : humanData) {

            if(data.split(":").length != 2)
                LoadingLogger.logErrorAndThrow("Name or color for the player are missing", FileFormatError.class);

            if(Objects.requireNonNull(data.split(":")[0]).isEmpty())
                LoadingLogger.logErrorAndThrow("A player does not have a name", FileFormatError.class);

            if(Objects.requireNonNull(data.split(":")[1]).isEmpty())
                LoadingLogger.logErrorAndThrow("A player does not have a color", FileFormatError.class);
        }
    }


    /**
     * Converts the parsed file data into a list of strings.
     *
     * @param fileData the parsed file data to convert.
     * @return a list of strings representing the file data.
     */
    private List<String> extractData(ParsedData<?> fileData) {
        return new ArrayList<>(((TXTParsedData) fileData).getData());
    }


    /**
     * Extracts and validates the player count information from the file.
     * <p>
     * This method ensures that the player count data is in the correct format and that it meets the requirements for the game.
     * </p>
     *
     * @param fileData The list of strings representing the file data.
     * @return The string containing player count data in the format "xB yH".
     */
    private String getPlayersCountInfo(List<String> fileData) {
        String playersCountInfo = fileData.get(TXTCircuitSetup.PLAYERS_COUNT_ROW);
        validatePlayersCount(playersCountInfo);
        return playersCountInfo;
    }


    /**
     * Retrieves and validates the human player data from the file.
     * <p>
     * This method parses the human player data from a string array, ensuring that the number of human players
     * matches the declared count and that each player has a valid name and color.
     * </p>
     *
     * @param fileData The list of strings representing the file data.
     * @param playersCountInfo The string containing player count information.
     * @return An array of strings representing the human player data in the format "name:color".
     * @throws IllegalArgumentException If the human player data is invalid or mismatches the declared count.
     */
    private String[] getHumanPlayerData(List<String> fileData, String playersCountInfo) {
        String[] humanData = fileData.get(TXTCircuitSetup.HUMAN_PLAYERS_DATA_ROW).split(",");

        int humanCount = PlayerUtils.getHumanCount(playersCountInfo);
        validateHumanPlayerData(humanData, humanCount);

        return humanData;
    }


    /**
     * Creates instances of {@link HumanPlayer} based on the human player data extracted from the file.
     * <p>
     * Each human player is created with the name and color specified in the data.
     * </p>
     *
     * @param humanData The array of strings representing human player data in the format "name:color".
     * @return A list of {@link HumanPlayer} objects.
     */
    private List<Player> createHumanPlayers(String[] humanData) {
        List<Player> players = new ArrayList<>();
        for (String data : humanData) {
            String[] playerInfo = data.split(":");
            players.add(new HumanPlayer(playerInfo[0], Color.valueOf(playerInfo[1])));
        }

        PlayerValidator.validate(players);
        return players;
    }


    /**
     * Creates a specified number of bot players with unique names and random colors.
     *
     * @param numBots The number of bot players to create.
     * @return A list of {@link BotPlayer} objects.
     */
    private List<Player> createBotPlayers(int numBots) {
        List<Player> botPlayers = new ArrayList<>();
        for (int i = 0; i < numBots; i++)
            botPlayers.add(new BotPlayer("Bot " + (i + 1), generateRandomColor()));

        return botPlayers;
    }


    /**
     * Generates a random color.
     *
     * @return A {@link Color} object with random RGB values.
     */
    private Color generateRandomColor() {return Color.rgb(getRandomRGBValue(), getRandomRGBValue(), getRandomRGBValue());}


    /**
     * Generates a random RGB value between 0 and 255.
     *
     * @return An integer representing an RGB value.
     */
    private int getRandomRGBValue() {return (int) (Math.random() * 256);}
}
