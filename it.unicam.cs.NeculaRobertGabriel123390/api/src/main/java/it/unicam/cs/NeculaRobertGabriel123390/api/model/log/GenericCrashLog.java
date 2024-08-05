package it.unicam.cs.NeculaRobertGabriel123390.api.model.log;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import javafx.scene.paint.Color;


/**
 * Class that implements the RaceLog interface and provides generic information about a player that
 * has crashed (he went out of track)
 * Other classes extends this class, providing more information about the crash
 */
public class GenericCrashLog implements RaceLog {

    protected final Position position;
    protected final Player player;


    public GenericCrashLog(Player player, Position position) {
        this.player = player;
        this.position = position;
    }


    @Override
    public String getMessage() {
        return "Player " + this.player.getName() + " crashed at : " + this.position;
    }


    @Override
    public Color getColor() {return Color.ORANGE;}


}