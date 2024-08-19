/*
 * Copyright (c) 2024.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


package it.unicam.cs.NeculaRobertGabriel123390.api.model.file;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.FileParsingException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that implements the {@link FileParser} interface for parsing TXT files.
 * <p>
 * This class is responsible for reading the content of a TXT file and converting
 * it into a {@link TXTParsedData} object. Additionally, it provides file validation
 * before parsing to ensure that the file exists, is readable, and is of the correct type.
 * </p>
 *
 * <p>
 * The {@code TXTFileParser} reads the file line by line using a {@link BufferedReader}
 * and stores the content in a list of strings.
 * </p>
 *
 * @see FileParser
 * @see TXTParsedData
 */
public final class TXTFileParser implements FileParser {


    /**
     * Parses the given TXT file into a {@link TXTParsedData} object.
     * <p>
     * The file content is read line by line and stored in a {@code List<String>}.
     * Each line of the file corresponds to an entry in the list.
     * </p>
     *
     * @param file the TXT file chosen by the user to be parsed.
     * @return {@link TXTParsedData} representing the parsed file data.
     * @throws FileParsingException if an I/O error occurs while reading the file.
     * @throws NullPointerException if the file is {@code null}.
     */
    @Override
    public ParsedData<?> parseFile(File file) {
        validateFile(file);
        List<String> fileData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
                fileData.add(line);
        } catch (IOException e) {
            throw new FileParsingException("Error parsing file", e);
        }

        return new TXTParsedData(fileData);
    }


    /**
     * Validates the provided file.
     * <p>
     * This method ensures that the file is not {@code null}, exists, is readable,
     * and is a regular file. If any of these conditions are not met,
     * it logs the corresponding error and throws an appropriate exception.
     * </p>
     *
     * @param file the file to be validated.
     * @throws NullPointerException if the file is {@code null}.
     * @throws FileNotFoundException if the file does not exist.
     * @throws IOException if the file cannot be read or is not a regular file.
     */
    @SuppressWarnings("javadoc")
    private void validateFile(File file) {
        if(file == null)
            throw new NullPointerException("File is null");

        if(!file.exists())
            try {
                throw new FileNotFoundException("File not found");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        if(!file.canRead())
            try {
                throw new IOException("File is not readable");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        if(!file.isFile())
            try {
                throw new IOException("File is not a file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }


}
