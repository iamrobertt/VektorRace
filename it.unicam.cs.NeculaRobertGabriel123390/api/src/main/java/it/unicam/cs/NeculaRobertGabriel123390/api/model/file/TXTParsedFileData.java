package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;

import java.util.List;


/**
 * Class that implements the FileData interface
 */
public class TXTParsedFileData implements ParsedFileData {


    private final List<String> fileData;


    public TXTParsedFileData(List<String> fileData){
        this.fileData = fileData;
    }


    public List<String> getData() {
        return this.fileData;
    }


}
