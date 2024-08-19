package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;


import java.io.File;


/**
 * Interface that provides methods to parse a file into a {@link ParsedData} object
 * based on the file's format, and to return a corresponding {@link ParsedDataValidator}
 * for validating the file.
 */
public interface FileParser {


    /**
     * Parses the given file into a {@link ParsedData} object based on the file's extension.
     * This method handles the extraction of data specific to the file format and creates an
     * appropriate representation as a ParsedFileData object.
     *
     * @param file the file selected by the user
     * @return {@link ParsedData} the data representation specific to the file's format
     * @throws IllegalArgumentException if the file format is unsupported or if the file is invalid
     */
    ParsedData<?> parseFile(File file);

}
