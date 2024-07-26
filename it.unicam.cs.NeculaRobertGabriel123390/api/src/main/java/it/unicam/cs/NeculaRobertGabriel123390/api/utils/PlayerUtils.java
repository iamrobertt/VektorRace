package it.unicam.cs.NeculaRobertGabriel123390.api.utils;

//todo crea interfaccia generale e rimodifica in txt
/**
 * Class that supplies methods for the player data
 */
public class PlayerUtils {

    /**
     * Method that retrieves the number of bot players from the given player string
     *
     * @param playersString The string containing player information
     * @return The number of bot players
     */
    public static int getNumberOfBotPlayers(String playersString) {
        String botsPart = playersString.split(" ")[0].replaceAll("\\D+", "");
        return Integer.parseInt(botsPart);
    }

    /**
     * Method that retrieves the number of human players from the given player string
     *
     * @param playersString The string containing player information
     * @return The number of human players
     */
    public static int getNumberOfHumanPlayers(String playersString) {
        String humanPart = playersString.split(" ")[1].replaceAll("\\D+", "");
        return Integer.parseInt(humanPart);
    }


    public static String getRegexForPlayers() {
        return "\\d+B \\d+H";
    }

}
