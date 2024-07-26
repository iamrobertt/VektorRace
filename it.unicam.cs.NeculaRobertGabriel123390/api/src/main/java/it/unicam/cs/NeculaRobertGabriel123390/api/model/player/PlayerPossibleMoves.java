package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;


/**
 * Class that stores the 9 possible moves that a player can make. The data is updated every time a new move is made
 */
public class PlayerPossibleMoves {


    private final Position[][] moves;


    /**
     * Starter values for every player
     */
    private final int[][] INIT_VALUES = {
            {-1, 1}, {0, 1}, {1, 1},
            {-1, 0}, {0, 0}, {1, 0},
            {-1, -1}, {0, -1}, {1, -1}
    };


    public PlayerPossibleMoves() {
        this.moves = new Position[3][3];
        setup();
    }


    /**
     * Method that sets up the moves based on the initial values
     */
    public void setup() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                this.moves[i][j] = new Position(INIT_VALUES[i * 3 + j][0], INIT_VALUES[i * 3 + j][1]);
    }


    /**
     * Method that takes the new move as a new position and adds it to all previous values
     * @param position - The new position relative to the current position
     */
    public void updateGridPositions(Position position) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                int initX = INIT_VALUES[i * 3 + j][0];
                int initY = INIT_VALUES[i * 3 + j][1];

                int newX = initX + position.getX();
                int newY = initY + position.getY();
                this.moves[i][j] = new Position(newX, newY);
            }
        }
    }


    public Position[][] getMoves() {return this.moves;}
}
