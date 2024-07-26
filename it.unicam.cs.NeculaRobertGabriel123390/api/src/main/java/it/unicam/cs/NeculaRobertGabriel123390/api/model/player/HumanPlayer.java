package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import javafx.scene.paint.Color;

public class HumanPlayer extends Player {

    public HumanPlayer(String playerName){
        super(playerName);
    }


    public HumanPlayer(String playerName, Color color){
        super(playerName, color);
    }


    public HumanPlayer(String playerName, Color color, Position position){
        super(playerName, color, position);
    }

}
