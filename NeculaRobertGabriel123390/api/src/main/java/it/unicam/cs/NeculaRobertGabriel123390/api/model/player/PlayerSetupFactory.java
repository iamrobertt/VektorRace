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


package it.unicam.cs.NeculaRobertGabriel123390.api.model.player;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.exception.UnsupportedFileException;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.ParsedData;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.file.TXTParsedData;

import java.util.HashMap;
import java.util.Map;


/**
 * Factory class for creating instances of {@link PlayerSetup}.
 * <p>
 * This factory provides {@link PlayerSetup} implementations based on the format of the provided
 * parsed file data.
 * </p>
 */
public final class PlayerSetupFactory {


    private static final Map<Class<? extends ParsedData<?>>, PlayerSetup> setups = new HashMap<>();

    static {
        setups.put(TXTParsedData.class, new TXTPlayerSetup());
    }


    /**
     * Returns a {@link PlayerSetup} instance based on the provided file data.
     *
     * @param fileData The parsed file data.
     * @return A {@link PlayerSetup} instance for the given file data format.
     * @throws IllegalArgumentException If the file data format is not supported.
     */
    public static PlayerSetup getPlayerSetup(ParsedData<?> fileData) {

        PlayerSetup setup = setups.get(fileData.getClass());
        if (setup == null)
            throw new UnsupportedFileException("Unsupported fileData format " + fileData.getClass().getName());

        return setup;
    }
}
