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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.circuit;


import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.UnsupportedFileException;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;

import java.util.HashMap;
import java.util.Map;


/**
 * Factory class that provides a {@link CircuitSetup} instance based on the format of the provided parsed file data.
 * It supports various file formats by associating them with specific {@link CircuitSetup} implementations.
 */
public final class CircuitSetupFactory {


    private static final Map<Class<? extends ParsedData<?>>, CircuitSetup> setups = new HashMap<>();


    static {
        setups.put(TXTParsedData.class, new TXTCircuitSetup());
    }

    /**
     * Provides a {@link CircuitSetup} instance based on the format of the given parsed file data.
     *
     * @param fileData the parsed file data.
     * @return the appropriate {@link CircuitSetup} for the file data format.
     * @throws UnsupportedFileException if the file data format is not supported.
     * @throws IllegalArgumentException if the provided file data is null.
     */
    public static CircuitSetup getCircuitSetup(ParsedData<?> fileData) {
        validateFileData(fileData);

        CircuitSetup setup = setups.get(fileData.getClass());
        if (setup == null)
            throw new UnsupportedFileException("Unsupported fileData format " + fileData.getClass().getName());

        return setup;
    }


    /**
     * Validates the provided parsed file data.
     *
     * @param fileData the parsed file data to validate.
     * @throws IllegalArgumentException if the provided file data is null.
     */
    private static void validateFileData(ParsedData<?> fileData) {
        if(fileData == null)
            throw new NullPointerException("Given ParsedFileData is null");
    }
}
