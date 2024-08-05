package it.unicam.cs.NeculaRobertGabriel123390.api.model.manager;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.PlayerMoves;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;


/**
 * Class that supplies information about current player playing on a turn
 */
public class TurnManager {

    private final PlayersManager playersManager;
    private int turn;

    public TurnManager(PlayersManager playersManager) {
        this.playersManager = playersManager;
        this.turn = 0;
    }


    public Player getCurrentPlayer() {return this.playersManager.getPlayer(this.turn % this.playersManager.playersCount());}


    public void updateCurrentPlayer() {this.turn++;}


    public PlayerMoves getNextPlayerPossibleMoves() {return this.playersManager.getPlayerMoves(getCurrentPlayer());}
}
