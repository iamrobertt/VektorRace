package it.unicam.cs.NeculaRobertGabriel123390.api;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.BotPlayer;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.HumanPlayer;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class TestPlayer {


    @Test
    public void testGetterSetter(){

        Player player = new HumanPlayer("robert", Color.BEIGE);
        player.setPosition(new Position(10,10));
        assertEquals("robert", player.getName());
        assertEquals(Color.BEIGE, player.getColor());
        assertEquals(new Position(10,10), player.getPosition());

        player.setPosition(new Position(20,20));
        assertEquals(new Position(20,20), player.getPosition());

    }


    @Test
    public void shouldPlayerThrowException() {
        Player player = new HumanPlayer("robert", Color.BEIGE);
        assertThrows(NullPointerException.class, player::getPosition);

        assertThrows(NullPointerException.class, () -> new HumanPlayer(null, Color.BEIGE));
        assertThrows(NullPointerException.class, () -> new HumanPlayer("robert", null));
        assertThrows(NullPointerException.class, () -> new HumanPlayer(null, null));

        assertThrows(IllegalArgumentException.class, () -> player.setPosition(new Position(-1,1)));
        assertFalse(player.isBot());

        player.setPosition(new Position(5,5));
        assertDoesNotThrow(player::getPosition);

        Player botPLayer = new BotPlayer("bot", Color.BEIGE);
        assertTrue(botPLayer.isBot());
    }


}
