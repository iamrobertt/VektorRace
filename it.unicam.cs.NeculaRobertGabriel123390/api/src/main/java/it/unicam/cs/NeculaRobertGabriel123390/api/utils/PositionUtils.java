package it.unicam.cs.NeculaRobertGabriel123390.api.utils;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;


/**
 * Class that supplies util methods to handle position operations
 */
public class PositionUtils {


    /**
     * Method that adds the x and y coordinates of 2 positions
     * @param pos1 - the first position
     * @param pos2 - the second position
     * @return Position a new position that is the sum of the 2 positions
     */
    public static Position addPositions(Position pos1, Position pos2){
        return new Position(pos1.getX() + pos2.getX(), pos1.getY() + pos2.getY());
    }


    /**
     * Method that subtracts the x and y coordinates of 2 positions
     * @param pos1 - the first position
     * @param pos2 - the second position
     * @return Position a new position that is the subtraction of the 2 positions
     */
    public static Position subPositions(Position pos1, Position pos2){
        return new Position(pos1.getX() - pos2.getX(), pos1.getY() - pos2.getY());
    }


    /**
     * Method that takes a position and scales it to display an object correctly
     * @param position - The position to scale
     * @return Position The scaled position adapted to view
     */
    public static Position scalePositionToDraw(Position position) {
        return new Position(position.getX() * CircuitSetup.DIM_RECT, position.getY() * CircuitSetup.DIM_RECT);
    }


    /**
     * Method that checks if the position of a circuit node is valid
     * @param position - The position to check
     */
    public static void validateCircuitNodePosition(Position position) {
        if(position.getX() < 0 || position.getX() > CircuitSetup.MAX_NODES_X ||
                position.getY() < 0 || position.getY() > CircuitSetup.MAX_NODES_Y)
            throw new IllegalArgumentException(position + "is out of bounds");

    }

}
