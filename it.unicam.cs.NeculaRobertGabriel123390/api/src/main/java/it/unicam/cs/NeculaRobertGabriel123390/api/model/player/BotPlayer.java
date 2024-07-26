package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import javafx.scene.paint.Color;


public class BotPlayer extends Player{


    public BotPlayer(String playerName){
        super(playerName);
    }


    public BotPlayer(String playerName, Color color){
        super(playerName, color);
    }


    public BotPlayer(String playerName, Color color, Position position) {
        super(playerName, color, position);
    }

}
