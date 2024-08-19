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
