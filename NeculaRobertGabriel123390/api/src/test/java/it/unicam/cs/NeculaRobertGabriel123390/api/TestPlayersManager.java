package it.unicam.cs.NeculaRobertGabriel123390.api;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.manager.PlayersManager;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.HumanPlayer;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Testable
public class TestPlayersManager {


    private final List<Player> players;

    TestPlayersManager(){
        this.players = new ArrayList<>();
        this.players.add(new HumanPlayer("robert", Color.RED));
        this.players.getFirst().setPosition(new Position(0,10));
        this.players.add(new HumanPlayer("alice", Color.BLUE));
        this.players.get(1).setPosition(new Position(0,11));
        this.players.add(new HumanPlayer("bob", Color.GREEN));
        this.players.get(2).setPosition(new Position(0,12));
        this.players.add(new HumanPlayer("bot1", Color.YELLOW));
        this.players.get(3).setPosition(new Position(0,13));
    }


    @Test
    public void testPlayersManager() {

        PlayersManager playersManager = new PlayersManager(this.players);

        assertEquals(this.players, playersManager.getPlayers());
        assertEquals(this.players.getFirst(), playersManager.getCurrentPlayer());

        playersManager.updatePosition(this.players.get(1), new Position(20,12));
        assertEquals(this.players.get(1).getPosition(), new Position(20,12));

        playersManager.makeMove(new Position(1, 0));
        assertEquals(this.players.getFirst().getPosition(), new Position(1, 10));

        playersManager.advanceToNextPlayer();
        assertEquals(this.players.get(1), playersManager.getCurrentPlayer());

        playersManager.removePlayer(this.players.get(2));
        assertEquals(this.players.size() - 1,playersManager.playersCount());
    }


    @Test
    public void shouldPlayersManagerThrowException(){

        PlayersManager playersManager = new PlayersManager(this.players);

        assertThrows(NullPointerException.class, () -> new PlayersManager(null));
        assertThrows(IllegalArgumentException.class, () -> new PlayersManager(new ArrayList<>()));

        List<Player> invalidList = new ArrayList<>();
        invalidList.add(new HumanPlayer("robert", Color.RED));
        assertThrows(IllegalArgumentException.class, () -> new PlayersManager(invalidList));

        invalidList.add(null);
        assertThrows(NullPointerException.class, () -> new PlayersManager(invalidList));

        assertThrows(NullPointerException.class, () -> playersManager.makeMove(null));
        assertThrows(NullPointerException.class, () -> playersManager.updatePosition(null, null));
        assertThrows(NullPointerException.class, () -> playersManager.updatePosition(this.players.getFirst(), null));
        assertThrows(NullPointerException.class, () -> playersManager.updatePosition(null, new Position(2,3)));
        assertThrows(NullPointerException.class, () -> playersManager.removePlayer(null));

        playersManager.updatePosition(this.players.getFirst(), new Position(0,0));

        assertThrows(IllegalArgumentException.class, () -> playersManager.updatePosition(this.players.get(1), new Position(-1,12)));
        assertThrows(IllegalArgumentException.class, () -> playersManager.makeMove(new Position(-1,0)));
        assertThrows(IllegalArgumentException.class, () -> playersManager.makeMove(new Position(0,-1)));

        List<Player> validList = new ArrayList<>();
        validList.add(new HumanPlayer("robert", Color.RED));
        validList.add(new HumanPlayer("alice", Color.BLUE));

        PlayersManager playersManager2 = new PlayersManager(validList);
        playersManager2.removePlayer(validList.getFirst());
        playersManager2.removePlayer(validList.get(1));
        assertThrows(IllegalStateException.class, playersManager2::advanceToNextPlayer);
    }
}
