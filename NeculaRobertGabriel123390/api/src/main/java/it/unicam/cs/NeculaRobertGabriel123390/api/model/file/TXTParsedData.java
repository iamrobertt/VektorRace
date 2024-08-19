package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;

import java.util.List;


/**
 * Implementation of the {@link ParsedData} interface for TXT file data.
 * <p>
 * This class encapsulates the parsed data of a TXT file as a list of strings,
 * where each string represents a line from the file. It serves as a data holder
 * for the information extracted from a TXT file, enabling subsequent processing
 * and validation.
 * </p>
 */
public final class TXTParsedData implements ParsedData<List<String>> {


    private final List<String> fileData;


    /**
     * Constructs a {@code TXTParsedFileData} object with the given file data.
     * <p>
     * This constructor initializes the object with the list of strings representing
     * the lines of the parsed TXT file.
     * </p>
     *
     * @param fileData a list of strings where each string represents a line from the TXT file
     */
    public TXTParsedData(List<String> fileData){
        validateFileData(fileData);
        this.fileData = fileData;
    }


    private void validateFileData(List<String> fileData) {
        if(fileData == null)
            throw new NullPointerException("fileData is null");

        if(fileData.isEmpty())
            throw new IllegalArgumentException("fileData is empty");
    }


    /**
     * Retrieves the list of strings representing the lines of the parsed TXT file.
     * <p>
     * This method provides access to the raw data of the file, allowing other components
     * to process and validate it as needed.
     * </p>
     *
     * @return a list of strings representing the lines of the TXT file
     */
    @Override
    public List<String> getData() {return this.fileData;}
}
