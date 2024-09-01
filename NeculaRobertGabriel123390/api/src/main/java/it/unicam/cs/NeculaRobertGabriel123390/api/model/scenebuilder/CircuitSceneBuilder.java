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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.scenebuilder;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.TXTCircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitUtils;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;


/**
 * {@code CircuitSceneBuilder} is responsible for constructing and rendering the visual representation of a circuit
 * on a specified grid layout. This class extends {@link SceneBuilder} and implements {@link Updatable}, allowing
 * for dynamic updates to the circuit display based on player movements. It ensures that the circuit layout, including
 * the track and start/end lines, is accurately represented and can be updated as the game progresses.
 * <p>
 * The {@code CircuitSceneBuilder} uses the provided {@link Circuit} data to generate a grid of tiles representing
 * the circuit layout. It draws lines to represent the circuit's path and highlights the start and end lines. The
 * class also provides methods to update the visual representation to reflect player movements on the circuit.
 * </p>
 */
public final class CircuitSceneBuilder extends SceneBuilder<Pane, Circuit> implements Updatable<MoveResult>{


    /**
     * Constructs a new {@code CircuitSceneBuilder} instance with the specified grid container and circuit data.
     *
     * @param circuitGrid The {@link Pane} where the circuit will be rendered.
     * @param circuit     The {@link Circuit} data used to build and display the grid.
     * @throws NullPointerException if either {@code circuitGrid} or {@code circuit} is {@code null}.
     */
    public CircuitSceneBuilder(Pane circuitGrid, Circuit circuit){
        super(circuitGrid, circuit);
        CircuitValidator.validate(circuit);
    }


    /**
     * Renders the circuit by first displaying the grid layout, then drawing the circuit shape
     * based on the provided circuit data. This method implements the {@link Renderable#render()}
     * method.
     */
    @Override
    public void render() {
        displayCircuitGrid();
        displayCircuitShape();
    }


    /**
     * Updates the circuit display to reflect a player's move.
     * <p>
     * This method draws a line representing the player's movement from the previous position
     * to the current position and updates the player's position on the grid. It ensures that the
     * visual representation of the player's movement is accurately reflected in the circuit display.
     * </p>
     *
     * @param moveResult The result of the player's move, containing previous and new positions, as well as the player's color.
     * @throws NullPointerException if {@code moveResult} is {@code null}.
     */
    @Override
    public void update(MoveResult moveResult) {
        MoveResultValidator.validate(moveResult);
        CircuitLine moveLine = createCircuitLine(moveResult.prevOrNewPosition(), moveResult.player().getPosition(), moveResult.player().getColor());
        Renderer.drawLine(this.container, moveLine);

        if(moveResult.moveType() != MoveResultType.CRASH_LEAVE_RACE && moveResult.moveType() != MoveResultType.COLLISION_ALLOWED)
            Renderer.drawCircle(this.container, moveResult.player());
        else
            Renderer.drawCircle(this.container, moveResult.prevOrNewPosition(), moveResult.player().getColor());
    }



    /**
     * Displays the grid where the circuit will be rendered by drawing a grid of white rectangles
     * on the pane. Each rectangle represents a tile in the circuit grid.
     */
    public void displayCircuitGrid() {
        for (int x = 0; x < TXTCircuitSetup.MAX_NODES_X; x++) {
            for (int y = 0; y < TXTCircuitSetup.MAX_NODES_Y; y++) {
                Position tilePosition = new Position(x, y);
                Rectangle rectangle = createTile(tilePosition);
                this.container.getChildren().add(rectangle);
            }
        }
    }


    /**
     * Creates and displays the circuit shape on the grid by drawing lines between circuit nodes
     * based on the circuit data. This includes both the track lines and the start/end lines.
     */
    public void displayCircuitShape(){

        List<Position> extNodes = getPositionOfExternNodes();
        for(Position nodePosition : extNodes) {
            List<Position> neighbours = CircuitUtils.get8Neighbours(this.data.getCircuitMap(), nodePosition);

            for(Position neighbourPosition : neighbours) {
                if (isRightOrDown(nodePosition, neighbourPosition) && this.data.isCircuitNodeExtern(neighbourPosition)) {
                    CircuitLine line = createCircuitLine(nodePosition, neighbourPosition, Color.BLACK);
                    Renderer.drawLine(this.container, line);
                }
            }
        }

        Renderer.drawLine(this.container, createStartLine());
        Renderer.drawLine(this.container, createEndLine());
    }


