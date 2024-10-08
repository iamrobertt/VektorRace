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


import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.UnsupportedFileException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * A factory class for obtaining file parsers based on the file format.
 * <p>
 * This class provides a static method to get a {@link FileParser} instance
 * according to the file extension. It uses a map to associate file extensions
 * with their corresponding parsers.
 * </p>
 * <p>
 * This class cannot be instantiated as it is designed to be used statically.
 * </p>
 */
public final class FileParserFactory {


    private static final Map<String, FileParser> parserMap = new HashMap<>();


    /**
     * Private constructor to prevent instantiation.
     * <p>
     * This constructor is private to ensure that this utility class cannot
     * be instantiated.
     * </p>
     */
    private FileParserFactory() {}


    static {
        parserMap.put("txt", new TXTFileParser());
        // Add other parsers here
    }


    /**
     * Returns a {@link FileParser} for the specified file based on its extension.
     * <p>
     * The method checks the file extension and retrieves the appropriate
     * parser from the map. If the file extension is not supported, an
     * {@link UnsupportedFileException} is thrown.
     * </p>
     *
     * @param file The file for which the parser is to be obtained.
     * @return A {@link FileParser} instance for the file's extension.
     * @throws UnsupportedFileException If the file format is not supported by any registered parser.
     */
    public static FileParser getParser(File file) {
        validateFile(file);
        String extension = getFileExtension(file.getName());
        FileParser parser = parserMap.get(extension);

        if (parser == null)
            throw new UnsupportedFileException("Unsupported file format: " + extension);

        return parser;
    }


    /**
     * Validates that the provided file is not null.
     * <p>
     * This method checks if the file is null and throws an {@link IOException}
     * if it is.
     * </p>
     * @param file The file to be validated.
     * @throws RuntimeException If the file is null.
     */
    private static void validateFile(File file) {
        if(file == null) {
            try {
                throw new IOException("file not found");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Extracts the file extension from the given file name.
     * @param fileName The name of the file from which to extract the extension.
     * @return The file extension in lowercase, or an empty string if no extension is found.
     */
    private static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        return (lastIndex == -1) ? "" : fileName.substring(lastIndex + 1).toLowerCase();
    }
}
