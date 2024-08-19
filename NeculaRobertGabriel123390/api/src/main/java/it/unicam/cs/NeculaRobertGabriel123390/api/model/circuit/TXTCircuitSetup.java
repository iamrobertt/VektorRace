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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.FileFormatError;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedDataValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedDataValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that sets up a circuit from a TXT file.
 * This class interprets the data provided by {@link TXTParsedData}
 * and creates a {@link Circuit} representation of it.
 */
public final class TXTCircuitSetup implements CircuitSetup {


    /**
     * The last row in the file.
     */
    public static final int LAST_ROW = 38;


    /**
     * The row where the player amount of players playing should be defined.
     */
    public static final int PLAYERS_COUNT_ROW = 37;


    /**
     * The row where the name and color of each human player should be defined.
     */
    public static final int HUMAN_PLAYERS_DATA_ROW = 38;


    /**
     * Sets up a {@link Circuit} using the provided parsed file data.
     * This method performs the following steps:
     * <ol>
     *     <li>Validates the file data.</li>
     *     <li>Extracts the data into a list.</li>
     *     <li>Creates the circuit map from the extracted data.</li>
     *     <li>Identifies the start and end lines of the circuit.</li>
     *     <li>Validates the positioning of the start and end lines.</li>
     * </ol>
     *
     * @param fileData the parsed file data, expected to be of type {@link TXTParsedData}.
     * @return a {@link Circuit} object representing the setup circuit.
     * @throws NullPointerException if the provided file data is null.
     * @throws IllegalArgumentException if the file data is not of the expected type.
     */
    @Override
    public Circuit setup(ParsedData<?> fileData) {

        validateFileData(fileData);
        List<String> dataToList = extractData(fileData);
        Map<Position, CircuitNode> circuitMap = createCircuitMap(dataToList);

        CircuitLine startLine = createStartLine(circuitMap);
        CircuitLine endLine = createEndLine(circuitMap);

        return new Circuit(circuitMap, startLine, endLine);
    }


    /**
     * Validates that the file data is not null and is of the expected type {@link TXTParsedData}.
     *
     * @param fileData the parsed file data to validate.
     * @throws NullPointerException if the file data is null.
     * @throws IllegalArgumentException if the file data is not an instance of {@link TXTParsedData}.
     */
    private void validateFileData(ParsedData<?> fileData) {
        ParsedDataValidator fileValidator = new TXTParsedDataValidator();
        fileValidator.validateData(fileData);
    }


    /**
     * Validates that the list of data is not null and not empty.
     *
     * @param dataToList the list of data to validate.
     * @throws NullPointerException if the data list is null.
     * @throws IllegalArgumentException if the data list is empty.
     */
    private static void validateListOfData(List<String> dataToList) {
        if(dataToList == null)
            throw new NullPointerException("dataToList is null");
        if(dataToList.isEmpty())
            throw new IllegalArgumentException("dataToList is empty");
    }


    /**
     * Converts the parsed file data into a list of strings.
     *
     * @param fileData the parsed file data to convert.
     * @return a list of strings representing the file data.
     */
    private List<String> extractData(ParsedData<?> fileData) {
        return new ArrayList<>(((TXTParsedData) fileData).getData());
    }


    /**
     * Creates a map of positions and corresponding circuit nodes from the provided data list.
     * Only nodes that are part of the circuit are included in the map.
     *
     * @param dataToList the data list to process.
     * @return a map where the keys are positions and the values are circuit nodes.
     * @throws NullPointerException if the data list is null.
     * @throws IllegalArgumentException if the data list is empty.
     */
    private Map<Position, CircuitNode> createCircuitMap(List<String> dataToList) {
        validateListOfData(dataToList);
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
     * Creates the start line for the circuit based on the node type.
     *
     * @param circuitNodes the nodes that are part of the circuit.
     * @return a {@link CircuitLine} representing the start line.
     */
    private CircuitLine createStartLine(Map<Position, CircuitNode> circuitNodes) {
        CircuitLine startLine = new StartEndCircuitLine();
        for (CircuitNode circuitNode : circuitNodes.values())
            if (circuitNode.getState() == CircuitNodeState.startNode)
                startLine.addNode(circuitNode.getPosition());
        return startLine;
    }


    /**
     * Creates the end line for the circuit based on the node type.
     *
     * @param circuitNodes the nodes that are part of the circuit.
     * @return a {@link CircuitLine} representing the end line.
     */
    private CircuitLine createEndLine(Map<Position, CircuitNode> circuitNodes) {
        CircuitLine endLine = new StartEndCircuitLine();
        for (CircuitNode circuitNode : circuitNodes.values())
            if (circuitNode.getState() == CircuitNodeState.endNode)
                endLine.addNode(circuitNode.getPosition());

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
            default -> throw new FileFormatError("Unexpected character: " + c);
        };
    }
}
