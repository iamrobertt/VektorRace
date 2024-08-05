package it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitNode;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitNodeState;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitLine;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that sets up a circuit from a txt file
 */
public class TXTCircuitSetup implements CircuitSetup {

    public static int LAST_ROW = 38;
    public static int PLAYERS_NUMBER_ROW = 37;
    public static int HUMAN_PLAYERS_DATA_ROW = 38;

    /**
     * Method that returns a circuit from the fileData
     *
     * @param fileData - The file data
     * @return Circuit a circuit
     */
    @Override
    public Circuit setup(ParsedFileData fileData) {

        validateCircuit(fileData);

        List<String> dataToList = extractDataToList(fileData);
        Map<Position, CircuitNode> circuitNodes = createCircuitNodes(dataToList);
        CircuitLine startLine = createStartLine(circuitNodes);
        CircuitLine endLine = createEndLine(circuitNodes);

        return new Circuit(circuitNodes, startLine, endLine);
    }


    /**
     * Method that checks if fileData is type TXTParsedFileData
     *
     * @param fileData - The file data
     */
    private void validateCircuit(ParsedFileData fileData) {
        if (!(fileData instanceof TXTParsedFileData))
            throw new IllegalArgumentException("Data passed is not instance of TXTParsedFileData");
    }


    /**
     * Method that retrieves the fileData as a list
     *
     * @param fileData - The file data
     * @return List<String> - The file data in List format
     */
    private List<String> extractDataToList(ParsedFileData fileData) {
        return new ArrayList<>(((TXTParsedFileData) fileData).getData());
    }


    /**
     * Method that returns a list of only nodes that are part of the circuit.
     * The x coordinate represents the horizontal coordinate, the y coordinate represents the vertical one
     *
     * @param dataToList - The data in list format
     * @return Map<Position, CircuitNode> - The only nodes that are part of the circuit
     */
    private Map<Position, CircuitNode> createCircuitNodes(List<String> dataToList) {
        Map<Position, CircuitNode> circuitNodes = new HashMap<>();
        for (int y = 1; y <= CircuitSetup.MAX_NODES_Y; y++) {
            for (int x = 0; x < CircuitSetup.MAX_NODES_X; x++) {
                char character = dataToList.get(y).charAt(x);
                CircuitNode circuitNode = new CircuitNode(new Position(x, y), getNodeTypeFromCharacter(character));
                if (circuitNode.getState() != CircuitNodeState.nonTrackNode)
                    circuitNodes.put(circuitNode.getPosition(), circuitNode);
            }
        }

        return circuitNodes;
    }


    /**
     * Methods that creates the start line based on node type
     *
     * @param circuitNodes - The nodes that are part of the circuit
     * @return Line The starting line
     */
    private CircuitLine createStartLine(Map<Position, CircuitNode> circuitNodes) {
        CircuitLine startLine = new CircuitLine();
        for (CircuitNode circuitNode : circuitNodes.values())
            if (circuitNode.getState() == CircuitNodeState.startNode)
                startLine.addNode(circuitNode);
        return startLine;
    }


    /**
     * Methods that creates the end line based on node type
     *
     * @param circuitNodes - The nodes that are part of the circuit
     * @return Line The ending line
     */
    private CircuitLine createEndLine(Map<Position, CircuitNode> circuitNodes) {
        CircuitLine endLine = new CircuitLine();
        for (CircuitNode circuitNode : circuitNodes.values())
            if (circuitNode.getState() == CircuitNodeState.endNode)
                endLine.addNode(circuitNode);

        return endLine;
    }


    /**
     * Method that provides a CircuitNodeState for circuitNodes based on the value of the character
     * @param c - A character representing a node in a circuit
     * @return CircuitNodeState the state based on character value
     */
    public static CircuitNodeState getNodeTypeFromCharacter(char c) {
        return switch (c) {
            case '#' -> CircuitNodeState.nonTrackNode;
            case '@' -> CircuitNodeState.trackNode;
            case '+' -> CircuitNodeState.startNode;
            case '-' -> CircuitNodeState.endNode;
            case '*' -> CircuitNodeState.wallNode;
            default -> throw new IllegalArgumentException("Unexpected character: " + c);
        };
    }
}
