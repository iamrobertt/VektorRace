package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.collision.AllowCollisionHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.collision.DoNotAllowCollisionHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.crash.ContinueWithPenaltyCrashHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.crash.LeaveRaceCrashHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.win.WinOnCrossEndLineHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.RaceHandlerConstants;

import java.util.HashMap;
import java.util.Map;


/**
 * Factory class that provides the appropriate race handlers based on the selected rules at the start of the race.
 * <p>
 * This class uses static maps to associate specific rule values with their corresponding handler implementations.
 * It supports obtaining COLLISION handlers, crash handlers, and win handlers based on predefined constants.
 * </p>
 */
public final class RaceHandlerFactory {


    private static final Map<String, RaceHandler> COLLISION_HANDLERS = new HashMap<>();
    private static final Map<String, RaceHandler> CRASH_HANDLERS = new HashMap<>();
    private static final Map<String, RaceHandler> WIN_HANDLERS = new HashMap<>();


    static {
        COLLISION_HANDLERS.put(RaceHandlerConstants.COLLISION_ALLOWED, new AllowCollisionHandler());
        COLLISION_HANDLERS.put(RaceHandlerConstants.COLLISION_NOT_ALLOWED, new DoNotAllowCollisionHandler());

        CRASH_HANDLERS.put(RaceHandlerConstants.CRASH_LEAVE_RACE, new LeaveRaceCrashHandler());
        CRASH_HANDLERS.put(RaceHandlerConstants.CRASH_CONTINUE_WITH_PENALTY, new ContinueWithPenaltyCrashHandler());

        WIN_HANDLERS.put(RaceHandlerConstants.WIN_FIRST_CROSSING_LINE, new WinOnCrossEndLineHandler());
    }


    /**
     * Retrieves the appropriate crash handler based on the specified rule value.
     *
     * @param value The value of the crash rule, as defined in {@link RaceHandlerConstants}.
     * @return The {@link RaceHandler} responsible for handling crashes according to the specified rule.
     * @throws IllegalArgumentException If the specified value does not correspond to a known crash handler.
     */
    public static RaceHandler getCollisionHandler(String value) {
        RaceHandler handler = COLLISION_HANDLERS.get(value);
        if (handler == null)
            throw new IllegalArgumentException("Unexpected value for COLLISION handler: " + value);

        return handler;
    }


    /**
     * Retrieves the appropriate win handler based on the specified rule value.
     *
     * @param value The value of the win rule, as defined in {@link RaceHandlerConstants}.
     * @return The {@link RaceHandler} responsible for handling win conditions according to the specified rule.
     * @throws IllegalArgumentException If the specified value does not correspond to a known win handler.
     */
    public static RaceHandler getCrashHandler(String value) {
        RaceHandler handler = CRASH_HANDLERS.get(value);
        if (handler == null)
            throw new IllegalArgumentException("Unexpected value for crash handler: " + value);

        return handler;
    }


    /**
     * Retrieves the appropriate win handler based on the specified rule value.
     *
     * @param value The value of the win rule, as defined in {@link RaceHandlerConstants}.
     * @return The {@link RaceHandler} responsible for handling win conditions according to the specified rule.
     * @throws IllegalArgumentException If the specified value does not correspond to a known win handler.
     */
    public static RaceHandler getWinHandler(String value) {
        RaceHandler handler = WIN_HANDLERS.get(value);
        if(handler == null)
            throw new IllegalArgumentException("Unexpected value for win handler: " + value);

        return handler;
    }

}
