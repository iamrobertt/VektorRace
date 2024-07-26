package it.unicam.cs.NeculaRobertGabriel123390.api.utils;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;

public class PositionUtils {

    public static Position addPositions(Position pos1, Position pos2){
        return new Position(pos1.getX() + pos2.getX(), pos1.getY() + pos2.getY());
    }

}
