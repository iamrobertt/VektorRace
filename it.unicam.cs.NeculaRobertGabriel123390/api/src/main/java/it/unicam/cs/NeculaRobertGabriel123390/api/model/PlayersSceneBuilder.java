package it.unicam.cs.NeculaRobertGabriel123390.api.model;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import java.util.List;


/**
 * Class that build a displays the players data
 */
public class PlayersSceneBuilder {


    private final List<Player> players;


    private final GridPane playersGrid;


    public PlayersSceneBuilder(List<Player> players, GridPane playersGrid) {
        validatePlayersSceneBuilder(players, playersGrid);
        this.playersGrid = playersGrid;
        this.players = players;
    }


    /**
     * Method that checks if data passed to constructor is valid
     *
     * @param players - The players to build
     * @param tablePlayers - The table where the players are displayed
     */
    private void validatePlayersSceneBuilder(List<Player> players, GridPane tablePlayers) {
        if (players == null) throw new NullPointerException("players is null");
        if (tablePlayers == null) throw new NullPointerException("tablePlayers is null");
    }


    /**
     * Method that displays the players
     */
    public void build() {
        displayPlayers();
    }


    /**
     * Method that iterates through the list and adds every player to the gridPane
     */
    private void displayPlayers(){
        for(int i = 0; i < this.players.size(); i++) {
            Rectangle playerColorRectangle = createRectangleWithPlayerColor(this.players.get(i).getColor());
            Text playerNameText = createTextWithPlayerName(this.players.get(i).getName());
            this.playersGrid.add(playerColorRectangle, 0, i);
            this.playersGrid.add(playerNameText, 1, i);
        }
    }


    /**
     * Method that creates a rectangle with the chose color
     * @param playerColor - The color of the player
     * @return Rectangle A rectangle filled with the player color
     */
    private Rectangle createRectangleWithPlayerColor(Color playerColor){
        Rectangle rectangle = new Rectangle(20, 20);
        rectangle.setFill(playerColor);
        return rectangle;
    }


    /**
     * Method that creates a text with the player name
     * @param playerName - The name of the player
     * @return Text A text object with player name
     */
    private Text createTextWithPlayerName(String playerName) {
        return new Text(playerName);
    }


}
