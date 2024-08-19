package it.unicam.cs.NeculaRobertGabriel123390.api.model.scenebuilder;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;

/**
 * The {@code PlayersSceneBuilder} class is responsible for building and displaying player data
 * in a {@link GridPane}. It visualizes player information including their colors and names,
 * and supports updates to the display based on game events.
 *
 * <p>This class extends {@link SceneBuilder} and implements the {@link Updatable} interface,
 * enabling it to render the initial player state and update the display in response to game
 * events such as player moves or game state changes.</p>
 */
public final class PlayersSceneBuilder extends SceneBuilder<GridPane, List<Player>> implements Updatable<MoveResult> {


    /**
     * Constructs a {@code PlayersSceneBuilder} instance with the specified list of players and grid pane.
     * @param players     The list of players to be displayed.
     * @param playersGrid The {@code GridPane} where player information will be displayed.
     * @throws NullPointerException if {@code players} or {@code playersGrid} is {@code null}.
     */
    public PlayersSceneBuilder(GridPane playersGrid, List<Player> players) {
        super(playersGrid, players);
    }


    /**
     * Renders the initial player data by populating the {@code GridPane} with player information.
     * This method is invoked to display all players' details in the grid.
     */
    @Override
    public void render() {
        displayPlayers();
    }


    /**
     * Updates the player display based on the provided {@link MoveResult}.
     * <p>
     * This method checks the type of move result and performs necessary updates to the grid pane.
     * If the move result indicates that a player has crashed or left the race, the player is removed
     * from the board. This ensures that the grid pane accurately reflects the current state of the game.
     * </p>
     *
     * @param moveResult The {@link MoveResult} used to determine how the display should be updated.
     * @throws NullPointerException if {@code moveResult} is {@code null}.
     */
    @Override
    public void update(MoveResult moveResult) {
        MoveResultValidator.validate(moveResult);
        if(moveResult.moveType().equals(MoveResultType.CRASH_LEAVE_RACE))
            removePlayerFromBoard();
    }


    /**
     * Clears the {@code GridPane} and displays the updated list of players.
     * This method is called when a player leaves the race, ensuring that the board is refreshed to reflect
     * the current state of the game.
     */
    private void removePlayerFromBoard() {
        this.container.getChildren().clear();
        displayPlayers();
    }



    /**
     * Populates the {@code GridPane} with rectangles representing player colors and text nodes
     * representing player names.
     * <p>
     * Each player is represented by a color-filled rectangle and a corresponding name. The rectangle
     * and text are placed in adjacent columns in the grid, with each player's information occupying a separate row.
     * </p>
     */
    private void displayPlayers() {
        for (int i = 0; i < this.data.size(); i++) {
            Rectangle playerColorRectangle = createRectangleWithPlayerColor(this.data.get(i));
            Text playerNameText = createTextWithPlayerName(this.data.get(i));
            this.container.add(playerColorRectangle, 0, i);
            this.container.add(playerNameText, 1, i);
        }
    }


    /**
     * Creates a rectangle filled with the specified color.
     *
     * @param player The player
     * @return A {@code Rectangle} filled with the player color.
     * @throws NullPointerException if {@code playerColor} is {@code null}.
     */
    private Rectangle createRectangleWithPlayerColor(Player player) {
        PlayerValidator.validate(player);

        Rectangle rectangle = new Rectangle(20, 20);
        rectangle.setFill(player.getColor());
        return rectangle;
    }


    /**
     * Creates a {@code Text} node with the specified player name.
     *
     * @param player The player
     * @return A {@code Text} node displaying the player name.
     * @throws NullPointerException if {@code playerName} is {@code null}.
     */
    private Text createTextWithPlayerName(Player player) {
        PlayerValidator.validate(player);
        return new Text(player.getName());
    }


    /**
     * Draws circles representing the initial positions of players on the provided {@code Pane}.
     *
     * <p>This method uses the {@link Renderer} class to draw circles for each player at their initial
     * positions on the circuit grid. This provides a visual representation of the players' starting positions
     * on the game board.</p>
     *
     * @param circuitGrid The {@link Pane} where player circles will be drawn.
     * @throws NullPointerException if {@code circuitGrid} is {@code null}.
     */
    public void displayInitialPlayerPosition(Pane circuitGrid) {
        if(circuitGrid == null)
            throw new NullPointerException("circuitGrid is null");

        for (Player player : this.data)
            Renderer.drawCircle(circuitGrid, player);
    }


}
