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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.manager;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.PlayerMoves;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * The {@code PlayersManager} class manages the list of players participating in a race.
 * It handles player actions, including updating their positions, managing possible moves,
 * and removing players from the race. The class ensures that each player has the correct state
 * and provides methods to interact with players during the race.
 */
public final class PlayersManager {


    private final Queue<Player> playerQueue;


    /**
     * Constructs a {@code PlayersManager} with a list of players participating in the race.
     *
     * @param players the list of players to manage.
     * @throws IllegalArgumentException if the provided players list is {@code null} or empty.
     */
    public PlayersManager(List<Player> players) {
        validatePlayers(players);
        this.playerQueue = new LinkedList<>(players);
    }


    /**
     * Checks if the given players are valid for the race. Checks also if there are at least
     * 2 players, the minimum amount to play.
     * @param players The list of players to validate
     */
    private void validatePlayers(List<Player> players) {
        PlayerValidator.validate(players);
        if(players.size() < 2)
            throw new IllegalArgumentException("At least two players are required.");
    }


    /**
     * Updates the current player's position and possible moves based on the provided move position.
     * The move is applied to the current player's position, and the new position is set for the player.
     *
     * @param movePosition the new relative position chosen by the player.
     * @throws IllegalArgumentException if the move position is invalid.
     */
    public void makeMove(Position movePosition) {
        PositionUtils.validatePosition(movePosition);
        Player currentPlayer = getCurrentPlayer();
        Position newPosition = PositionUtils.addPositions(currentPlayer.getPosition(), movePosition);
        updatePosition(currentPlayer, newPosition);
        currentPlayer.getPossibleMoves().update(movePosition);
    }


    /**
     * Updates the position of the specified player.
     *
     * @param player the player to update.
     * @param newPosition the new position to assign to the player.
     * @throws IllegalArgumentException if the new position is invalid.
     */
    public void updatePosition(Player player, Position newPosition) {
        PositionUtils.validateCircuitNodePosition(newPosition);
        PlayerValidator.validate(player);
        player.setPosition(newPosition);
    }


    /**
     * Removes a player from the race.
     *
     * @param player the player to remove from the list.
     * @throws IllegalArgumentException if the player is not found in the queue.
     */
    public void removePlayer(Player player) {
        PlayerValidator.validate(player);
        if (!this.playerQueue.remove(player))
            throw new IllegalArgumentException("Player " + player.getName() + " not found in the queue");
    }


    /**
     * Retrieves the possible moves for the current player.
     *
     * @return the current player's possible moves
     */
    public PlayerMoves getCurrentPlayerMoves() {
        return getCurrentPlayer().getPossibleMoves();
    }


    /**
     * Retrieves the current player.
     *
     * @return the player whose turn it is to move
     */
    public Player getCurrentPlayer() {
        Player currentPlayer = this.playerQueue.peek();
        if(currentPlayer == null)
            throw new IllegalArgumentException("Failed to get current player, value is null");

        return currentPlayer;
    }


    /**
     * Shifts the current player to the next one and the previous current player
     * is added to the end of the queue.
     * @throws IllegalStateException if the player queue is empty.
     */
    public void advanceToNextPlayer() {
        if(this.playerQueue.isEmpty())
            throw new IllegalStateException("Player queue is empty. Cannot advance to any player.");

        Player currentPlayer = this.playerQueue.poll();
        this.playerQueue.add(currentPlayer);
    }


    /**
     * Returns the total number of players in the race.
     *
     * @return the number of players
     */
    public int playersCount() {return this.playerQueue.size();}


    /**
     * Returns the list of the players participating in the race
     * @return a List of {@link Player} participating in the race
     */
    public List<Player> getPlayers() {return this.playerQueue.stream().toList();}

}
