package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.UnsupportedFileFormatException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * Class that supplies parsers based on file format
 */
public class FileParserFactory {


    private static final Map<String, FileParser> parserMap = new HashMap<>();

    static {
        parserMap.put("txt", new TXTFileParser());
        // Add other parsers here
    }

    /**
     * Method that gives the correct parser based on file extension
     * @param file - The file chosen by user
     * @return FileParser The file parser for the file extension
     * @throws UnsupportedFileFormatException if the file format is not supported
     */
    public static FileParser getParser(File file) {
        String extension = getFileExtension(file.getName());
        FileParser parser = parserMap.get(extension);

        if (parser == null)
            throw new UnsupportedFileFormatException("Unsupported file format: " + extension);

        return parser;
    }

    private static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        return (lastIndex == -1) ? "" : fileName.substring(lastIndex + 1).toLowerCase();
    }
}
