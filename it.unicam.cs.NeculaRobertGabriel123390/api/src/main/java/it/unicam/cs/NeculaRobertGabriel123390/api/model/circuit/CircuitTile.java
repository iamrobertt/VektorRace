package it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitNodeState;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Class that represent a tile of the circuit
 */
public class CircuitTile {

    private Rectangle rectangle;
    private final Position position;
    private CircuitNodeState state;


    public CircuitTile(Position position, CircuitNodeState tileState) {
        validateCircuitTale(position, tileState);
        this.position = position;
        this.state = tileState;
        createRect();
    }


    /** Method that validates the tile data
     * @param position  - The position of the tale
     * @param state - The current state of the tale
     */
    private void validateCircuitTale(Position position, CircuitNodeState state) {
        if (position == null) throw new NullPointerException("Position is null");
        if (state == null) throw new NullPointerException("TileState is null");
    }


    /**
     * Method that creates a rectangle object from given data
     */
    private void createRect() {

        this.rectangle = new Rectangle(CircuitSetup.DIM_RECT, CircuitSetup.DIM_RECT);
        this.rectangle.setX(this.position.getX() * CircuitSetup.DIM_RECT);
        this.rectangle.setY(this.position.getY() * CircuitSetup.DIM_RECT);

        this.state.applyStyle(this.rectangle);
        this.rectangle.setStroke(Color.DARKGRAY);
    }


    public CircuitNodeState getState() {
        return this.state;
    }


    /**
     * Method that modifies the tile state
     * @param newState - The new state
     */
    public void setState(CircuitNodeState newState) {
        this.state = newState;
        this.state.applyStyle(this.rectangle);
    }


    public Rectangle getRectangle() {
        return this.rectangle;
    }

}

