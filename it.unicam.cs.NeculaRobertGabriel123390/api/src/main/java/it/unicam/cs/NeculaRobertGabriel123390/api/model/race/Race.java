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


import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.BotPlayer;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.HumanPlayer;


import java.util.ArrayList;
import java.util.List;


/**
 * Record that represents a race merging circuit data and player data
 */
public record Race(Circuit circuit, List<Player> players) {

    public Race(Circuit circuit, List<Player> players) {
        validateRace(circuit, players);
        this.circuit = circuit;
        this.players = new ArrayList<>(players);
    }


    private void validateRace(Circuit circuit, List<Player> players) {
        if (circuit == null) throw new NullPointerException("Circuit cannot be null");
        if (circuit.isEmpty()) throw new IllegalArgumentException("Circuit cannot be empty");
        if (circuit.isEmpty()) throw new IllegalArgumentException("Circuit is empty");
        if (players == null) throw new NullPointerException("Players cannot be null");
        if (players.isEmpty()) throw new IllegalArgumentException("Amount of players not valid for a race");
    }


    public int getNumberOfBotPlayers() {
        int botPlayers = 0;
        for (Player player : players)
            if (player instanceof BotPlayer)
                botPlayers++;

        return botPlayers;

    }


    public int getNumberOfHumanPlayers() {
        int humanPlayers = 0;
        for (Player player : players)
            if (player instanceof HumanPlayer)
                humanPlayers++;

        return humanPlayers;
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

}
