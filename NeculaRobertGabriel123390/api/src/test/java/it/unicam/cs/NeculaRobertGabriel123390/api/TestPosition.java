package it.unicam.cs.NeculaRobertGabriel123390.api;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class TestPosition {

    @Test
    public void testPositionGetterSetter(){
        Position position = new Position(10,10);
        assertEquals(10, position.getX());
        assertEquals(10, position.getY());

        position.setX(5);
        position.setY(5);

        assertEquals(5, position.getX());
        assertEquals(5, position.getY());
    }


    @Test
    public void shouldPositionThrowException(){

        assertThrows(NullPointerException.class, () -> PositionUtils.validateCircuitNodePosition(null));


        assertThrows(NullPointerException.class, () -> PositionUtils.validatePosition(null));


        Position position1 = new Position(-1, -1);

        assertThrows(IllegalArgumentException.class, () -> PositionUtils.validateCircuitNodePosition(position1));


        Position position2 = new Position(1, -1);
        assertThrows(IllegalArgumentException.class, () -> PositionUtils.validateCircuitNodePosition(position2));

    }


    @Test
    public void testAddingPosition(){
        Position position = new Position(10,10);
        Position position1 = new Position(10,10);

        assertEquals(new Position(20,20), PositionUtils.addPositions(position, position1));

        Position position2 = new Position(-10,-10);
        Position position3 = new Position(-10,10);

        assertEquals(new Position(-20,0), PositionUtils.addPositions(position2, position3));
    }


    @Test
    public void testScalePositionToDraw(){
        Position position = new Position(10,10);

        assertEquals(new Position(10 * CircuitSetup.DIM_RECT,10* CircuitSetup.DIM_RECT), PositionUtils.scalePositionToDraw(position));
    }


    @Test
    public void testNodeAdjacency(){
        Position position = new Position(0,1);
        Position position1 = new Position(0,2);
        Position position2 = new Position(0,3);

        List<Position> list = new ArrayList<>();
        list.add(position);
        list.add(position1);
        list.add(position2);

        assertDoesNotThrow( () -> PositionUtils.checkNodesAdjacency(list));


        position.setX(2);

        assertThrows(IllegalArgumentException.class, () -> PositionUtils.checkNodesAdjacency(list));

        list.clear();
        list.add(position);

        assertDoesNotThrow( () -> PositionUtils.checkNodesAdjacency(list));
    }


    @Test
    public void shouldNodeAdjacencyThrowException(){

        assertThrows(NullPointerException.class, () -> PositionUtils.checkNodesAdjacency(null));

        List<Position> list1 = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> PositionUtils.checkNodesAdjacency(list1));

        Position position = new Position(10,10);
        list1.add(position);
        assertDoesNotThrow(() -> PositionUtils.checkNodesAdjacency(list1));
    }


}
