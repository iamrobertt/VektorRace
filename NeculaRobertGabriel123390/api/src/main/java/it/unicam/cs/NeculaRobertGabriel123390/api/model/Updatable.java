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


package it.unicam.cs.NeculaRobertGabriel123390.api.model;


/**
 * Interface for objects that need to be updated with new data based on game events.
 * <p>
 * Classes implementing this interface are designed to receive updates regarding various game states or events,
 * allowing them to adjust their internal state or visual representation accordingly. The {@link #update(Object)}
 * method is called with the updated data, enabling dynamic responses to changes in the game.
 * </p>
 *
 * @param <T> The type of data used to update the object. This could be move results, game state changes, etc.
 */
public interface Updatable<T> {


    /**
     * Updates the object with the provided data.
     * <p>
     * Implementations of this method should handle the provided data to refresh or adjust the object's state
     * or representation. This method is typically used to update visual components, game logic, or other aspects
     * of the system in response to changes in the game.
     * </p>
     *
     * @param updatedData The data used to update the object. This could include details such as move results,
     *                    game state changes, or other relevant information that impacts the object's behavior.
     * @throws NullPointerException if {@code updatedData} is {@code null}.
     */
    void update(T updatedData);
}
