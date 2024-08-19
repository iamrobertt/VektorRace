package it.unicam.cs.NeculaRobertGabriel123390.api.model;

/**
 * The {@code Renderable} interface represents a contract for objects that can be rendered.
 * <p>
 * Classes implementing this interface should provide a specific implementation of the {@link #render()} method,
 * which is intended to handle the rendering or display of the object.
 * </p>
 *
 * @param <T> The type of data or object that the implementing class is expected to render.
 */
public interface Renderable<T> {


    /**
     * Renders the object or data represented by the implementing class.
     * <p>
     * This method should contain the logic necessary to visualize the object,
     * based on the specific needs of the implementing class.
     * </p>
     */
    void render();
}
