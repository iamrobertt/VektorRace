package it.unicam.cs.NeculaRobertGabriel123390.api;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.BotPlayer;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.HumanPlayer;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testable
public class TestPlayerValidator {


    @Test
    public void testSinglePlayer() {
        assertThrows(NullPointerException.class, () -> PlayerValidator.validate((Player) null));
        assertThrows(NullPointerException.class, () -> PlayerValidator.validate(new HumanPlayer(null, Color.BEIGE)));
        assertThrows(NullPointerException.class, () -> PlayerValidator.validate(new HumanPlayer("ciao", null)));
        assertThrows(IllegalArgumentException.class, () -> PlayerValidator.validate(new HumanPlayer("", Color.RED)));
        assertThrows(IllegalArgumentException.class, () -> PlayerValidator.validate(new HumanPlayer("ciao", Color.valueOf("NON_VALID_COLOR"))));

        assertDoesNotThrow(() -> PlayerValidator.validate(new BotPlayer("bot", Color.GOLD)));
    }


    @Test
    public void testListPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(null);

        assertThrows(NullPointerException.class, () -> players.add(new HumanPlayer(null, Color.BEIGE)));
        assertThrows(NullPointerException.class, () -> players.add(new HumanPlayer("ciao", null)));
        assertThrows(IllegalArgumentException.class, () -> players.add(new HumanPlayer("", Color.RED)));
        assertThrows(IllegalArgumentException.class, () ->players.add(new HumanPlayer("ciao", Color.valueOf("NON_VALID_COLOR"))));

        players.add(new HumanPlayer("ciao", Color.valueOf("RED")));
        players.add(new HumanPlayer("ciao2", Color.valueOf("YELLOW")));
        players.add(new BotPlayer("botPLayer1", Color.valueOf("BLUE")));
    }
}
