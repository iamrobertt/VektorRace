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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.race;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.HandlerValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;

import java.util.List;


/**
 * Provides validation functionality for a {@link Race} instance.
 *
 * <p>The {@code RaceValidator} class is responsible for validating the various components
 * of a race, including the circuit, players, and handlers. It ensures that all parts of the
 * race meet the required criteria for a valid race setup.</p>
 */
public final class RaceValidator {


    /**
     * Don't allow user to instantiate a RaceValidator object because that's not how this class is meant to work.
     */
    private RaceValidator() {}


    /**
     * Validates a {@code Race} instance by checking the circuit, players, and handlers.
     *
     * <p>This method validates the {@code Race} instance, including its {@link Circuit},
     * {@link Player} list, and {@link RaceHandler} list. It ensures that each of these components
     * is not null and meets the validation criteria defined in their respective validators.</p>
     *
     * @param race The {@code Race} instance to validate.
     * @throws NullPointerException if {@code race} is {@code null}.
     * @throws IllegalArgumentException if any component of the race (circuit, players, or handlers) is invalid.
     */
    public static void validate(Race race){
        if(race == null)
            throw new NullPointerException("Race is null");
        CircuitValidator.validate(race.circuit());
        validatePlayers(race.players());
        validateHandlers(race.handlers());
    }


    /**
     * Validates the components of a race individually: circuit, players, and handlers.
     *
     * <p>This method validates a {@link Circuit} instance, a list of {@link Player} instances,
     * and a list of {@link RaceHandler} instances. It checks that none of these components
     * are {@code null} and that they meet the validation criteria defined in their respective
     * validators.</p>
     *
     * @param circuit The {@code Circuit} instance to validate.
     * @param players The list of {@code Player} instances to validate.
     * @param handlers The list of {@code RaceHandler} instances to validate.
     * @throws NullPointerException if {@code circuit}, {@code players}, or {@code handlers} is {@code null}.
     * @throws IllegalArgumentException if any component (circuit, players, or handlers) is invalid.
     */
    public static void validate(Circuit circuit, List<Player> players, List<RaceHandler> handlers) {
        CircuitValidator.validate(circuit);
        validatePlayers(players);
        validateHandlers(handlers);
    }


    /**
     * Validates a list of {@code Player} instances.
     *
     * <p>This method delegates the validation of the provided list of {@code Player} instances
     * to {@link PlayerValidator}. It ensures that all players in the list meet the required
     * validation criteria.</p>
     *
     * @param players The list of {@code Player} instances to validate.
     * @throws NullPointerException if {@code players} is {@code null}.
     * @throws IllegalArgumentException if any player in the list is invalid.
     */
    private static void validatePlayers(List<Player> players) {
        PlayerValidator.validate(players);
    }


    /**
     * Validates a list of {@code RaceHandler} instances.
     *
     * <p>This method checks that the list of {@code RaceHandler} instances is not {@code null},
     * and then delegates the validation of each handler to {@link HandlerValidator}.</p>
     *
     * @param handlers The list of {@code RaceHandler} instances to validate.
     * @throws NullPointerException if {@code handlers} is {@code null}.
     * @throws NullPointerException if any handler in the list is invalid.
     */
    private static void validateHandlers(List<RaceHandler> handlers) {
        if(handlers == null)
            throw new NullPointerException("Handlers is null");

        for(RaceHandler handler : handlers)
            HandlerValidator.validate(handler);

    }
}
