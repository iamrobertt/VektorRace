package it.unicam.cs.NeculaRobertGabriel123390.api.model;


import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;

/**
 * Class representing the 9 possible moves a player can make in a 3x3 grid relative to their current position.
 * <p>
 * The `PlayerMoves` class manages these possible moves, which are updated based on the player's current position.
 * </p>
 */
public final class PlayerMoves {


    private final Position[][] moves;


    /**
     * Initial relative position offsets for the 9 possible moves around the current position.
     */
    private final int[][] INIT_VALUES = {
            {-1, -1}, {0, -1}, {1, -1},
            {-1, 0}, {0, 0}, {1, 0},
            {-1, 1}, {0, 1}, {1, 1}
    };


    /**
     * Constructs a `PlayerMoves` instance and initializes the move positions.
     */
    public PlayerMoves() {
        this.moves = new Position[3][3];
        setup();
    }


    /**
     * Initializes the `moves` array with the default positions based on `INIT_VALUES`.
     */
    private void setup() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                this.moves[i][j] = new Position(INIT_VALUES[i * 3 + j][0], INIT_VALUES[i * 3 + j][1]);
    }


    /**
     * Updates the possible move positions based on the given new position relative to the current position.
     * <p>
     * Each move in the grid is recalculated by adding the specified position offsets to the current position.
     * </p>
     *
     * @param position The new position relative to the current position.
     */
    public void updatePossibleMoves(Position position) {
        PositionUtils.validatePosition(position);

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


    /**
     * Retrieves the current array of possible moves.
     *
     * @return A 2D array of `Position` objects representing the possible moves.
     */
    public Position[][] getMoves() {return this.moves;}


    /**
     * Resets the move positions to their initial values.
     */
    public void resetMoves(){setup();}

}
