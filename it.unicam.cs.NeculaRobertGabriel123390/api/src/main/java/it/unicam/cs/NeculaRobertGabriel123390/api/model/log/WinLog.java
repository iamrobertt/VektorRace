package it.unicam.cs.NeculaRobertGabriel123390.api.model.log;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import javafx.scene.paint.Color;


/**
 * Class that implements the RaceLog interface and provides information about a player that
 * has won the race, crossing the end line
 */
public final class WinLog implements RaceLog {


    private final Player player;


    public WinLog(Player player) {this.player = player;}


    @Override
    public String getMessage() {return this.player.getName() + " has win the race.";}


    @Override
    public Color getColor() {return Color.GREEN;}

}
