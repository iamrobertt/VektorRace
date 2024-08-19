package it.unicam.cs.NeculaRobertGabriel123390.api;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.FileFormatError;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParser;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParserFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.PlayerSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.player.TXTPlayerSetup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class TestTXTPlayerSetup {


    @BeforeAll
    public static void setupJavaFX() {
        InitializerLoadingLogger.initialize();
    }



    @Test
    public void testTXTPlayerSetup(){
            File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("ovalCircuit.txt")).getFile());
            assertTrue(file.exists());

            FileParser fileParser = FileParserFactory.getParser(file);
            ParsedData<?> parsedFileData = fileParser.parseFile(file);

            PlayerSetup playerSetup = new TXTPlayerSetup();

            assertDoesNotThrow(() -> playerSetup.setup(parsedFileData));
    }


    @Test
    public void shouldTXTPlayerSetupThrowException1(){
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit1.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        ParsedData<?> parsedFileData = fileParser.parseFile(file);

        PlayerSetup playerSetup = new TXTPlayerSetup();

        assertThrows(FileFormatError.class, () -> playerSetup.setup(parsedFileData));
    }


    @Test
    public void shouldTXTPlayerSetupThrowException2(){

        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit2.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        ParsedData<?> parsedFileData = fileParser.parseFile(file);

        PlayerSetup playerSetup = new TXTPlayerSetup();

        assertThrows(FileFormatError.class, () -> playerSetup.setup(parsedFileData));

    }


    @Test
    public void shouldTXTPlayerSetupThrowException3(){

        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit3.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        ParsedData<?> parsedFileData = fileParser.parseFile(file);

        PlayerSetup playerSetup = new TXTPlayerSetup();

        assertThrows(FileFormatError.class, () -> playerSetup.setup(parsedFileData));

    }


    @Test
    public void shouldTXTPlayerSetupThrowException4(){

        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit4.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        ParsedData<?> parsedFileData = fileParser.parseFile(file);

        PlayerSetup playerSetup = new TXTPlayerSetup();

        assertThrows(FileFormatError.class, () -> playerSetup.setup(parsedFileData));

    }


    @Test
    public void shouldTXTPlayerSetupThrowException5(){
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit5.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        ParsedData<?> parsedFileData = fileParser.parseFile(file);

        PlayerSetup playerSetup = new TXTPlayerSetup();

        assertThrows(FileFormatError.class, () -> playerSetup.setup(parsedFileData));
    }


    @Test
    public void shouldTXTPlayerSetupThrowException6(){
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit6.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        ParsedData<?> parsedFileData = fileParser.parseFile(file);

        PlayerSetup playerSetup = new TXTPlayerSetup();

        assertThrows(FileFormatError.class, () -> playerSetup.setup(parsedFileData));
    }


    @Test
    public void shouldTXTPlayerSetupThrowException7(){
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit7.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        ParsedData<?> parsedFileData = fileParser.parseFile(file);

        PlayerSetup playerSetup = new TXTPlayerSetup();

        assertThrows(FileFormatError.class, () -> playerSetup.setup(parsedFileData));
    }


    @Test
    public void shouldTXTPlayerSetupThrowException8(){
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit8.txt")).getFile());
        assertTrue(file.exists());

        FileParser fileParser = FileParserFactory.getParser(file);
        ParsedData<?> parsedFileData = fileParser.parseFile(file);

        PlayerSetup playerSetup = new TXTPlayerSetup();

        assertThrows(FileFormatError.class, () -> playerSetup.setup(parsedFileData));
    }
}
