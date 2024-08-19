package it.unicam.cs.NeculaRobertGabriel123390.api;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.CircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit.TXTCircuitSetup;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.FileFormatError;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParser;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParserFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.io.File;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class TestTXTCircuitSetup {


    @BeforeAll
    public static void setupJavaFX() {
        InitializerLoadingLogger.initialize();
    }


    @Test
    public void testTXTCircuitSetup() {

        File circuitFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("ovalCircuit.txt")).getFile());
        assertTrue(circuitFile.exists());
        FileParser fileParser = FileParserFactory.getParser(circuitFile);
        ParsedData<?> parsedFileData = fileParser.parseFile(circuitFile);

        CircuitSetup circuitSetup = new TXTCircuitSetup();
        assertDoesNotThrow(() -> circuitSetup.setup(parsedFileData));

    }


    @Test
    public void shouldTXTCircuitSetupThrowException1(){

        File circuitFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit1.txt")).getFile());
        assertTrue(circuitFile.exists());
        FileParser fileParser = FileParserFactory.getParser(circuitFile);
        ParsedData<?> parsedFileData = fileParser.parseFile(circuitFile);

        CircuitSetup circuitSetup = new TXTCircuitSetup();
        assertThrows(FileFormatError.class, () -> circuitSetup.setup(parsedFileData));

    }


    @Test
    public void shouldTXTCircuitSetupThrowException2() {
        File circuitFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit2.txt")).getFile());
        assertTrue(circuitFile.exists());
        FileParser fileParser = FileParserFactory.getParser(circuitFile);
        ParsedData<?> parsedFileData = fileParser.parseFile(circuitFile);

        CircuitSetup circuitSetup = new TXTCircuitSetup();
        assertThrows(FileFormatError.class, () -> circuitSetup.setup(parsedFileData));
    }


    @Test
    public void shouldTXTCircuitSetupThrowException3() {
        File circuitFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit3.txt")).getFile());
        assertTrue(circuitFile.exists());
        FileParser fileParser = FileParserFactory.getParser(circuitFile);
        ParsedData<?> parsedFileData = fileParser.parseFile(circuitFile);

        CircuitSetup circuitSetup = new TXTCircuitSetup();
        assertThrows(FileFormatError.class, () -> circuitSetup.setup(parsedFileData));
    }


    @Test
    public void shouldTXTCircuitSetupThrowException4() {
        File circuitFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit4.txt")).getFile());
        assertTrue(circuitFile.exists());
        FileParser fileParser = FileParserFactory.getParser(circuitFile);
        ParsedData<?> parsedFileData = fileParser.parseFile(circuitFile);

        CircuitSetup circuitSetup = new TXTCircuitSetup();
        assertThrows(FileFormatError.class, () -> circuitSetup.setup(parsedFileData));
    }


    @Test
    public void shouldTXTCircuitSetupThrowException5() {
        File circuitFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("errorCircuit5.txt")).getFile());
        assertTrue(circuitFile.exists());
        FileParser fileParser = FileParserFactory.getParser(circuitFile);
        ParsedData<?> parsedFileData = fileParser.parseFile(circuitFile);

        CircuitSetup circuitSetup = new TXTCircuitSetup();
        assertThrows(FileFormatError.class, () -> circuitSetup.setup(parsedFileData));
    }

}
