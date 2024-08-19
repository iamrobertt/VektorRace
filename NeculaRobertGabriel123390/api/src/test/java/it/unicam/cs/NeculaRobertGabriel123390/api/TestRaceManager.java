package it.unicam.cs.NeculaRobertGabriel123390.api;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResult;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.MoveResultType;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.*;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.collision.AllowCollisionHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.collision.DoNotAllowCollisionHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.crash.ContinueWithPenaltyCrashHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.crash.LeaveRaceCrashHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.win.WinOnCrossEndLineHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.RaceManager;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.BotPlayer;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.Race;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.RaceSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@Testable
public class TestRaceManager {


    private final ParsedData<?> parsedData;


    @BeforeAll
    public static void setupJavaFX() {
        InitializerRaceLogger.initialize();
    }


    TestRaceManager() {

        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("ovalCircuit.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        this.parsedData = fileParser.parseFile(file);

        ParsedDataValidator validator = new TXTParsedDataValidator();
        validator.validateData(this.parsedData);
    }


    @Test
    public void testRaceManagerDefaultHandlers(){

        List<RaceHandler> handlers = new ArrayList<>();
        handlers.add(new WinOnCrossEndLineHandler());
        handlers.add(new LeaveRaceCrashHandler());
        handlers.add(new DoNotAllowCollisionHandler());

        Race race = new RaceSetup(this.parsedData, handlers).setup();

        RaceManager raceManager = new RaceManager(race);
        List<Player> players = race.players();

        Position prevFirstPlayerPosition = race.players().getFirst().getPosition();

        assertEquals(raceManager.getCurrentPlayer(), players.getFirst());
        assertNotNull(raceManager.getCurrentPlayer().getPosition());

        MoveResult moveResult = raceManager.onPlayerMove(new Position(0,1));

        assertEquals(MoveResultType.SUCCESS, moveResult.moveType());
        assertEquals(players.getFirst(), moveResult.player());
        assertEquals(PositionUtils.addPositions(prevFirstPlayerPosition, new Position(0,1)), raceManager.getPlayersManager().getPlayers().get(1).getPosition());
        assertEquals(players.get(1), raceManager.getCurrentPlayer());

        //collision not allowed simulation
        MoveResult moveResult2 = raceManager.onPlayerMove(new Position(1,1));
        assertEquals(MoveResultType.COLLISION_NOT_ALLOWED, moveResult2.moveType());
        //current player has not changed
        assertEquals(players.get(1), raceManager.getCurrentPlayer());

        assertEquals(players.get(1).getPosition(), raceManager.getCurrentPlayer().getPosition());


        //crash with leave race simulation
        MoveResult moveResult3 = raceManager.onPlayerMove(new Position(-1,-1));
        assertEquals(MoveResultType.CRASH_LEAVE_RACE, moveResult3.moveType());
        assertEquals(players.getFirst(), raceManager.getCurrentPlayer());
        assertEquals(1, raceManager.getPlayersManager().playersCount());
        assertFalse(raceManager.wasLastPlayer());

    }


    @Test
    public void testRaceManagerWithCollisionAllowed(){
        List<RaceHandler> handlers = new ArrayList<>();

        handlers.add(new WinOnCrossEndLineHandler());
        handlers.add(new LeaveRaceCrashHandler());
        handlers.add(new AllowCollisionHandler());

        Race race = new RaceSetup(this.parsedData, handlers).setup();
        RaceManager raceManager = new RaceManager(race);
        List<Player> players = race.players();

        Position prevFirstPlayerPosition = race.players().getFirst().getPosition();

        assertEquals(raceManager.getCurrentPlayer(), players.getFirst());
        assertNotNull(raceManager.getCurrentPlayer().getPosition());

        MoveResult moveResult = raceManager.onPlayerMove(new Position(0,1));

        assertEquals(MoveResultType.SUCCESS, moveResult.moveType());
        assertEquals(players.getFirst(), moveResult.player());
        assertEquals(PositionUtils.addPositions(prevFirstPlayerPosition, new Position(0,1)), raceManager.getPlayersManager().getPlayers().get(1).getPosition());
        assertEquals(players.get(1), raceManager.getCurrentPlayer());


        //collision allowed simulation
        MoveResult moveResult2 = raceManager.onPlayerMove(new Position(1,1));
        assertEquals(MoveResultType.COLLISION_ALLOWED, moveResult2.moveType());
        //current player has changed
        assertEquals(players.getFirst(), raceManager.getCurrentPlayer());
        assertEquals(players.get(1).getPosition(), raceManager.getPlayersManager().getPlayers().get(1).getPosition());


        //crash simulation
        MoveResult moveResult3 = raceManager.onPlayerMove(new Position(10,4));
        assertEquals(MoveResultType.CRASH_LEAVE_RACE, moveResult3.moveType());
        assertEquals(players.get(1), raceManager.getCurrentPlayer());
        assertEquals(1, raceManager.getPlayersManager().playersCount());
        assertFalse(raceManager.wasLastPlayer());


        //another crash simulator
        MoveResult moveResult4 = raceManager.onPlayerMove(new Position(10,4));
        assertEquals(MoveResultType.CRASH_LEAVE_RACE, moveResult4.moveType());

        assertThrows(IllegalArgumentException.class, raceManager::getCurrentPlayer);
        assertTrue(raceManager.wasLastPlayer());
    }


    @Test
    public void testRaceManagerWithCrashMovePenalty(){

        List<RaceHandler> handlers = new ArrayList<>();

        handlers.add(new WinOnCrossEndLineHandler());
        handlers.add(new ContinueWithPenaltyCrashHandler());
        handlers.add(new AllowCollisionHandler());

        Race race = new RaceSetup(this.parsedData, handlers).setup();
        RaceManager raceManager = new RaceManager(race);
        List<Player> players = race.players();

        Position prevFirstPlayerPosition = race.players().getFirst().getPosition();

        //success move
        MoveResult moveResult = raceManager.onPlayerMove(new Position(-1,-1));
        assertEquals(MoveResultType.SUCCESS, moveResult.moveType());
        assertEquals(players.getFirst(), moveResult.player());
        assertEquals(PositionUtils.addPositions(prevFirstPlayerPosition, new Position(-1,-1)), raceManager.getPlayersManager().getPlayers().get(1).getPosition());
        assertEquals(players.get(1), raceManager.getCurrentPlayer());

        //crash simulation
        Position previousPosition2 = players.get(1).getPosition();
        MoveResult moveResult2 = raceManager.onPlayerMove(new Position(-1,-1));
        assertEquals(MoveResultType.CRASH_CONTINUE_WITH_PENALTY, moveResult2.moveType());
        assertEquals(players.getFirst(), raceManager.getCurrentPlayer());
        assertEquals(2, raceManager.getPlayersManager().playersCount());
        assertFalse(raceManager.wasLastPlayer());
        assertEquals(previousPosition2, raceManager.getPlayersManager().getPlayers().get(1).getPosition());


        //collision allowed simulation
        Position previousPosition3 = players.get(1).getPosition();
        MoveResult moveResult3 = raceManager.onPlayerMove(new Position(0,1));
        assertEquals(MoveResultType.COLLISION_ALLOWED, moveResult3.moveType());
        assertEquals(players.get(1), raceManager.getCurrentPlayer());
        assertEquals(previousPosition3, raceManager.getPlayersManager().getPlayers().getFirst().getPosition());

    }


    @Test
    public void testRaceManagerWin(){
        List<RaceHandler> handlers = new ArrayList<>();

        handlers.add(new WinOnCrossEndLineHandler());
        handlers.add(new ContinueWithPenaltyCrashHandler());
        handlers.add(new AllowCollisionHandler());

        Race race = new RaceSetup(this.parsedData, handlers).setup();
        RaceManager raceManager = new RaceManager(race);


        //win simulation
        Position prevFirstPlayerPosition = race.players().getFirst().getPosition();
        MoveResult moveResult = raceManager.onPlayerMove(new Position(2,15));
        assertEquals(MoveResultType.WIN, moveResult.moveType());
        assertEquals(PositionUtils.addPositions(prevFirstPlayerPosition, new Position(2,15)), raceManager.getPlayersManager().getPlayers().getFirst().getPosition());
        assertTrue(raceManager.getCircuitManager().hasWon(prevFirstPlayerPosition, PositionUtils.addPositions(prevFirstPlayerPosition, new Position(2,15))));
    }


    @Test
    public void shouldRaceManagerThrowException(){
        List<RaceHandler> handlers = new ArrayList<>();

        handlers.add(new WinOnCrossEndLineHandler());
        handlers.add(new LeaveRaceCrashHandler());
        handlers.add(new AllowCollisionHandler());

        Race race = new RaceSetup(this.parsedData, handlers).setup();

        List<Player> invalidPlayers = new ArrayList<>();
        invalidPlayers.add(new BotPlayer("bot1", Color.RED));
        Race invalidRace = new Race(race.circuit(), invalidPlayers, handlers);
        assertThrows(IllegalArgumentException.class, ()-> new RaceManager(invalidRace));
    }

}
