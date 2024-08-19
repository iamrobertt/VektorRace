package it.unicam.cs.NeculaRobertGabriel123390.api;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.BresenhamLineAlgorithm;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testable
public class TestBresenhamLineAlgorithm {


    @Test
    public void testBresenhamLineAlgorithm1() {
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(0,4);

        List<Position> positionsBetween = new ArrayList<Position>();
        positionsBetween.add(pos1);
        positionsBetween.add(new Position(0,1));
        positionsBetween.add(new Position(0,2));
        positionsBetween.add(new Position(0,3));
        positionsBetween.add(pos2);

        assertEquals(positionsBetween, BresenhamLineAlgorithm.getPositionsBetween(pos1, pos2));

    }

    @Test
    public void testBresenhamLineAlgorithm2(){
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(1,3);

        List<Position> positionsBetween = new ArrayList<Position>();

        positionsBetween.add(pos1);
        positionsBetween.add(new Position(0,1));
        positionsBetween.add(new Position(1,2));
        positionsBetween.add(pos2);
        assertEquals(positionsBetween, BresenhamLineAlgorithm.getPositionsBetween(pos1, pos2));
    }

}
