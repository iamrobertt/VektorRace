package it.unicam.cs.NeculaRobertGabriel123390.api.utils;


/**
 * Class containing constant values for race handling configurations.
 * <p>
 * This class provides predefined constant values used to specify various race handling behaviors such as
 * COLLISION rules, crash outcomes, and win conditions.
 * </p>
 */
public final class RaceHandlerConstants {


    /**
     * Don't allow user to instantiate a RaceHandlerConstants object because that's not how this class is meant to work.
     */
    private RaceHandlerConstants() {}


    /**
     * Constant for allowing collisions between race participants.
     */
    public static final String COLLISION_ALLOWED = "true";


    /**
     * Constant for not allowing collisions between race participants.
     */
    public static final String COLLISION_NOT_ALLOWED = "false";


    /**
     * Constant indicating that a player should leave the race if a crash occurs.
     */
    public static final String CRASH_LEAVE_RACE = "leave race";


    /**
     * Constant indicating that a player should continue the race with a penalty if a crash occurs.
     */
    public static final String CRASH_CONTINUE_WITH_PENALTY = "continue with penalty";


    /**
     * Constant indicating that the win condition is the first player to cross the finish line.
     */
    public static final String WIN_FIRST_CROSSING_LINE = "first crossing line";
}
