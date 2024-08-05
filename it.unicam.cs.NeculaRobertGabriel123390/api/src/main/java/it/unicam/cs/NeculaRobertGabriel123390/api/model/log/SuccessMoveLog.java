package it.unicam.cs.NeculaRobertGabriel123390.api.model.log;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import javafx.scene.paint.Color;


/**
 * Class that implements the RaceLog interface and provides information about a player that
 * has moved correctly in the circuit (he has not crashed nor collided)
 */
public final class SuccessMoveLog implements RaceLog {

    private final Position position;
    private final Player player;

    public SuccessMoveLog(Player player, Position position) {
        this.player = player;
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "Player " + this.player.getName() + " moved at : " + this.position;
    }


    @Override
    public Color getColor() {return Color.BLACK;}
}
