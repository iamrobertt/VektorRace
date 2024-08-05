package it.unicam.cs.NeculaRobertGabriel123390.api.model.log;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import javafx.scene.paint.Color;


/**
 * Class that implements the RaceLog interface and provides information about a player that
 * is going to collide with another player if he makes that move
 */
public final class CollisionLog implements RaceLog {

    private final Position position;
    private final Player player;

    public CollisionLog(Player player, Position position) {
        this.player = player;
        this.position = position;
    }


    @Override
    public String getMessage() {return this.player.getName() + " will collide going at " + this.position + ".\nThe move has not been saved, try again.";}


    @Override
    public Color getColor() {return Color.GOLDENROD;}
}