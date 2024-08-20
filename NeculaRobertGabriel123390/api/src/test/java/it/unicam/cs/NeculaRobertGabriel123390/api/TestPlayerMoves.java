package it.unicam.cs.NeculaRobertGabriel123390.api;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.PlayerMoves;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;


import static org.junit.jupiter.api.Assertions.*;


@Testable
public class TestPlayerMoves {



    @Test
    public void testUpdateMoves(){

        PlayerMoves playerMoves = new PlayerMoves();
        Position updatePosition = new Position(1,1);

        playerMoves.update(updatePosition);

        Position[][] newMoves = new Position[][]{
                {new Position(0,0), new Position(1,0), new Position(2,0)},
                {new Position(0,1), new Position(1,1), new Position(2,1)},
                {new Position(0,2), new Position(1,2), new Position(2,2)}
        };

        assertArrayEquals(newMoves, playerMoves.getMoves());
    }


    @Test
    public void shouldPlayerMovesThrow() {

        PlayerMoves playerMoves = new PlayerMoves();

        assertThrows(NullPointerException.class, () -> playerMoves.update(null));

    }
}
