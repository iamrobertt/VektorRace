package it.unicam.cs.NeculaRobertGabriel123390.api;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Line;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerPossibleMoves;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitManager;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitTile;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayersManager;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;

import java.util.List;
import java.util.Map;

public class RaceManager {


    private final List<Player> players;


    private final PlayersManager playersManager;


    private final CircuitManager circuitManager;


    private boolean isRaceFinished = false;


    private int currentTurn = 0;


    private Player currentPlayer;


    public RaceManager(Map<Position, CircuitTile> mappedCircuit, Line startLine, Line endline ,List<Player> players) {
        //todo validate
        this.players = players;
        this.playersManager = new PlayersManager(players);
        this.circuitManager = new CircuitManager(mappedCircuit, startLine, endline);
        this.currentPlayer = getCurrentPlayer();
        initializePlayersPosition();
    }


    /**
     * Method that handles a player move, updating its data in player and circuit managers
     * @param position - The new position relative to the current position, retrieved by the chosen move
     */
    public void handlePlayerMove(Position position) {
        this.playersManager.makeMove(this.currentPlayer, position);
        updateCurrentPlayerPosition(position);
        updateCurrentTurn();
        updateCurrentPlayer();
    }


    /**
     * Method that returns for the GUI the next player's moves
     * @return PlayerPossibleMoves The next player's move
     */
    public PlayerPossibleMoves getNextPlayerPossibleMoves() {return this.playersManager.getPlayerPossibleMoves(this.currentPlayer);}


    public boolean hasRaceFinished() {
        return this.isRaceFinished;
    }


    public Player getCurrentPlayer() {return this.players.get(currentTurn % this.players.size());}


    public String getCurrentPlayerName() {return this.currentPlayer.getPlayerName();}


    public void updateCurrentPlayer() {this.currentPlayer = getCurrentPlayer();}


    private void updateCurrentTurn(){this.currentTurn++;}


    /**
     * Method that updates the current position both in player and circuit data
     * @param position - The new position relative to the current position, retrieved by the chosen move
     */
    private void updateCurrentPlayerPosition(Position position) {
        Position currentPosition = this.currentPlayer.getPlayerPosition();
        Position newPosition = PositionUtils.addPositions(currentPosition, position);
        this.circuitManager.setPositionOccupiedByPlayer(this.currentPlayer, newPosition);
        this.currentPlayer.setPlayerPosition(newPosition);
    }


    /**
     * Method that initializes the players position, assigning a different position for every player
     */
    private void initializePlayersPosition(){
        Line startLine = this.circuitManager.getStartLine();
        for(int i = 0; i < this.players.size(); i++)
            this.players.get(i).setPlayerPosition(startLine.getNodesOfLine().get(i).getPosition());

    }
}
