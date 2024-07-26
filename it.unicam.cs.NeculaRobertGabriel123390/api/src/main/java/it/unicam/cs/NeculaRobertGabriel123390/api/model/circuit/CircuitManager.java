package it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Line;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.CircuitNodeState;
import javafx.scene.paint.Color;

import java.util.Map;

public class CircuitManager {

    private final Map<Position, CircuitTile> mappedCircuit;


    private final Line startLine;


    private final Line endLine;


    public CircuitManager(Map<Position, CircuitTile> mappedCircuit, Line startLine, Line endline) {
        //todo validate
        this.mappedCircuit = mappedCircuit;
        this.startLine = startLine;
        this.endLine = endline;
    }


    public void setPositionOccupiedByPlayer(Player player, Position position){
        //todo modifica
        Color color = player.getPlayerColor();
        this.mappedCircuit.get(position).setState(CircuitNodeState.wallNode);
    }


    public boolean isTileAlreadyOccupied(Position position){
        //todo
        //return this.mappedCircuit.get(position).getState() != x;
        return false;
    }


    public Line getStartLine() {
        return this.startLine;
    }



}
