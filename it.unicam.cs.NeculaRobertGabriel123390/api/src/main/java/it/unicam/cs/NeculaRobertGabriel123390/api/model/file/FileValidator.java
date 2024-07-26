package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;


/**
 * Interface used to validate a file based on the parsed data format
 */
public interface FileValidator {


    /**
     * Method that checks if fileData is valid to set up a race
     *
     * @return True if the file is valid, false otherwise
     */
    boolean isFileValid(ParsedFileData fileData);


    /*
    private void handleFileNotValid() {
        //TODO tell the view to display an alert
    }
    *
     */


}
