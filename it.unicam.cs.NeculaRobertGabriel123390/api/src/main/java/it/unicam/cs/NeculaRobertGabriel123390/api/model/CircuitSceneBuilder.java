package it.unicam.cs.NeculaRobertGabriel123390.api.model;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that build and displays a grid with the circuit tiles inside, providing a mapped circuit
 */
public class CircuitSceneBuilder{


    private final Circuit circuit;
    private final Pane circuitGrid;


    public CircuitSceneBuilder(Circuit circuit, Pane circuitGrid){
        validateCircuitBuilder(circuit, circuitGrid);
        this.circuit = circuit;
        this.circuitGrid = circuitGrid;
    }


    /**
     * Method that displays the circuit grid
     */
    public void build() {
        initializeGrid();
        displayCircuitShape();
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
     * Method that displays a grid of white rectangles to create the area where the circuit will be drawn
     */
    private void initializeGrid() {
        for (int x = 0; x < CircuitSetup.MAX_NODES_X; x++) {
            for (int y = 0; y < CircuitSetup.MAX_NODES_Y; y++) {
                Rectangle tile = createTile(new Position(x, y));
                this.circuitGrid.getChildren().add(tile);
            }
        }
    }


    /**
     * Method that creates a single tile in the scene
     * @param tilePosition - The position of the tile to create
     * @return Rectangle The tile at specified position
     */
    private Rectangle createTile(Position tilePosition) {

        Rectangle rectangle = new Rectangle(CircuitSetup.DIM_RECT, CircuitSetup.DIM_RECT);
        Position rectangleScaledToDrawPosition = PositionUtils.scalePositionToDraw(tilePosition);

        rectangle.setX(rectangleScaledToDrawPosition.getX());
        rectangle.setY(rectangleScaledToDrawPosition.getY());

        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.DARKGRAY);

        return rectangle;
    }


    /**
     * Method that draws the circuit shape based on the circuit data
     */
    private void displayCircuitShape(){
        drawStartLine();
        drawEndLine();
        List<Position> extNodes = getPositionOfExternNodes();

        for(Position nodePosition : extNodes) {
            List<Position> neighbours = getNodeNeighbours(nodePosition);

            for(Position neighbourPosition : neighbours)
                if(isRightOrDown(nodePosition, neighbourPosition) && isCircuitNodeExtern(neighbourPosition))
                    drawLine(nodePosition, neighbourPosition, Color.BLACK);
        }

    }


    /**
     * Method that draws the end line into the circuit
     */
    private void drawEndLine() {
        List<CircuitNode> endLineNodes = this.circuit.getEndLine().getNodesOfLine();

        for(int i = 0; i < endLineNodes.size() - 1; i++)
            drawLine(endLineNodes.get(i).getPosition(), endLineNodes.get(i+1).getPosition(), Color.RED);

    }


    /**
     * Method that draws the start line into the circuit
     */
    private void drawStartLine() {
        List<CircuitNode> startLineNodes = this.circuit.getStartLine().getNodesOfLine();

        for(int i = 0; i < startLineNodes.size() - 1; i++)
            drawLine(startLineNodes.get(i).getPosition(), startLineNodes.get(i+1).getPosition(), Color.GREEN);
    }


    /**
     *  Method that draws a line from the node position to the neighbour position
     * @param nodePosition1 - The node position
     * @param nodePosition2 - A neighbour position
     */
    private void drawLine(Position nodePosition1, Position nodePosition2, Color lineColor) {
        Line line = new Line();

        line.setStartX(PositionUtils.scalePositionToDraw(nodePosition1).getX());
        line.setStartY(PositionUtils.scalePositionToDraw(nodePosition1).getY());

        line.setEndX(PositionUtils.scalePositionToDraw(nodePosition2).getX());
        line.setEndY(PositionUtils.scalePositionToDraw(nodePosition2).getY());

        line.setStroke(lineColor);
        line.setStrokeWidth(2);
        this.circuitGrid.getChildren().add(line);
    }


    /**
     * Method that checks if the node is an extern node. If it has less than 8 neighbours, it's an extern node, otherwise it is not.
     * @param nodePosition - The node position
     * @return True if the node at that position is an extern node, False otherwise
     */
    private boolean isCircuitNodeExtern(Position nodePosition) {
        return getNodeNeighbours(nodePosition).size() != 8;
    }


    /**
     * Method that determines if the neighbour is to the right or below the current node.
     * @param nodePosition - The node position
     * @param neighbourPosition - A neighbour position
     * @return True if the neighbour is to the right or below, false otherwise
     */
    private boolean isRightOrDown(Position nodePosition, Position neighbourPosition) {
        return (neighbourPosition.getX() == nodePosition.getX() && neighbourPosition.getY() > nodePosition.getY()) ||
                (neighbourPosition.getY() == nodePosition.getY() && neighbourPosition.getX() > nodePosition.getX());
    }


    /**
     * Method that retrieves the position of all the external nodes in the circuit to create the shape
     * @return List<Position> A list of positions of the external nodes
     */
    private List<Position> getPositionOfExternNodes(){
        List<Position> extNodes = new ArrayList<>();
        for(Position position : this.circuit.getPositions())
            if(isCircuitNodeExtern(position)) extNodes.add(position);

        return extNodes;
    }


    /**
     * Method that supplies a list of the neighbours of that node
     * @param nodePosition - The node position
     * @return List<Position> A list of all the neighbours of that node
     */
    private List<Position> getNodeNeighbours(Position nodePosition) {
        List<Position> neighbours = new ArrayList<>();

        int[][] offsets = {
                {-1, 1}, {0, 1}, {1, 1},
                {-1, 0},         {1, 0},
                {-1, -1}, {0, -1}, {1, -1}
        };

        for (int[] offset : offsets) {
            int newX = nodePosition.getX() + offset[0];
            int newY = nodePosition.getY() + offset[1];
            Position neighbourPosition = new Position(newX, newY);

            if (this.circuit.getCircuitNode(neighbourPosition) != null)
                neighbours.add(neighbourPosition);
        }

        return neighbours;

    }

}