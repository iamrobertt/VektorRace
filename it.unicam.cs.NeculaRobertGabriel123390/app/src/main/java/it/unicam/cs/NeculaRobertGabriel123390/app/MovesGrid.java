package it.unicam.cs.NeculaRobertGabriel123390.app;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.PlayerMoves;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.Position;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * Class that manages graphically the next possible moves based on supplied PlayerPossibleMoves data
 * This data is assigned to every button in the grid pane, allowing to move players
 */
public class MovesGrid {


    private final GridPane inputMovesGrid;

    private PlayerMoves playerPossibleMoves;


    public MovesGrid(GridPane inputMovesGrid, PlayerMoves playerPossibleMoves) {
        validateMoveGrid(inputMovesGrid, playerPossibleMoves);
        this.inputMovesGrid = inputMovesGrid;
        this.playerPossibleMoves = playerPossibleMoves;
    }


    private void validateMoveGrid(GridPane inputMovesGrid, PlayerMoves playerPossibleMoves) {
        if(inputMovesGrid == null) throw new NullPointerException("inputMovesGrid is null");
        if(playerPossibleMoves == null) throw new NullPointerException("playerPossibleMoves is null");
        //todo verifica perche ci sono 10 children (un group di troppo)
        //if(inputMovesGrid.getChildren().size() != 9) throw new IllegalArgumentException("Must have 9 children");
        //todo aggiungi controlli per verificare che siano tutti bottoni ecc ecc
        //todo check instanceof button dei children with validate
    }


    /**
     * Method that sets up the grid data based on PLayerPossibleMoves supplied
     */
    public void setupGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Position positionToSet = this.playerPossibleMoves.getMoves()[i][j];
                int index = i * 3 + j;
                Button button = (Button) this.inputMovesGrid.getChildren().get(index);
                button.setUserData(positionToSet);
                button.setText("(" + this.playerPossibleMoves.getMoves()[i][j].getX() + "," + this.playerPossibleMoves.getMoves()[i][j].getY() + ")");
            }
        }
    }


    /**
     * Method that updates the grid data with the next player's possible moves
     * @param playerPossibleMoves - The next player's possible moves
     */
    public void updateGrid(PlayerMoves playerPossibleMoves) {
        this.playerPossibleMoves = playerPossibleMoves;
        setupGrid();
    }

}
