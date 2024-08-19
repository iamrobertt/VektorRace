package it.unicam.cs.NeculaRobertGabriel123390.api.utils;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.FileFormatError;

/**
 * Utility class providing methods to extract player counts from a string representation of player data.
 * <p>
 * The methods in this class are designed to parse a specific format of player data strings and extract the number of bot and human players.
 * </p>
 */
public final class PlayerUtils {


    /**
     * REGEX used to validate the string format of the players count information in the file.
     */
    public static final String PLAYER_COUNT_REGEX = "\\d+B \\d+H";


    /**
     * Don't allow user to instantiate a PlayerUtils object because that's not how this class is meant to work.
     */
    private PlayerUtils() {}


    /**
     * Retrieves the number of bot players from a string containing player information.
     * <p>
     * The expected format of the input string is "xB xH", where x is a non-negative number. This method extracts and parses the number of bot players.
     * </p>
     *
     * @param playersCountString A string containing player information in the format "xB xH", where x is a non-negative number.
     * @return The number of bot players parsed from the input string.
     * @throws NumberFormatException if the extracted number cannot be parsed to an integer.
     */
    public static int getBotCount(String playersCountString) {
        validatePlayersCountStringFormat(playersCountString);
        String botPart = playersCountString.split(" ")[0].replaceAll("\\D+", "");
        try{
            return Integer.parseInt(botPart);
        }
        catch(NumberFormatException e){
            throw new FileFormatError("The value given for bots is not a number.");
        }
    }

    /**
     * Retrieves the number of human players from a string containing player information.
     * <p>
     * The expected format of the input string is "xB xH", where x is a non-negative number. This method extracts and parses the number of human players.
     * </p>
     *
     * @param playersCountString A string containing player information in the format "xB xH", where x is a non-negative number.
     * @return The number of human players parsed from the input string.
     * @throws NumberFormatException if the extracted number cannot be parsed to an integer.
     * */
    public static int getHumanCount(String playersCountString) {
        validatePlayersCountStringFormat(playersCountString);
        String humanPart = playersCountString.split(" ")[1].replaceAll("\\D+", "");
        try{
            return Integer.parseInt(humanPart);
        }
        catch(NumberFormatException e){
            throw new FileFormatError("The value given for humans is not a number.");
        }
    }


    /**
     * Checks if the format of the string given is correct.
     * @param playersCountString The string representing the count info of the players.
     * @throws NullPointerException if the given string is null.
     * @throws IllegalArgumentException if the format is not correct.
     */
    private static void validatePlayersCountStringFormat(String playersCountString) {
        if(playersCountString == null)
            throw new NullPointerException("playerCount string is null.");
        if(!playersCountString.matches(PLAYER_COUNT_REGEX))
            throw new FileFormatError("Invalid playersCountString: " + playersCountString + ". The format must be xB yH, where x, y are 2 non negative numbers");
    }


}
