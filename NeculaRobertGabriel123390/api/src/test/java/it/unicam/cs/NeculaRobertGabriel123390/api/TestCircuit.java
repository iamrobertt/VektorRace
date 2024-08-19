package it.unicam.cs.NeculaRobertGabriel123390.api;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class TestCircuit {


    @Test
    public void testGetterSetter(){
        CircuitNode node1 = new CircuitNode(new Position(1,1), CircuitNodeState.trackNode);
        CircuitNode node2 = new CircuitNode(new Position(2,1), CircuitNodeState.trackNode);
        CircuitNode node3 = new CircuitNode(new Position(3,1), CircuitNodeState.trackNode);
        CircuitNode node4 = new CircuitNode(new Position(4,1), CircuitNodeState.trackNode);
        CircuitNode node5 = new CircuitNode(new Position(5,1), CircuitNodeState.trackNode);
        CircuitNode node6 = new CircuitNode(new Position(6,1), CircuitNodeState.trackNode);
        CircuitNode node7 = new CircuitNode(new Position(7,1), CircuitNodeState.trackNode);
        CircuitNode node8 = new CircuitNode(new Position(8,1), CircuitNodeState.trackNode);
        CircuitNode node9 = new CircuitNode(new Position(9,1), CircuitNodeState.trackNode);
        CircuitNode node10 = new CircuitNode(new Position(10,1), CircuitNodeState.trackNode);

        Map<Position, CircuitNode> circuitNodes = new HashMap<>();
        circuitNodes.put(node1.getPosition(), node1);
        circuitNodes.put(node2.getPosition(), node2);
        circuitNodes.put(node3.getPosition(), node3);
        circuitNodes.put(node4.getPosition(), node4);
        circuitNodes.put(node5.getPosition(), node5);
        circuitNodes.put(node6.getPosition(), node6);
        circuitNodes.put(node7.getPosition(), node7);
        circuitNodes.put(node8.getPosition(), node8);
        circuitNodes.put(node9.getPosition(), node9);
        circuitNodes.put(node10.getPosition(), node10);

        CircuitLine startLine = new StartEndCircuitLine();
        startLine.addNode(node1.getPosition());
        startLine.addNode(node2.getPosition());
        startLine.addNode(node3.getPosition());


        CircuitLine endLine = new StartEndCircuitLine();
        endLine.addNode(node4.getPosition());
        endLine.addNode(node5.getPosition());
        endLine.addNode(node6.getPosition());
        Circuit circuit = new Circuit(circuitNodes, startLine, endLine);

        assertEquals(circuit.getCircuitMap(), circuitNodes);
        assertEquals(circuit.getStartLine(), startLine);
        assertEquals(circuit.getEndLine(), endLine);

        List<Position> node2Neighbours = new ArrayList<>();
        node2Neighbours.add(node1.getPosition());
        node2Neighbours.add(node3.getPosition());
        assertEquals(circuit.getNeighbours(node2.getPosition()), node2Neighbours);
    }


    @Test
    public void testNeighbouring(){

        CircuitNode node1 = new CircuitNode(new Position(5,5), CircuitNodeState.trackNode);
        CircuitNode node2 = new CircuitNode(new Position(5,6), CircuitNodeState.trackNode);
        CircuitNode node3 = new CircuitNode(new Position(5,4), CircuitNodeState.trackNode);
        CircuitNode node4 = new CircuitNode(new Position(6,5), CircuitNodeState.trackNode);
        CircuitNode node5 = new CircuitNode(new Position(6,6), CircuitNodeState.trackNode);
        CircuitNode node6 = new CircuitNode(new Position(6,4), CircuitNodeState.trackNode);
        CircuitNode node7 = new CircuitNode(new Position(4,5), CircuitNodeState.trackNode);
        CircuitNode node8 = new CircuitNode(new Position(4,6), CircuitNodeState.trackNode);
        CircuitNode node9 = new CircuitNode(new Position(4,4), CircuitNodeState.trackNode);

        CircuitNode node10 = new CircuitNode(new Position(10,11), CircuitNodeState.trackNode);
        CircuitNode node11 = new CircuitNode(new Position(10,12), CircuitNodeState.trackNode);
        CircuitNode node12 = new CircuitNode(new Position(10,13), CircuitNodeState.trackNode);

        Map<Position, CircuitNode> circuitNodes = new HashMap<>();
        circuitNodes.put(node1.getPosition(), node1);
        circuitNodes.put(node2.getPosition(), node2);
        circuitNodes.put(node3.getPosition(), node3);
        circuitNodes.put(node4.getPosition(), node4);
        circuitNodes.put(node5.getPosition(), node5);
        circuitNodes.put(node6.getPosition(), node6);
        circuitNodes.put(node7.getPosition(), node7);
        circuitNodes.put(node8.getPosition(), node8);
        circuitNodes.put(node9.getPosition(), node9);
        circuitNodes.put(node10.getPosition(), node10);
        circuitNodes.put(node11.getPosition(), node11);
        circuitNodes.put(node12.getPosition(), node12);


        CircuitLine startLine = new StartEndCircuitLine();

        startLine.addNode(node1.getPosition());
        startLine.addNode(node2.getPosition());
        startLine.addNode(node3.getPosition());


        CircuitLine endLine = new StartEndCircuitLine();
        endLine.addNode(node10.getPosition());
        endLine.addNode(node11.getPosition());
        endLine.addNode(node12.getPosition());

        Circuit circuit = new Circuit(circuitNodes, startLine, endLine);

        List<Position> node1Neighbours = new ArrayList<>();
        node1Neighbours.add(node2.getPosition());
        node1Neighbours.add(node3.getPosition());
        node1Neighbours.add(node4.getPosition());
        node1Neighbours.add(node5.getPosition());
        node1Neighbours.add(node6.getPosition());
        node1Neighbours.add(node7.getPosition());
        node1Neighbours.add(node8.getPosition());
        node1Neighbours.add(node9.getPosition());


        List<Position> circuitNode1Neighbours = circuit.getNeighbours(node1.getPosition());

        for(Position neighPosition : circuitNode1Neighbours) {
            assertTrue(node1Neighbours.contains(neighPosition));
            node1Neighbours.remove(neighPosition);
        }

        assertTrue(node1Neighbours.isEmpty());



    }


    @Test
    public void testExternNodes(){

        CircuitNode node1 = new CircuitNode(new Position(5,5), CircuitNodeState.trackNode);
        CircuitNode node2 = new CircuitNode(new Position(5,6), CircuitNodeState.trackNode);
        CircuitNode node3 = new CircuitNode(new Position(5,4), CircuitNodeState.trackNode);
        CircuitNode node4 = new CircuitNode(new Position(6,5), CircuitNodeState.trackNode);
        CircuitNode node5 = new CircuitNode(new Position(6,6), CircuitNodeState.trackNode);
        CircuitNode node6 = new CircuitNode(new Position(6,4), CircuitNodeState.trackNode);
        CircuitNode node7 = new CircuitNode(new Position(4,5), CircuitNodeState.trackNode);
        CircuitNode node8 = new CircuitNode(new Position(4,6), CircuitNodeState.trackNode);
        CircuitNode node9 = new CircuitNode(new Position(4,4), CircuitNodeState.trackNode);

        CircuitNode node10 = new CircuitNode(new Position(10,11), CircuitNodeState.trackNode);
        CircuitNode node11 = new CircuitNode(new Position(10,12), CircuitNodeState.trackNode);
        CircuitNode node12 = new CircuitNode(new Position(10,13), CircuitNodeState.trackNode);

        Map<Position, CircuitNode> circuitNodes = new HashMap<>();
        circuitNodes.put(node1.getPosition(), node1);
        circuitNodes.put(node2.getPosition(), node2);
        circuitNodes.put(node3.getPosition(), node3);
        circuitNodes.put(node4.getPosition(), node4);
        circuitNodes.put(node5.getPosition(), node5);
        circuitNodes.put(node6.getPosition(), node6);
        circuitNodes.put(node7.getPosition(), node7);
        circuitNodes.put(node8.getPosition(), node8);
        circuitNodes.put(node9.getPosition(), node9);
        circuitNodes.put(node10.getPosition(), node10);
        circuitNodes.put(node11.getPosition(), node11);
        circuitNodes.put(node12.getPosition(), node12);


        CircuitLine startLine = new StartEndCircuitLine();

        startLine.addNode(node1.getPosition());
        startLine.addNode(node2.getPosition());
        startLine.addNode(node3.getPosition());


        CircuitLine endLine = new StartEndCircuitLine();
        endLine.addNode(node10.getPosition());
        endLine.addNode(node11.getPosition());
        endLine.addNode(node12.getPosition());

        Circuit circuit = new Circuit(circuitNodes, startLine, endLine);

        assertFalse(circuit.isCircuitNodeExtern(node1.getPosition()));

        circuitNodes.remove(node1.getPosition());

        for(Position position : circuitNodes.keySet())
            assertTrue(circuit.isCircuitNodeExtern(position));


    }


    @Test
    public void shouldCircuitThrowException(){

        CircuitNode node1 = new CircuitNode(new Position(5,5), CircuitNodeState.trackNode);
        CircuitNode node2 = new CircuitNode(new Position(5,6), CircuitNodeState.trackNode);
        CircuitNode node3 = new CircuitNode(new Position(5,4), CircuitNodeState.trackNode);
        CircuitNode node4 = new CircuitNode(new Position(6,5), CircuitNodeState.trackNode);
        CircuitNode node5 = new CircuitNode(new Position(6,6), CircuitNodeState.trackNode);
        CircuitNode node6 = new CircuitNode(new Position(6,4), CircuitNodeState.trackNode);
        CircuitNode node7 = new CircuitNode(new Position(4,5), CircuitNodeState.trackNode);
        CircuitNode node8 = new CircuitNode(new Position(4,6), CircuitNodeState.trackNode);
        CircuitNode node9 = new CircuitNode(new Position(4,4), CircuitNodeState.trackNode);

        Map<Position, CircuitNode> circuitNodes = new HashMap<>();
        circuitNodes.put(node1.getPosition(), node1);
        circuitNodes.put(node2.getPosition(), node2);
        circuitNodes.put(node3.getPosition(), node3);
        circuitNodes.put(node4.getPosition(), node4);
        circuitNodes.put(node5.getPosition(), node5);
        circuitNodes.put(node6.getPosition(), node6);
        circuitNodes.put(node7.getPosition(), node7);
        circuitNodes.put(node8.getPosition(), node8);
        circuitNodes.put(node9.getPosition(), node9);


        CircuitLine startLine = new StartEndCircuitLine();

        startLine.addNode(node1.getPosition());
        startLine.addNode(node2.getPosition());
        startLine.addNode(node3.getPosition());


        CircuitLine endLine = new StartEndCircuitLine();
        endLine.addNode(node4.getPosition());
        endLine.addNode(node5.getPosition());
        endLine.addNode(node6.getPosition());


        assertThrows(NullPointerException.class, () -> new Circuit(null, new StartEndCircuitLine(), new StartEndCircuitLine()));
        assertThrows(NullPointerException.class, () -> new Circuit(circuitNodes, null, null));
        assertThrows(NullPointerException.class, () -> new Circuit(circuitNodes, startLine, null));
        assertThrows(NullPointerException.class, () -> new Circuit(circuitNodes, null, endLine));

        assertThrows(IllegalArgumentException.class, () -> new Circuit(new HashMap<>(), startLine, endLine));
        assertThrows(IllegalArgumentException.class, () -> new Circuit(new HashMap<>(), new StartEndCircuitLine(), endLine));
        assertThrows(IllegalArgumentException.class, () -> new Circuit(new HashMap<>(), startLine, new StartEndCircuitLine()));

        assertThrows(IllegalArgumentException.class, () -> new Circuit(circuitNodes, new DrawCircuitLine(), endLine));
        assertThrows(IllegalArgumentException.class, () -> new Circuit(circuitNodes, startLine, new DrawCircuitLine()));

        CircuitLine line = new DrawCircuitLine();
        line.addNode(node1.getPosition());
        line.addNode(node2.getPosition());

        assertThrows(IllegalArgumentException.class, () -> new Circuit(circuitNodes, line, endLine));
        assertThrows(IllegalArgumentException.class, () -> new Circuit(circuitNodes, startLine, line));

    }

}
