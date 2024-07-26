package it.unicam.cs.NeculaRobertGabriel123390.api;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParser;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileParserFactory;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.FileValidator;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedFileData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.Race;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.race.RaceSetup;


import java.io.File;


public class LoadingDataController {

    private Race race;

    /**
     * Method that parses and validates the data supplied and sets up a new race
     * @param file - The supplied file
     */
    public void initializeRaceData(File file) {

        FileParser fileParser = FileParserFactory.getParser(file);
        ParsedFileData parsedData = fileParser.parseFile(file);
        FileValidator fileValidator = fileParser.getFileValidator();

        validateFile(parsedData, fileValidator);
        RaceSetup raceSetup = new RaceSetup(parsedData);

        this.race = raceSetup.setup();
        if (!isRaceValid()) throw new IllegalStateException("File not valid for a race");
    }


    /**
     * Method that uses a validator to validate the parsed data
     * @param parsedData - The data parsed from the file
     * @param fileValidator - The validator for that specific file type
     */
    private void validateFile(ParsedFileData parsedData, FileValidator fileValidator) {
        if (!fileValidator.isFileValid(parsedData)) throw new IllegalArgumentException("File not valid for a race");
    }

    public int getNumBotPlayers(){
        return this.race.getNumberOfBotPlayers();
    }


    public int getNumHumanPlayers(){
        return this.race.getNumberOfHumanPlayers();
    }


    public boolean isRaceValid() {
        return this.race != null;
    }


    public Race getRace() {
        return this.race;
    }

}
