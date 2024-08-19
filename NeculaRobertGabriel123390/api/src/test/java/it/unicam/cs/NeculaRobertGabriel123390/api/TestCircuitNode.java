package it.unicam.cs.NeculaRobertGabriel123390.api;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitNode;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitNodeState;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class TestCircuitNode {


    @Test
    public void testCircuitNodeGetterSetter(){
        CircuitNode node = new CircuitNode(new Position(10,10), CircuitNodeState.startNode);

        assertEquals(CircuitNodeState.startNode, node.getState());
        assertEquals(new Position(10,10), node.getPosition());

        node.setState(CircuitNodeState.endNode);
        assertEquals(CircuitNodeState.endNode, node.getState());

        CircuitNode node2 = new CircuitNode(new Position(10,10), CircuitNodeState.endNode);

        assertEquals(node, node2);
    }


    @Test
    public void shouldCircuitNodeThrowException(){

        assertThrows(IllegalArgumentException.class, () -> new CircuitNode(new Position(-1, 10), CircuitNodeState.trackNode));

        assertThrows(NullPointerException.class, () -> new CircuitNode(null, CircuitNodeState.trackNode));

        assertThrows(NullPointerException.class, () -> new CircuitNode(new Position(1,1), null));

        assertThrows(NullPointerException.class, () -> new CircuitNode(null, null));

    }
}
