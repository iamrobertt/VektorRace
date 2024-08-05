package it.unicam.cs.NeculaRobertGabriel123390.api.model.log;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;


/**
 * Class that extends the CrashLog and provides information about a player that
 * has crashed (he went out of track)
 */
public final class ContinueWithPenaltyCrashLog extends GenericCrashLog {


    public ContinueWithPenaltyCrashLog(Player player, Position position) {
        super(player, position);
    }


    @Override
    public String getMessage() {return super.getMessage() + "\n" + this.player.getName() + " will start again from its previous position " + this.player.getPosition();}

}
