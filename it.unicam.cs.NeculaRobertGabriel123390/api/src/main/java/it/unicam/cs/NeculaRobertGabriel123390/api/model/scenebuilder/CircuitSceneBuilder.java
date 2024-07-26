package it.unicam.cs.NeculaRobertGabriel123390.api.model.scenebuilder;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitNode;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitTile;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitNodeState;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;


/**
 * Class that build and displays a grid with the circuit tiles inside, providing a mapped circuit
 */
public class CircuitSceneBuilder{


    private final Circuit circuit;
    private final Pane circuitGrid;
    private final Map<Position, CircuitTile> mappedCircuit;


    public CircuitSceneBuilder(Circuit circuit, Pane circuitGrid){
        validateCircuitBuilder(circuit, circuitGrid);
        this.mappedCircuit = new HashMap<Position, CircuitTile>();
        this.circuit = circuit;
        this.circuitGrid = circuitGrid;
    }


    /**
     * Method that displays the circuit grid
     */
    public void build() {
        initializeGrid();
        displayCircuitTiles();
    }


    /**
     * Method that checks if data passed to constructor is valid
     *
     * @param circuit  - The circuit
     * @param circuitGrid - The pane
     */
    private void validateCircuitBuilder(Circuit circuit, Pane circuitGrid) {
        if (circuit == null) throw new NullPointerException("Circuit is null");
        if (circuitGrid == null) throw new NullPointerException("gridPane is null is null");
    }


    /**
     * Method that displays a grid where all rectangles are by default non track nodes
     */
    private void initializeGrid() {
        for (int x = 0; x < CircuitSetup.MAX_NODES_X; x++) {
            for (int y = 0; y < CircuitSetup.MAX_NODES_Y; y++) {
                CircuitTile tile = createTile(new Position(x, y));
                this.circuitGrid.getChildren().add(tile.getRectangle());
            }
        }
    }


    /**
     * Method that creates a single tile in the scene
     * @param tilePosition - The position of the tile to create
     * @return CircuitTile the tile at specified position
     */
    private CircuitTile createTile(Position tilePosition) {

        CircuitNodeState tileState = CircuitNodeState.nonTrackNode;
        CircuitTile circuitTile = new CircuitTile(tilePosition, tileState);

        this.mappedCircuit.put(tilePosition, circuitTile);
        return circuitTile;
    }


    /**
     * Method that takes only the circuit nodes and displays them as tiles based on every the node position and type
     */
    private void displayCircuitTiles() {

        for (Map.Entry<Position, CircuitNode> circuitNodeEntry : this.circuit.getCircuitMap().entrySet()){
            Position circuitNodePosition = circuitNodeEntry.getKey();
            CircuitNode circuitNode = circuitNodeEntry.getValue();
            this.mappedCircuit.get(circuitNodePosition).setState(circuitNode.getType());
        }
    }


    public Map<Position, CircuitTile> getMappedCircuit() {
        return this.mappedCircuit;
    }

}
