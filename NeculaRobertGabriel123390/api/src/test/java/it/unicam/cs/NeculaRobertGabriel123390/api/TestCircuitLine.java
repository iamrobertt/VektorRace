package it.unicam.cs.NeculaRobertGabriel123390.api;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.CircuitLine;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.DrawCircuitLine;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.StartEndCircuitLine;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testable
public class TestCircuitLine {


    @Test
    public void testGetterSetter(){
        CircuitLine line =  new DrawCircuitLine();
        line.addNode(new Position(1,1));
        line.addNode(new Position(1,2));
        line.setColor(Color.BEIGE);
        assertEquals(line.getNode(0), new Position(1,1));
        assertEquals(line.getNode(1), new Position(1,2));
        assertEquals(line.getColor(), Color.BEIGE);

        line.setColor(Color.RED);
        assertEquals(line.getColor(), Color.RED);
    }


    @Test
    public void shouldDrawCircuitLineThrowException(){
        CircuitLine line =  new DrawCircuitLine();
        line.addNode(new Position(1,1));
        line.addNode(new Position(1,2));

        assertThrows(IllegalArgumentException.class, ()-> line.addNode(new Position(2,1)));
        assertThrows(NullPointerException.class, line::getColor);

        CircuitLine line1 = new DrawCircuitLine();
        line1.addNode(new Position(1,1));
        assertThrows(IllegalArgumentException.class, ()-> line1.addNode(new Position(1,1)));

        assertThrows(NullPointerException.class, ()-> line1.addNode(null));
        assertThrows(NullPointerException.class, line::getColor);

        CircuitLine line3 = new DrawCircuitLine();
        line3.addNode(new Position(1,1));
        assertThrows(IllegalStateException.class, line1::getPositions);
    }


    @Test
    public void shouldStartEndCircuitLineThrowException(){
        CircuitLine line =  new StartEndCircuitLine();
        line.addNode(new Position(1,1));
        line.addNode(new Position(1,2));
        line.addNode(new Position(1,3));
        assertThrows(IllegalArgumentException.class, ()-> line.addNode(new Position(1,1)));
        assertThrows(NullPointerException.class, ()-> line.addNode(null));
        assertThrows(NullPointerException.class, line::getColor);
        assertThrows(IllegalArgumentException.class, ()-> line.addNode(new Position(4,1)));

    }
}
