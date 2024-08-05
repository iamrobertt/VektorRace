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

package it.unicam.cs.NeculaRobertGabriel123390.api.model;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.BotPlayer;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.HumanPlayer;


import java.util.ArrayList;
import java.util.List;


/**
 * Record that represents a race merging circuit data, player data and handlers
 */
public record Race(Circuit circuit, List<Player> players, List<RaceHandler> handlers){


    public Race(Circuit circuit, List<Player> players, List<RaceHandler> handlers) {
        validateRace(circuit, players, handlers);
        this.circuit = circuit;
        this.players = players;
        this.handlers = handlers;
    }

    /**
     * Method that checks if circuit, race and handlers are null.
     *
     * @param circuit  - The circuit of the race
     * @param players  - The list of players that are playing
     * @param handlers - The list of handlers for the race
     */
    private void validateRace(Circuit circuit, List<Player> players, List<RaceHandler> handlers) {
        if (circuit == null) throw new NullPointerException("Circuit cannot be null");
        if (players == null) throw new NullPointerException("Players cannot be null");
        if (handlers == null) throw new NullPointerException("MoveHandlers cannot be null");
    }


    /**
     * Method used by the controller to display in th view the number of bot players
     *
     * @return int The number of bot players
     */
    public int getNumberOfBotPlayers() {
        int botPlayers = 0;
        for (Player player : players)
            if (player instanceof BotPlayer)
                botPlayers++;

        return botPlayers;

    }


    /**
     * Method used by the controller to display in th view the number of human players
     *
     * @return int The number of human players
     */
    public int getNumberOfHumanPlayers() {
        int humanPlayers = 0;
        for (Player player : players)
            if (player instanceof HumanPlayer)
                humanPlayers++;

        return humanPlayers;
    }

}
