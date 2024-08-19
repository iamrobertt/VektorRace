package it.unicam.cs.NeculaRobertGabriel123390.api.model.scenebuilder;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.PlayerMoves;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Updatable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * Manages the graphical representation of possible moves in a grid layout.
 * <p>
 * This class sets up and updates a {@link GridPane} with buttons representing possible moves
 * based on the provided {@link PlayerMoves} data. Each button is assigned a position and displays
 * the coordinates of the move. The grid pane is expected to contain exactly 10 children: 9 buttons
 * representing moves and 1 group for additional UI components.
 * </p>
 * <p>
 * The class extends {@link SceneBuilder} and implements the {@link Updatable} interface,
 * enabling it to render the grid initially and update it when new move data is provided.
 * </p>
 */
public final class MovesGridSceneBuilder extends SceneBuilder<GridPane, PlayerMoves> implements Updatable<PlayerMoves> {


    /**
     * Constructs a {@code MovesGridSceneBuilder} instance with the specified grid pane and player moves.
     *
     * @param inputMovesGrid The {@code GridPane} containing the buttons and group.
     * @param playerPossibleMoves The {@code PlayerMoves} to be displayed on the grid.
     * @throws NullPointerException if {@code inputMovesGrid} or {@code playerPossibleMoves} is {@code null}.
     * @throws IllegalArgumentException if {@code inputMovesGrid} does not contain exactly 10 children
     *                                  (9 buttons and 1 group) or contains unexpected nodes.
     */
    public MovesGridSceneBuilder(GridPane inputMovesGrid, PlayerMoves playerPossibleMoves) {
        super(inputMovesGrid, playerPossibleMoves);
        validateMoveGrid(inputMovesGrid, playerPossibleMoves);
    }


    /**
     * Validates the provided grid pane and player moves.
     *
     * @param inputMovesGrid the grid pane to validate
     * @param playerPossibleMoves the player moves to validate
     * @throws NullPointerException if {@code inputMovesGrid} or {@code playerPossibleMoves} is {@code null}
     * @throws IllegalArgumentException if {@code inputMovesGrid} does not contain exactly 10 children
     *                                  (9 buttons and 1 group) or contains unexpected nodes
     */
    private void validateMoveGrid(GridPane inputMovesGrid, PlayerMoves playerPossibleMoves) {
        int countButtons = 0;
        int countGroup = 0;

        if(inputMovesGrid == null)
            throw new NullPointerException("inputMovesGrid is null");
        if(playerPossibleMoves == null)
            throw new NullPointerException("playerPossibleMoves is null");
        if(inputMovesGrid.getChildren().size() != 10)
            throw new IllegalArgumentException("Must have 10 children, 9 buttons and a Group");

        for(Node node : inputMovesGrid.getChildren())
            if(node instanceof Button) countButtons++;
            else if(node instanceof Group) countGroup++;
            else throw new IllegalArgumentException("Unexpected node:" + node.getClass().getName());


        if(countButtons != 9 && countGroup != 1)
            throw new IllegalArgumentException("Must have 9 buttons");
    }


    /**
     * Renders the initial grid setup by populating it with buttons representing the possible moves.
     * <p>
     * This method is called to initialize the grid with the current state of possible moves.
     * It assigns positions and coordinates to each button.
     * </p>
     */
    @Override
    public void render() {
        setupGrid();
    }


    /**
     * Updates the grid with new {@link PlayerMoves} data.
     * <p>
     * This method updates the button configurations in the grid to reflect the new possible moves.
     * It is called when the data representing possible moves changes.
     * </p>
     *
     * @param updatedData The new {@code PlayerMoves} data to update the grid with.
     * @throws NullPointerException if {@code updatedData} is {@code null}.
     */
    @Override
    public void update(PlayerMoves updatedData) {
        if(updatedData == null)
            throw new NullPointerException("updatedData is null");

        this.data = updatedData;
        setupGrid();
    }


    /**
     * Sets up the grid with buttons representing the current possible moves based on the provided
     * {@link PlayerMoves}. Each button's user data is set to the corresponding {@link Position} and its text
     * is updated to show the coordinates.
     */
    public void setupGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Position positionToSet = this.data.getMoves()[i][j];
                int index = i * 3 + j;
                Button button = (Button) this.container.getChildren().get(index);
                button.setUserData(positionToSet);
                button.setText("(" + this.data.getMoves()[i][j].getX() + "," + this.data.getMoves()[i][j].getY() + ")");
            }
        }
    }


    /**
     * Disables all buttons in the grid, preventing any further interaction.
     * This method is typically invoked when all players have crashed, and there are no remaining players
     * to take a turn.
     */
    public void disableGrid(){
        for(Node node : this.container.getChildren())
            node.setDisable(true);
    }


}
