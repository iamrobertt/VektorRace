package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that implements the FileParser interface for a txt file
 */
public class TXTFileParser implements FileParser {


    /**
     * Method that retrieves all the information from the given file in list format
     * @param file - The file chose by user
     */
    @Override
    public ParsedFileData parseFile(File file) {
        List<String> fileData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) fileData.add(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new TXTParsedFileData(fileData);
    }


    /**
     * Method that returns the txt validator
     * @return FileValidator a file Validator
     */
    @Override
    public FileValidator getFileValidator() {
        return new TXTFileValidator();
    }


}
