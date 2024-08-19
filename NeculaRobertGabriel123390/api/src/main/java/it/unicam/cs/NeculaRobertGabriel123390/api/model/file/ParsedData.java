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
