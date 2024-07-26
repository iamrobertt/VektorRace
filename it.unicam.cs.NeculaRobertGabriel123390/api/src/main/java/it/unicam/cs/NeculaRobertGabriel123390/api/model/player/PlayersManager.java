package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that manages all the players participating at the race, pairing every user with its own possible moves
 * that wil be different for every player. This class handles the actions during the race that affect the user (like changing its possible moves,
 * updating its position based on the chosen move ecc)
 */
public class PlayersManager {

    private final Map<Player, PlayerPossibleMoves> players;


    public PlayersManager(List<Player> players) {
        this.players = new HashMap<Player, PlayerPossibleMoves>();
        setupManager(players);
    }


    /**
     * Method that setup the possible moves for every player
     * @param players - List of participating players
     */
    private void setupManager(List<Player> players) {
        for(Player player : players)
            this.players.put(player, new PlayerPossibleMoves());

    }


    /**
     * Method that updates the player possible moves based on the chosen move
     * @param player - The player making the move
     * @param newPosition - The new position related to its current
     */
    public void makeMove(Player player, Position newPosition) {
        this.players.get(player).updateGridPositions(newPosition);
    }


    /**
     * Method used to retrieve the player's possible moves
     * @param player - The player to take the moves
     * @return The player's possible moves
     */
    public PlayerPossibleMoves getPlayerPossibleMoves(Player player) {
        return this.players.get(player);
    }


}
