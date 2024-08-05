package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.TXTCircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PlayerUtils;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that implements the PlayerSetup interface for a txt file
 */
public class TXTPlayerSetup implements PlayerSetup {


    private List<String> linesOfFile;


    private String playersCountInfo;


    /**
     * Method that supplies a list of players from the file data
     *
     * @param fileData - The data from the txt file
     * @return List<Player> - The list of players
     */
    @Override
    public List<Player> setup(ParsedFileData fileData) {

        validateFileDataType(fileData);

        this.linesOfFile = new ArrayList<>(((TXTParsedFileData) fileData).getData());

        this.playersCountInfo = getPlayersCountInfo();
        validatePlayersInfo();

        List<Player> players = new ArrayList<>();
        int numBotsPlayer = PlayerUtils.getBotCount(this.playersCountInfo);

        players.addAll(createHumanPlayers());
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
     * If I write 0B 2H I also need to write in the new line 2 pair of player data  name:color,name:color
     *
     * @param dividedPlayersData - The array of strings each representing one user data
     * @param humanPlayers       - NUmber of human players declared in file in "xH" where x is the number of players declared
     */
    private void validateHumanPlayerData(String[] dividedPlayersData, int humanPlayers) {
        if (dividedPlayersData.length != humanPlayers)
            throw new IllegalArgumentException("Human player data supplied [name:color] not same as human player declared in previous row");
    }


    /**
     * Method that checks if the amount of player supplied is at least 2
     */
    private void validatePlayersInfo(){
        if(PlayerUtils.getBotCount(this.playersCountInfo) + PlayerUtils.getHumanCount(this.playersCountInfo) < 2)
            throw new IllegalArgumentException("Minimum of 2 players required");
    }


    /**
     * Method that extracts the row of the file with the number of players data
     *
     * @return String The line where number of players data is written
     */
    private String getPlayersCountInfo() {
        return this.linesOfFile.get(TXTCircuitSetup.PLAYERS_NUMBER_ROW);
    }


    /**
     * Method that retrieves the human player info (name:color) from the file, splitting them
     *
     * @return String[] arrays of strings each representing a user
     */
    private String[] getHumanPlayerData() {
        String[] humanData = this.linesOfFile.get(TXTCircuitSetup.HUMAN_PLAYERS_DATA_ROW).split(",");

        int humanCount = PlayerUtils.getHumanCount(this.playersCountInfo);

        validateHumanPlayerData(humanData, humanCount);

        return humanData;
    }


    /**
     * Method that retrieves the human players data from the file and creates the human players
     * player info holds the name:color data for each player
     *
     * @return Map<String, Color> All human player data
     */
    private List<Player> createHumanPlayers() {
        List<Player> players = new ArrayList<>();
        String[] humanData = getHumanPlayerData();
        for (String data : humanData) {
            String[] playerInfo = data.split(":");
            players.add(new HumanPlayer(playerInfo[0], Color.valueOf(playerInfo[1])));
        }

        return players;
    }



    /**
     * Method that creates numBots players, assigning them a progressive number and a random color
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
