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
