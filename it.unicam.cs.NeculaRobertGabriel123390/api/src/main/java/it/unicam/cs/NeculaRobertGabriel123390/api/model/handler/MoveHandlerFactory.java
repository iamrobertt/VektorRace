package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler;


import java.util.HashMap;
import java.util.Map;


/**
 * Class that supplies all type of handlers based on the values of the rules chosen at the start of the race
 */
public class MoveHandlerFactory {


    private static final Map<String, RaceHandler> COLLISION_HANDLERS = new HashMap<>();
    private static final Map<String, RaceHandler> CRASH_HANDLERS = new HashMap<>();


    static {
        COLLISION_HANDLERS.put("true", new NoCollisionHandler());
        COLLISION_HANDLERS.put("false", new CollisionHandler());

        CRASH_HANDLERS.put("leave_race", new LeaveRaceCrashHandler());
        CRASH_HANDLERS.put("continue_with_penalty", new ContinueWithPenaltyCrashHandler());
    }


    /**
     * Method that supplies the right collision handler based on the value
     * @param value - The value of the collision rule
     * @return RaceHandler a RaceHandler for the collisions
     */
    public static RaceHandler getCollisionHandler(String value) {
        System.out.println("getCollisionHandler: " + value);
        RaceHandler handler = COLLISION_HANDLERS.get(value);
        if (handler == null)
            throw new IllegalArgumentException("Unexpected value for collision handler: " + value);

        return handler;
    }


    /**
     * Method that supplies the right crash handler based on the value
     * @param value - The value of the collision rule
     * @return RaceHandler a RaceHandler for the crashes
     */
    public static RaceHandler getCrashHandler(String value) {
        RaceHandler handler = CRASH_HANDLERS.get(value);
        if (handler == null)
            throw new IllegalArgumentException("Unexpected value for crash handler: " + value);

        return handler;
    }
}
