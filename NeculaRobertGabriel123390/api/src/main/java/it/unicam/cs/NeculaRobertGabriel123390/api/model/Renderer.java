package it.unicam.cs.NeculaRobertGabriel123390.api.model;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;



/**
 * The {@code Renderer} class is responsible for rendering and updating the visual representation
 * of the race circuit and players on a graphical user interface.
 *
 * <p>This class provides static methods to draw the circuit grid, circuit shape, and player positions
 * on the GUI. It can draw lines representing the circuit and circles representing players' positions.
 * The {@code render} and {@code update} methods delegate rendering and updating tasks to the appropriate
 * objects, ensuring separation of concerns and modularity in the rendering process.</p>
 */
public final class Renderer {


    /**
     * Renders the graphical representation of some given {@link Renderable} object(s).
     *
     * @param renderable the {@link Renderable} object(s) to be rendered.
     * @throws NullPointerException if {@code renderable} is {@code null}.
     */
    public void render(Renderable<?> ...renderable) {
        for(Renderable<?> r : renderable)
            r.render();
    }


    /**
     * Updates the graphical representation based on the provided data.
     *
     * @param <D> The type of data to be used for the update.
     * @param updatable The {@link Updatable} object to be updated.
     * @param data The data to update the object with. It can be of any type.
     * @throws NullPointerException if {@code updatable} is {@code null}.
     */
    public <D> void update(Updatable<D> updatable, D data){
        updatable.update(data);
    }


    /**
     * Validates the positions and color for drawing a line.
     *
     * @param pane The Pane where the line will be drawn.
     * @param pos1 The starting position of the line.
     * @param pos2 The ending position of the line.
     * @param color The color of the line.
     * @throws NullPointerException if {@code pane}, {@code pos1}, {@code pos2} or {@code color} is {@code null}.
     * @throws IllegalArgumentException if {@code pos1} or {@code pos2} have invalid coordinate values.
     */
    private static void validateLineToDraw(Pane pane, Position pos1, Position pos2, Color color) {
        PositionUtils.validateCircuitNodePosition(pos1);
        PositionUtils.validateCircuitNodePosition(pos2);

        if(pane == null)
            throw new NullPointerException("pane is null");
        if(color == null)
            throw new NullPointerException("color is null");
    }


    /**
     * Validates the positions and color for drawing a circle, which represents the current position of a player.
     *
     * @param pane The Pane where the circle will be drawn.
     * @param centerPosition The center position for the circle
     * @param color The color of the circle.
     * @throws NullPointerException if {@code pane}, {@code pos1}, {@code pos2} or {@code color} is {@code null}.
     * @throws IllegalArgumentException if {@code centerPosition} has invalid coordinate values.
     */
    private static void validateCircleToDraw(Pane pane, Position centerPosition, Color color) {
        PositionUtils.validateCircuitNodePosition(centerPosition);

        if(color == null)
            throw new NullPointerException("color is null");
        if(pane == null)
            throw new NullPointerException("pane is null");
    }


    /**
     * Draws a line based on the provided circuit line data.
     * <p>
     * This method draws lines between consecutive positions in the circuit line. It handles lines with a maximum of two nodes.
     * </p>
     *
     * @param container The container where the line will be drawn
     * @param line The line data to draw
     */
    public static void drawLine(Pane container, CircuitLine line) {
        if(line == null)
            throw new NullPointerException("line is null");

        for (int i = 0; i < line.getPositions().size() - 1; i++) {
            Position pos1 = line.getNode(i);
            Position pos2 = line.getNode(i + 1);
            drawLine(container, pos1, pos2, line.getColor());
        }

    }


    /**
     * Draws a line between two positions with the specified color.
     *
     * @param container The {@link Pane} where the line will be added.
     * @param pos1 The starting position of the line.
     * @param pos2 The ending position of the line.
     * @param color The color of the line.
     */
    public static void drawLine(Pane container, Position pos1, Position pos2, Color color) {
        validateLineToDraw(container, pos1, pos2, color);

        Line drawLine = new Line();
        Position scaledPos1 = PositionUtils.scalePositionToDraw(pos1);
        Position scaledPos2 = PositionUtils.scalePositionToDraw(pos2);

        drawLine.setStartX(scaledPos1.getX());
        drawLine.setStartY(scaledPos1.getY());

        drawLine.setEndX(scaledPos2.getX());
        drawLine.setEndY(scaledPos2.getY());

        drawLine.setStroke(color);
        drawLine.setStrokeWidth(2);
        container.getChildren().add(drawLine);
    }


    /**
     * Draws a circle representing the current position of a player on the circuit.
     *
     * @param container   the {@link Pane} where the circle will be added.
     * @param player the {@link Player} whose position will be represented by the circle.
     * @throws NullPointerException if {@code container} or {@code player} is {@code null}.
     */
    public static void drawCircle(Pane container, Player player) {
        PlayerValidator.validate(player);
        drawCircle(container, player.getPosition(), player.getColor());
    }


    /**
     * Draws a circle representing the current position of a player on the circuit.
     *
     * @param container   the {@link Pane} to which the circle will be added.
     * @param centerPosition the position whose position will be represented by the circle.
     * @param color The color of the circle.
     * @throws NullPointerException if {@code container} or {@code player} is {@code null}.
     */
    public static void drawCircle(Pane container, Position centerPosition, Color color) {
        validateCircleToDraw(container, centerPosition, color);

        Circle playerPoint = new Circle();
        Position scaledToDrawPlayerPosition = PositionUtils.scalePositionToDraw(centerPosition);

        playerPoint.setCenterX(scaledToDrawPlayerPosition.getX());
        playerPoint.setCenterY(scaledToDrawPlayerPosition.getY());

        playerPoint.setRadius(4);
        playerPoint.setFill(color);
        container.getChildren().add(playerPoint);
    }

}
