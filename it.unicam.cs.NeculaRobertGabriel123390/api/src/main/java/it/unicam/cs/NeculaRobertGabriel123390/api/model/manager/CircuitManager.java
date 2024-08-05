package it.unicam.cs.NeculaRobertGabriel123390.api.model.manager;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitLine;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitNodeState;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;


/**
 * Class that manages the circuit updates and checks for the race
 */
public class CircuitManager {


    private final Circuit circuit;


    public CircuitManager(Circuit circuit) {
        validateCircuit(circuit);
        this.circuit = circuit;
    }


    /**
     * Method that checks if the circuit is valid
     * @param circuit - The circuit to validate
     */
    private void validateCircuit(Circuit circuit){
        if(circuit == null)
            throw new NullPointerException("Circuit is null");
    }


    /**
     * Method that updates the states of the positions
     * @param prevPosition - The previous player position, before the move
     * @param movePosition - The chosen move
     */
    public void updateCircuitAfterMove(Position prevPosition, Position movePosition) {
        Position updatedPosition = PositionUtils.addPositions(prevPosition, movePosition);
        setFree(prevPosition);
        setOccupied(updatedPosition);
    }


    /**
     * Method that checks if the player, with the chosen move, will collide with another player
     * @param newPosition - The position reached after the move
     * @return True if another player is present on that position, false otherwise
     */
    public boolean isColliding(Position newPosition){
        return this.circuit.getCircuitNode(newPosition).getState().equals(CircuitNodeState.occupied);
    }


    /**
     * Method that checks all the nodes between the start and end position and checks if all the nodes are part of the track
     * @param prevPosition - The previous player position, before the move
     * @param newPosition - The position of the player after the move
     * @return True if another player has crashed, false otherwise
     */
    public boolean isCrashing(Position prevPosition, Position newPosition){
        //todo controlla tutti i nodi tra la prev e la new position

        return this.circuit.getCircuitNode(newPosition) == null;
    }


    /**
     * Method that updates the state of the node to occupied after moving
     * @param position - the position to update
     */
    public void setOccupied(Position position){this.circuit.getCircuitNode(position).setState(CircuitNodeState.occupied);}


    /**
     * Method that updates the state of the node to trackNode after moving
     * @param position - the position to update
     */
    private void setFree(Position position){this.circuit.getCircuitNode(position).setState(CircuitNodeState.trackNode);}


    public CircuitLine getStartLine(){return this.circuit.getStartLine();}

}
