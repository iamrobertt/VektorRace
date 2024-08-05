package it.unicam.cs.NeculaRobertGabriel123390.api.utils;


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
    public static int getBotCount(String playersString) {
        String botsPart = playersString.split(" ")[0].replaceAll("\\D+", "");
        return Integer.parseInt(botsPart);
    }

    /**
     * Method that retrieves the number of human players from the given player string
     *
     * @param playersString The string containing player information
     * @return The number of human players
     */
    public static int getHumanCount(String playersString) {
        String humanPart = playersString.split(" ")[1].replaceAll("\\D+", "");
        return Integer.parseInt(humanPart);
    }


}
