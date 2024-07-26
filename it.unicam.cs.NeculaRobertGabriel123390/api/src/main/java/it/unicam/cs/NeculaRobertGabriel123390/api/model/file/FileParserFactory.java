package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;


import java.io.File;


/**
 * Class that supplies parsers based on file format
 */
public class FileParserFactory {


    /**
     * Method that gives the correct parser based on file extension
     * @param file - The file chose by user
     * @return FileParser The file parser for the file extension
     */
    public static FileParser getParser(File file) {
        String fileName = file.getName().toLowerCase();

        if (fileName.endsWith(".txt"))
            return new TXTFileParser();

        // implement new parsers here

        throw new IllegalArgumentException("Unsupported file format");
    }
}
