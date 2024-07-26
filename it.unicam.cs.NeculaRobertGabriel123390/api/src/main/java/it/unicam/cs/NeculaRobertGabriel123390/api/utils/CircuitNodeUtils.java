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

package it.unicam.cs.NeculaRobertGabriel123390.api.utils;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitNode;
import java.util.List;


/**
 * Class that supply methods related to operation with nodes
 */
//todo crea interfaccia e rimodifica per tx
public class CircuitNodeUtils {


    /**
     * Method that checks if all nodes given are adjacent
     *
     * @param nodesToCheck - An arraylist with nodes to check
     * @return - True if all the nodes are adjacent
     */
    public static boolean areNodesAdjacent(List<CircuitNode> nodesToCheck) {

        if (nodesToCheck == null) throw new IllegalArgumentException("Nodes cannot be null");
        if (nodesToCheck.size() < 2)
            throw new IllegalArgumentException("Needed at least 2 nodes to check the adjacency");

        for (int i = 0; i < nodesToCheck.size() - 1; ++i) {
            CircuitNode currentNode = nodesToCheck.get(i);
            CircuitNode nextNode = nodesToCheck.get(i + 1);

            if(!areAdjacent(currentNode, nextNode)) return false;

        }

        return true;
    }


    /**
     * Method that checks if two given node are adjacent
     *
     * @param node1 - First node
     * @param node2 - Second node
     * @return True if the nodes are adjacent, false otherwise
     */
    private static boolean areAdjacent(CircuitNode node1, CircuitNode node2) {
        int deltaX = Math.abs(node1.getPosition().getX() - node2.getPosition().getX());
        int deltaY = Math.abs(node1.getPosition().getY() - node2.getPosition().getY());

        return (deltaX == 0 && deltaY == 1) || (deltaX == 1 && deltaY == 0);
    }

    public static boolean validateNode(CircuitNode node) {
        return false;
        //TODO
    }


    /**
     * Method that returns the nodetype of the specific character
     * # is not a part of the track
     *
     * @param c - The character from the file
     * @return CircuitNodeState - The type of the node
     * @ it's a part of the track
     * + is part of the start line
     * - is part of the endline
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


    /**
     * Method that supply a regex for characters that are accepted in the file
     * @return String the regex
     */
    public static String getRegexForNode(){
        return "[@*#+-]*";
    }

}
