package it.unicam.cs.NeculaRobertGabriel123390.api;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.FileFormatError;
import it.unicam.cs.NeculaRobertGabriel123390.api.utils.PlayerUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class TestPlayerUtils {


    @Test
    public void test(){
        String countPlayer = "2B 4H";

        assertEquals(2, PlayerUtils.getBotCount(countPlayer));
        assertEquals(4, PlayerUtils.getHumanCount(countPlayer));

        assertThrows(NullPointerException.class, () -> PlayerUtils.getHumanCount(null));
        assertThrows(NullPointerException.class, () -> PlayerUtils.getBotCount(null));
        assertThrows(FileFormatError.class, () -> PlayerUtils.getHumanCount("ciao"));
        assertThrows(FileFormatError.class, () -> PlayerUtils.getBotCount("ciao"));
        assertThrows(FileFormatError.class, () -> PlayerUtils.getHumanCount(""));
        assertThrows(FileFormatError.class, () -> PlayerUtils.getBotCount(""));
        assertThrows(FileFormatError.class, () -> PlayerUtils.getHumanCount("2B"));
        assertThrows(FileFormatError.class, () -> PlayerUtils.getBotCount("2H"));
        assertThrows(FileFormatError.class, () -> PlayerUtils.getHumanCount("2B -1C"));
        assertThrows(FileFormatError.class, () -> PlayerUtils.getBotCount("2B -1C"));
        assertThrows(FileFormatError.class, () -> PlayerUtils.getHumanCount("2H -1B"));
        assertThrows(FileFormatError.class, () -> PlayerUtils.getBotCount("2H -1B"));
        assertDoesNotThrow(() -> PlayerUtils.getHumanCount("2B 4H"));
        assertDoesNotThrow(() -> PlayerUtils.getBotCount("2B 4H"));

    }
}
