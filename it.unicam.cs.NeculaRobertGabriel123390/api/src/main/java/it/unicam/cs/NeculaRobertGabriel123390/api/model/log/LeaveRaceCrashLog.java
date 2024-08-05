package it.unicam.cs.NeculaRobertGabriel123390.api.model.log;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import javafx.scene.paint.Color;


/**
 * Class that extends the CrashLog and provides information about a player that
 * has crashed (he went out of track)
 */
public final class LeaveRaceCrashLog extends GenericCrashLog {


    public LeaveRaceCrashLog(Player player, Position position) {
        super(player, position);
    }


    @Override
    public String getMessage(){return super.getMessage() + "\n" + this.player.getPosition() + " is banned from the race";}


    @Override
    public Color getColor(){return Color.RED;}
}
