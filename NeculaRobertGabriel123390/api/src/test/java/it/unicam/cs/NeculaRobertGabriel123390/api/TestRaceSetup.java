package it.unicam.cs.NeculaRobertGabriel123390.api;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParser;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParserFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.collision.DoNotAllowCollisionHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.crash.LeaveRaceCrashHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.RaceHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.win.WinOnCrossEndLineHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.RaceSetup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class TestRaceSetup {

    private final ParsedData<?> parsedData;


    TestRaceSetup() {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("ovalCircuit.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        this.parsedData = fileParser.parseFile(file);
    }


    @BeforeAll
    public static void setupJavaFX() {
        InitializerLoadingLogger.initialize();
    }


    @Test
    public void testRaceSetup(){
        List<RaceHandler> handlers = new ArrayList<>();
        handlers.add(new WinOnCrossEndLineHandler());
        handlers.add(new LeaveRaceCrashHandler());
        handlers.add(new DoNotAllowCollisionHandler());
        RaceSetup raceSetup = new RaceSetup(this.parsedData, handlers);
        assertDoesNotThrow(raceSetup::setup);
    }


    @Test
    public void shouldRaceSetupThrowException() {

        List<RaceHandler> handlers = new ArrayList<>();
        handlers.add(new WinOnCrossEndLineHandler());
        handlers.add(new DoNotAllowCollisionHandler());
        handlers.add(new LeaveRaceCrashHandler());


        assertThrows(NullPointerException.class, () -> new RaceSetup(null, null));
        assertThrows(NullPointerException.class, () -> new RaceSetup(null, handlers));
        assertThrows(NullPointerException.class, () -> new RaceSetup(this.parsedData, null));
        assertThrows(IllegalArgumentException.class, () -> new RaceSetup(this.parsedData, handlers));
        assertThrows(IllegalArgumentException.class, () -> new RaceSetup(this.parsedData, new ArrayList<>()));

        handlers.set(1, new LeaveRaceCrashHandler());
        handlers.set(2, new DoNotAllowCollisionHandler());
        assertDoesNotThrow(() -> new RaceSetup(this.parsedData, handlers));
    }

}
