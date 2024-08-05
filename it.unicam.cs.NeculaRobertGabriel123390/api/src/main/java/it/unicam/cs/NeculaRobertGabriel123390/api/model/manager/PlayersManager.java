package it.unicam.cs.NeculaRobertGabriel123390.api.model.manager;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.PlayerMoves;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that manages all the players participating at the race, pairing every user with its own possible moves
 * that are different for every player. This class handles the actions during the race that affect the user (like changing its possible moves,
 * updating its position based on the chosen move ecc)
 */
public class PlayersManager {


    private final Map<Player, PlayerMoves> playersData;


    public PlayersManager(List<Player> players) {
        //todo validate
        this.playersData = new LinkedHashMap<Player, PlayerMoves>();
        setup(players);
    }


    /**
     * Method that sets up the possible moves for every player
     * @param players - List of participating players
     */
    private void setup(List<Player> players) {
        for(Player player : players)
            this.playersData.put(player, new PlayerMoves());
    }


    /**
     * Method that updates the player possible moves based on the chosen move
     * @param player - The player making the move
     * @param movePosition - The new position related to its current
     */
    public void makeMove(Player player, Position movePosition) {
        Position newPosition = PositionUtils.addPositions(player.getPosition(), movePosition);
        updatePosition(player, newPosition);
        this.playersData.get(player).updatePossibleMoves(movePosition);
    }


    /**
     * Method that updates a playerToUpdate position
     * @param playerToUpdate - The playerToUpdate
     * @param newPosition - The new position
     */
    public void updatePosition(Player playerToUpdate, Position newPosition) {
        for(Player player : this.playersData.keySet())
            if(player.equals(playerToUpdate)) playerToUpdate.setPlayerPosition(newPosition);
    }


    public void removePlayer(Player playerToRemove) {this.playersData.remove(playerToRemove);}


    /**
     * Method used to retrieve the player's possible moves
     * @param player - The player to take the moves
     * @return The player's possible moves
     */
    public PlayerMoves getPlayerMoves(Player player) {
        return this.playersData.get(player);
    }


    public Player getPlayer(int index) {return new ArrayList<>(this.playersData.keySet()).get(index);}


    public int playersCount() {return this.playersData.size();}

}
