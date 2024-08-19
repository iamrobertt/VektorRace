package it.unicam.cs.NeculaRobertGabriel123390.api;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.Circuit;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.collision.DoNotAllowCollisionHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.crash.LeaveRaceCrashHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.win.WinOnCrossEndLineHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.CircuitManager;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.Race;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.RaceSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class TestCircuitManager {


    private final Race race;

    TestCircuitManager() {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("ovalCircuit.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        ParsedData<?> parsedData = fileParser.parseFile(file);

        ParsedDataValidator validator = new TXTParsedDataValidator();
        validator.validateData(parsedData);

        List<RaceHandler> handlers = new ArrayList<>();
        handlers.add(new WinOnCrossEndLineHandler());
        handlers.add(new LeaveRaceCrashHandler());
        handlers.add(new DoNotAllowCollisionHandler());

        this.race = new RaceSetup(parsedData, handlers).setup();
    }


    @Test
    public void testCircuitManager() {

        Circuit circuit = race.circuit();

        Position pos1 = new Position(5,17);
        CircuitManager circuitManager = new CircuitManager(circuit);
        circuitManager.setOccupied(pos1);
        assertTrue(circuitManager.isOccupied(pos1));
        assertTrue(circuitManager.isColliding(pos1));
        assertFalse(circuitManager.isCrashing(pos1,new Position(7,18)));

        Position movePos = new Position(1,4);
        circuitManager.updateCircuitAfterMove(pos1, movePos);
        assertTrue(circuitManager.isOccupied(PositionUtils.addPositions(pos1, movePos)));
        assertFalse(circuitManager.isOccupied(pos1));
        assertFalse(circuitManager.hasWon(pos1, PositionUtils.addPositions(pos1, movePos)));

        assertTrue(circuitManager.hasWon(new Position(10,10), this.race.circuit().getEndLine().getNode(1)));

        Position cornerPosition = new Position(5,13);
        assertEquals(new Position(5,14), circuitManager.getFreeNeighbour(cornerPosition));
        assertEquals(new Position(5,15), circuitManager.getFreeNeighbour(new Position(5,14)));
    }


    @Test
    public void shouldCircuitManagerThrowNullPointerException(){

        CircuitManager circuitManager = new CircuitManager(this.race.circuit());

        assertThrows(NullPointerException.class, () -> new CircuitManager(null));
        assertThrows(NullPointerException.class, () -> circuitManager.isOccupied(null));
        assertThrows(NullPointerException.class, () -> circuitManager.isCrashing(null, null));
        assertThrows(NullPointerException.class, () -> circuitManager.hasWon(null, null));
        assertThrows(NullPointerException.class, () -> circuitManager.updateCircuitAfterMove(null, null));
        assertThrows(NullPointerException.class, () -> circuitManager.getFreeNeighbour(null));
    }


    @Test
    public void shouldCircuitManagerThrowIllegalArgumentException(){
        CircuitManager circuitManager = new CircuitManager(this.race.circuit());
        assertThrows(IllegalArgumentException.class, () -> circuitManager.isOccupied(new Position(0,0)));
        assertThrows(IllegalArgumentException.class, () -> circuitManager.isColliding(new Position(-1,-1)));
        assertThrows(IllegalArgumentException.class, () -> circuitManager.hasWon(new Position(10,10), new Position(1,-1)));
        assertThrows(IllegalArgumentException.class, () -> circuitManager.setOccupied(new Position(-1,0)));

    }


    @Test
    public void testPositionOutOfCircuitThrowsException(){
        CircuitManager circuitManager = new CircuitManager(this.race.circuit());

        Position outOfCircuitPosition = new Position(0,0);
        assertThrows(IllegalArgumentException.class, () -> circuitManager.isOccupied(outOfCircuitPosition));
        assertThrows(IllegalArgumentException.class, () -> circuitManager.setOccupied(outOfCircuitPosition));
    }

}
