package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PlayerUtils;
import javafx.scene.paint.Color;

import java.util.*;


/**
 * Class that implements the PlayerSetup interface for a txt file
 */
//todo magari rivedi e semplifica
public class TXTPlayerSetup implements PlayerSetup {


    /**
     * Method that retrieves the players for the race based on file data
     *
     * @param fileData - The data from the txtFile
     * @return List<Player> - The list of players
     */
    @Override
    public List<Player> setup(ParsedFileData fileData) {

        validateFileDataType(fileData);
        String numOfPlayersString = extractNumPlayersStringFromFile(fileData);

        List<Player> players = new ArrayList<>();
        int numBotsPlayer = PlayerUtils.getNumberOfBotPlayers(numOfPlayersString);

        Map<String, Color> humanPlayersData = retrieveHumanPlayersDataFromFile(fileData);
        players.addAll(createHumanPlayers(humanPlayersData));
        players.addAll(createBotPlayers(numBotsPlayer));

        return players;

    }


    /**
     * Method that checks if the data passed is a txtFileData
     *
     * @param fileData - The data to validate
     */
    private void validateFileDataType(ParsedFileData fileData) {
        if (!(fileData instanceof TXTParsedFileData))
            throw new IllegalArgumentException("FileData is not a TXTParsedFileData");

    }


    /**
     * Method that checks if supplied human player data is same as number of human players declared
     *
     * @param dividedPlayersData - The array of strings each representing one user data
     * @param humanPlayers       - NUmber of human players declared in file in "xH" where x is the number of players declared
     */
    private void validatePlayerData(String[] dividedPlayersData, int humanPlayers) {
        if (dividedPlayersData.length != humanPlayers)
            throw new IllegalArgumentException("HUman player data supplied (name, color) not same as human player declared in previous row");
    }


    /**
     * Method that extracts the row of the file with the number of players data
     *
     * @param fileData - The data passed
     * @return String The string with players data
     */
    private String extractNumPlayersStringFromFile(ParsedFileData fileData) {
        List<String> dataToList = new ArrayList<>(((TXTParsedFileData) fileData).getData());
        return dataToList.get(CircuitSetup.PLAYERS_NUMBER_ROW);
    }


    /**
     * Method that extracts the last row of file where human players data is supplied
     *
     * @param fileData - The data passed
     * @return String[] arrays of strings each representing a user
     */
    private String[] extractHumanPlayersDataStringFromFile(ParsedFileData fileData) {
        List<String> dataToList = new ArrayList<>(((TXTParsedFileData) fileData).getData());
        String[] dividedPlayersData = dataToList.get(CircuitSetup.HUMAN_PLAYERS_DATA_ROW).split(",");

        String numOfHumanPlayersString = extractNumPlayersStringFromFile(fileData);

        int humanPlayers = PlayerUtils.getNumberOfHumanPlayers(numOfHumanPlayersString);

        validatePlayerData(dividedPlayersData, humanPlayers);

        return dividedPlayersData;
    }


    /**
     * Method that retrieves the human players data from the file
     *
     * @param fileData - The data passed
     * @return Map<String, Color> All human player data
     */
    private Map<String, Color> retrieveHumanPlayersDataFromFile(ParsedFileData fileData) {

        Map<String, Color> playersData = new HashMap<>();//
        String[] dividedPlayersData = extractHumanPlayersDataStringFromFile(fileData);
        for (String playerData : dividedPlayersData) {
            String[] splitPlayerData = playerData.split(":");
            playersData.put(splitPlayerData[0], Color.valueOf(splitPlayerData[1]));
        }

        return playersData;
    }


    /**
     * Method that creates new human players, data got from the file
     *
     * @param players - Human players
     * @return List<Player> - Human players
     */
    private List<Player> createHumanPlayers(Map<String, Color> players) {
        List<Player> humanPlayers = new ArrayList<>();

        for (Map.Entry<String, Color> player : players.entrySet()) {
            String playerName = player.getKey();
            Color playerColor = player.getValue();
            humanPlayers.add(new HumanPlayer(playerName, playerColor));
        }

        return humanPlayers;
    }


    /**
     * Method that creates numBots players, data got from the file
     *
     * @param numBots - Number of bot players
     * @return List<Player> - BotPlayer
     */
    private List<Player> createBotPlayers(int numBots) {
        List<Player> botPlayers = new ArrayList<>();

        for (int i = 0; i < numBots; i++)
            botPlayers.add(new BotPlayer(("Bot " + (i + 1)), Color.rgb(getRandomRGBValue(), getRandomRGBValue(), getRandomRGBValue())));

        return botPlayers;
    }


    private int getRandomRGBValue() {
        return (int) (Math.random() * 255);
    }
}
