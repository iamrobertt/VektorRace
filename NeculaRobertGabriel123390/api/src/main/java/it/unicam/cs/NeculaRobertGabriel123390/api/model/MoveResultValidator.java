package it.unicam.cs.NeculaRobertGabriel123390.api.model;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PositionUtils;


/**
 * A utility class for validating {@link MoveResult} objects.
 * <p>
 * This class provides a method for ensuring that the data within a {@code MoveResult}
 * object is valid and complete. The validation checks that all components of the move result
 * are properly initialized and valid before using the data for updates or other operations.
 * </p>
 */
public class MoveResultValidator {


    /**
     * Don't allow user to instantiate a MoveResultValidator object because that's not how this class is meant to work.
     */
    private MoveResultValidator() {}


    /**
     * Validates the provided move result for updating the view.
     * <p>
     * This method checks that the {@code moveResult} object and its components (player, previous position, move type) are not {@code null}.
     * It ensures that the player and positions are valid and that no component required for updating the view is missing.
     * </p>
     *
     * @param moveResult The move result to validate. Must not be {@code null}.
     * @throws NullPointerException if {@code moveResult} or any of its components (i.e., player, previous position, move type) is {@code null}.
     */
    public static void validate(MoveResult moveResult) {
        if(moveResult == null)
            throw new NullPointerException("moveResult is null");
        if(moveResult.moveType() == null)
            throw new NullPointerException("moveType is null");

        PlayerValidator.validate(moveResult.player());
        PositionUtils.validateCircuitNodePosition(moveResult.prevOrNewPosition());
    }
}
