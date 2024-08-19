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


/**
 * Marker interface for parsed file data. This interface provides a common
 * abstraction for different types of file data formats that can be used to
 * create circuits and players.
 * <p>
 * Implementations of this interface represent data extracted from various
 * file formats and are used by {@link FileParser} implementations to parse
 * and validate files. Each specific implementation of {@link ParsedData}
 * contains data structures and values relevant to a particular file format.
 * </p>
 * @param <P> Object of any type( custom object, list, map, ecc.) that contains the data in the file parsed.
 **/
public interface ParsedData<P> {


    /**
     * Method that retrieves the data parsed from the file.
     * @return P The data parsed.
     */
    P getData();
}
