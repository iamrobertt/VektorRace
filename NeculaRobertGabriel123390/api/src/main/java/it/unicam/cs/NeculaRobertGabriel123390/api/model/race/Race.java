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


import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;


import java.util.List;


/**
 * Represents a race which includes the circuit, players, and handlers.
 * <p>
 * This record encapsulates all the essential components of a race including
 * the circuit layout, the list of players participating in the race (both human
 * and bot players), and the handlers responsible for race events and logic.
 * </p>
 *
 * @param circuit The circuit of the race.
 * @param players The list of players participating in the race.
 * @param handlers The list of handlers managing race events.
 */
public record Race(Circuit circuit, List<Player> players, List<RaceHandler> handlers){


    /**
     * Constructs a new `Race` instance.
     * <p>
     * Validates that the circuit, players, and handlers are not null.
     * </p>
     *
     * @param circuit The circuit of the race.
     * @param players The list of players participating in the race.
     * @param handlers The list of handlers managing race events.
     * @throws NullPointerException if any of the parameters are null.
     */
    public Race {
        validateRace(circuit, players, handlers);
    }


    /**
     * Validates that the circuit, players, and handlers are not null.
     *
     * @param circuit The circuit of the race.
     * @param players The list of players.
     * @param handlers The list of handlers.
     */
    private void validateRace(Circuit circuit, List<Player> players, List<RaceHandler> handlers) {
        RaceValidator.validate(circuit, players, handlers);
    }

}
