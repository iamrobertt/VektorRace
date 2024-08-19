package it.unicam.cs.NeculaRobertGabriel123390.app;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.Player;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


/**
 * Controller class responsible for displaying the winner of a race.
 * <p>
 * This class manages the user interface component that shows the name of the winning player
 * after a race. It is connected to an FXML file and operates with JavaFX's {@link Text} component.
 * </p>
 */
public class WinController {


    @FXML
    private Text winnerText;


    /**
     * Displays the name of the winning player in the UI.
     * <p>
     * This method updates the {@link Text} component with the name of the winner. It also
     * performs validation on the provided {@code Player} instance to ensure it is not null
     * and has a valid name before updating the UI.
     * </p>
     *
     * @param winner The {@link Player} who has won the race. Must not be {@code null}.
     * @throws NullPointerException if {@code winner} is {@code null}.
     */
    public void displayWinner(Player winner) {
        PlayerValidator.validate(winner);
        this.winnerText.setText(winner.getName());
    }


}