    /**
     * Creates a single tile represented by a white rectangle at the specified position.
     *
     * @param tilePosition The position of the tile.
     * @return A {@link Rectangle} representing the tile at the specified position.
     * @throws NullPointerException if {@code tilePosition} is {@code null}.
     * @throws IllegalArgumentException if {@code tilePosition} is ouf of bounds for the circuit.
     */
    private Rectangle createTile(Position tilePosition) {
        PositionUtils.validateCircuitNodePosition(tilePosition);

        Rectangle rectangle = new Rectangle(CircuitSetup.DIM_RECT, CircuitSetup.DIM_RECT);
        Position rectangleScaledToDrawPosition = PositionUtils.scalePositionToDraw(tilePosition);

        rectangle.setX(rectangleScaledToDrawPosition.getX());
        rectangle.setY(rectangleScaledToDrawPosition.getY());

        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.DARKGRAY);

        return rectangle;
    }


    /**
     * Creates a {@link DrawCircuitLine} between two positions with a specified color.
     *
     * @param startPosition The starting position of the line.
     * @param endPosition   The ending position of the line.
     * @param color         The color of the line.
     * @return A {@link CircuitLine} connecting the two positions.
     * @throws NullPointerException if either {@code startPosition} or {@code endPosition} is {@code null}.
     * @throws IllegalArgumentException if {@code startPosition} or {@code endPosition} are ouf of bounds for the circuit.
     */
    private CircuitLine createCircuitLine(Position startPosition, Position endPosition, Color color) {
        PositionUtils.validateCircuitNodePosition(startPosition);
        PositionUtils.validateCircuitNodePosition(endPosition);

        CircuitLine line = new DrawCircuitLine();
        line.addNode(startPosition);
        line.addNode(endPosition);
        line.setColor(color);
        return line;
    }



    /**
     * Creates the start line for the circuit based on the circuit data.
     *
     * @return A {@link CircuitLine} representing the start line, colored green.
     */
    private CircuitLine createStartLine() {
        List<Position> startLineNodesPositions = this.data.getStartLine().getPositions();
        CircuitLine startLine = new StartEndCircuitLine();

        for (Position startLineNodesPosition : startLineNodesPositions)
            startLine.addNode(startLineNodesPosition);

        startLine.setColor(Color.GREEN);

        return startLine;
    }


    /**
     * Creates the end line for the circuit based on the circuit data.
     *
     * @return A {@link CircuitLine} representing the end line, colored red.
     */
    private CircuitLine createEndLine() {
        List<Position> endLinePositions = this.data.getEndLine().getPositions();
        CircuitLine endLine = new StartEndCircuitLine();

        for (Position endLinePosition : endLinePositions)
            endLine.addNode(endLinePosition);


        endLine.setColor(Color.RED);

        return endLine;
    }


    /**
     * Determines if the neighboring position is to the right or below the current node position.
     * This is used to decide the direction of drawing circuit lines.
     *
     * @param nodePosition      The current node's position.
     * @param neighbourPosition The neighboring node's position.
     * @return {@code true} if the neighbor is to the right or below the current node, {@code false} otherwise.
     * @throws NullPointerException if either {@code nodePosition} or {@code neighbourPosition} is {@code null}.
     * @throws IllegalArgumentException if {@code nodePosition} or {@code neighbourPosition} are ouf of bounds for the circuit.
     */
    private boolean isRightOrDown(Position nodePosition, Position neighbourPosition) {
        PositionUtils.validateCircuitNodePosition(nodePosition);
        PositionUtils.validateCircuitNodePosition(neighbourPosition);

        return (neighbourPosition.getX() == nodePosition.getX() && neighbourPosition.getY() > nodePosition.getY()) ||
                (neighbourPosition.getY() == nodePosition.getY() && neighbourPosition.getX() > nodePosition.getX());
    }


    /**
     * Retrieves the positions of all external nodes in the circuit.
     *
     * @return A list of {@link Position} objects representing the external nodes of the circuit.
     */
    private List<Position> getPositionOfExternNodes(){
        List<Position> extNodes = new ArrayList<>();
        for(Position position : this.data.getPositions())
            if (this.data.isCircuitNodeExtern(position))
                extNodes.add(position);

        return extNodes;
    }

}