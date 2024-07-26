package it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit;


import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitNodeState;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Line;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitNodeUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that sets up a circuit from a txt file
 */
public class TXTCircuitSetup implements CircuitSetup {



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
        Line startLine = createStartLine(circuitNodes);
        Line endLine = createEndLine(circuitNodes);

        return new Circuit(circuitNodes, startLine, endLine);
    }


    /**
     * Method that checks if the fileData is type TXTParsedFileData
     *
     * @param fileData - The file data
     */
    @Override
    public void validateCircuit(ParsedFileData fileData) {
        if (!(fileData instanceof TXTParsedFileData))
            throw new IllegalArgumentException("Data passed is not instance of ListCircuitData");
    }


    /**
     * Method that converts retrieves the fileData as a list
     *
     * @param fileData - The file data
     * @return List<String> - The file data in List format
     */
    private List<String> extractDataToList(ParsedFileData fileData) {
        return new ArrayList<>(((TXTParsedFileData) fileData).getData());
    }


    /**
     * Method that returns a list of only the nodes that are part of the circuit
     *
     * @param dataToList - The data in list format
     * @return Map<Position, CircuitNode> - The only nodes that are part of the circuit
     */
    private Map<Position, CircuitNode> createCircuitNodes(List<String> dataToList) {
        Map<Position, CircuitNode> circuitNodes = new HashMap<>();
        for (int y = CircuitSetup.MAX_NODES_Y; y >= 1; y--) {
            for (int x = 0; x < CircuitSetup.MAX_NODES_X; x++) {
                char character = dataToList.get(CircuitSetup.MAX_NODES_Y - y + 1).charAt(x);
                CircuitNode circuitNode = new CircuitNode(new Position(x, y), CircuitNodeUtils.getNodeTypeFromCharacter(character));
                if (circuitNode.getType() != CircuitNodeState.nonTrackNode)
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
    private Line createStartLine(Map<Position, CircuitNode> circuitNodes) {
        Line startLine = new Line();
        for (CircuitNode circuitNode : circuitNodes.values())
            if (circuitNode.getType() == CircuitNodeState.startNode)
                startLine.addNodeToLine(circuitNode);
        return startLine;
    }


    /**
     * Methods that creates the end line based on node type
     *
     * @param circuitNodes - The nodes that are part of the circuit
     * @return Line The ending line
     */
    private Line createEndLine(Map<Position, CircuitNode> circuitNodes) {
        Line endLine = new Line();
        for (CircuitNode circuitNode : circuitNodes.values())
            if (circuitNode.getType() == CircuitNodeState.endNode)
                endLine.addNodeToLine(circuitNode);

        return endLine;
    }
}
