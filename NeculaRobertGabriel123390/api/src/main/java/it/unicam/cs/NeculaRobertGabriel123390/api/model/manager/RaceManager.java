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

import it.unicam.cs.NeculaRobertGabriel123390.api.model.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.success.BaseSuccessHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.success.SuccessHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.Race;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.RaceValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * The {@code RaceManager} class manages the flow of the race, handling each player's move
 * and delegating the move processing to a series of handlers. It also provides utility
 * methods to assist in updating the view, such as initializing player positions and
 * retrieving possible moves for the current player.
 */
public final class RaceManager{


    private final PlayersManager playersManager;


    private final CircuitManager circuitManager;


    private final List<RaceHandler> handlers;


    /**
     * Constructs a {@code RaceManager} with the given race configuration.
     *
     * @param race the {@link Race} configuration containing the players, circuit, and handlers
     */
    public RaceManager(Race race) {
        validateManager(race);
        this.playersManager = new PlayersManager(race.players());
        this.circuitManager = new CircuitManager(race.circuit());
        this.handlers = new ArrayList<>(race.handlers());
        initializeHandlers();
        setInitialPlayersPosition(calcInitialPlayersPosition());
    }


    /**
     * Validates the {@code Race} instance using {@link RaceValidator}.
     *
     * <p>This method calls the {@code validate} method of {@code RaceValidator} to ensure that
     * the provided {@code Race} instance, including its circuit, players, and handlers, meets
     * the necessary validation criteria. It performs a comprehensive validation of the race setup.</p>
     *
     * @param race The {@code Race} instance to validate.
     * @throws NullPointerException if {@code race} is {@code null}.
     * @throws IllegalArgumentException if any component of the race (circuit, players, or handlers) is invalid.
     */
    private void validateManager(Race race) {
        RaceValidator.validate(race);
    }


    /**
     * Handles the player's move by processing it through all registered handlers.
     * If a handler returns a non-null {@link MoveResult}, the move is considered handled,
     * and the result is returned. If no handler processes the move, a success result is returned.
     *
     * @param movePosition the position chosen by the player for the move.
     * @return the {@link MoveResult} which includes the result of the move and player data.
     */
    public MoveResult onPlayerMove(Position movePosition) {
        PositionUtils.validatePosition(movePosition);

        Position prevPosition = getCurrentPlayer().getPosition();
        Position newPosition = PositionUtils.addPositions(prevPosition, movePosition);

        for(RaceHandler raceHandler : this.handlers) {
            MoveResult result = raceHandler.handle(prevPosition, newPosition);
            if(result != null)
                return result;
        }

        SuccessHandler successHandler = new BaseSuccessHandler();
        successHandler.setRaceManager(this);
        return successHandler.handle(prevPosition, newPosition);
    }


    /**
     * Sets the raceManager to all the handlers of the race.
     */
    private void initializeHandlers() {
        for(RaceHandler raceHandler : this.handlers)
            raceHandler.setRaceManager(this);
    }



    /**
     * Initializes the positions of the players on the starting line. Each player is assigned
     * a unique position on the start line, and if there are more players than positions
     * on the line, they are positioned in front of the start line.
     *
     * @return A list of {@link Position} representing the initial positions of the players.
     */
    private List<Position> calcInitialPlayersPosition() {

        List<Position> startLinePositions = this.circuitManager.getStartLine().getPositions();
        List<Position> initialPlayersPositions = new ArrayList<>();

        if (this.playersManager.playersCount() <= startLinePositions.size()){
            for(int i = 0; i < this.playersManager.playersCount(); i++)
                updateCircuitWithInitialization(initialPlayersPositions, startLinePositions.get(i));
            return startLinePositions.subList(0, this.playersManager.playersCount());
        }


        initialPlayersPositions = new ArrayList<>(startLinePositions);
        for (int i = initialPlayersPositions.size(); i < this.playersManager.playersCount(); i++) {
            Position playerPosition = findFreeNeighbourPosition(initialPlayersPositions.get(i - startLinePositions.size()));

            if(playerPosition == null)
                throw new NullPointerException("Too many players, there are no free nodes left to place other players.");

            updateCircuitWithInitialization(initialPlayersPositions, playerPosition);
        }

        return initialPlayersPositions;
    }


    /**
     * Updates the circuit with the initial position of a player and adds this position to the list
     * of initial positions.
     *
     * @param initialPlayersPositions The list of initial player positions.
     * @param playerPosition The position to assign to a player.
     */
    private void updateCircuitWithInitialization(List<Position> initialPlayersPositions, Position playerPosition){
        PositionUtils.validateCircuitNodePosition(playerPosition);

        for(Position position : initialPlayersPositions)
            PositionUtils.validateCircuitNodePosition(position);

        this.circuitManager.setOccupied(playerPosition);
        initialPlayersPositions.add(playerPosition);
    }


    /**
     * Sets the initial positions of all players according to the provided list of positions.
     *
     * @param initialPlayersPositions A list of positions to assign to the players.
     */
    private void setInitialPlayersPosition(List<Position> initialPlayersPositions) {

        for(int i = this.playersManager.playersCount() - 1, j = 0; i >= 0 && j < this.playersManager.playersCount(); i--, j++){
            Player currentPlayer = this.playersManager.getPlayers().get(i);
            Position currentPlayerPosition = initialPlayersPositions.get(j);
            this.playersManager.updatePosition(currentPlayer, currentPlayerPosition);
        }

    }


    /**
     * Retrieves the initial positions of all players after they have been positioned on the start line.
     *
     * @return a list of {@link Player} objects with their assigned positions on the start line.
     * @throws IllegalStateException if the positions were not initialized with {@link #calcInitialPlayersPosition()}
     */
    public List<Player> getInitialPlayersPositions(){

        for(Player player : this.playersManager.getPlayers())
            if(player.getPosition() == null)
                throw new IllegalStateException("Player: " + player.getName() + " has a null start position");

        return this.playersManager.getPlayers();
    }


    /**
     * Finds a free position neighboring the given position on the circuit.
     * This method searches for the 8 nearest neighbours from the top-left to the bottom-right.
     * The first one to be free is returned.
     *
     * @param position The reference position from which to find a neighboring free position.
     * @return A {@link Position} that is free and neighboring the given position.
     */
    private Position findFreeNeighbourPosition(Position position) {
        return this.circuitManager.getFreeNeighbour(position);
    }


    /**
     * Retrieves the moves the player can do.
     *
     * @return the {@link PlayerMoves} available for the next player
     */
    public PlayerMoves getCurrentPlayerMoves() {return this.playersManager.getCurrentPlayerMoves();}


    /**
     * Returns the current player playing.
     *
     * @return the {@link Player} who is currently making a move
     */
    public Player getCurrentPlayer() {return this.playersManager.getCurrentPlayer();}


    /**
     * Advances to the next player in the queue
     */
    public void updateCurrentPlayer() {this.playersManager.advanceToNextPlayer();}


    /**
     * Returns the circuit manager used to handle the circuit state.
     *
     * @return the {@link CircuitManager} managing the circuit
     */
    public CircuitManager getCircuitManager() {return this.circuitManager;}


    /**
     * Returns the players manager used to handle player-related operations.
     *
     * @return the {@link PlayersManager} managing the players
     */
    public PlayersManager getPlayersManager() {return this.playersManager;}


    /**
     * Checks if the remaining players playing are 0 after a player crashed.
     * @return Returns true if the amount of player remaining is 0.
     */
    public boolean wasLastPlayer(){return this.playersManager.getPlayers().isEmpty();}
}
