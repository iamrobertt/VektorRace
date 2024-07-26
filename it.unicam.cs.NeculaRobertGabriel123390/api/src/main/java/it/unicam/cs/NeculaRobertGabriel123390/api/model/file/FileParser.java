package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;


import java.io.File;


/**
 * Interface that based on the received File, returns the fileData type for the file format and gives the validator for that specific file format
 */
public interface FileParser {


    /**
     * Method that parses a file to a FileData based on the file extension
     * @param file - The file chose by user
     * @return ParsedFileData the file data for that extension
     */
    ParsedFileData parseFile(File file);


    /**
     * Method that based return a file validator based on file extension given
     * @return FileValidator The file validator for the file given
     */
    FileValidator getFileValidator();
}
