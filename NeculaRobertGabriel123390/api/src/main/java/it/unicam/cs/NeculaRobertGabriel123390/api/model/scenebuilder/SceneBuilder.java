package it.unicam.cs.NeculaRobertGabriel123390.api.model.scenebuilder;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.Renderable;
import javafx.scene.layout.Pane;


/**
 * The {@code SceneBuilder} class is an abstract base class for constructing and rendering scenes in a JavaFX application.
 * <p>
 * This class is designed to be extended by specific scene builders that need to manage a graphical container (such as a
 * {@link Pane}) and some associated data that dictates how the scene should be built and rendered. The class also implements
 * the {@link Renderable} interface, requiring subclasses to define the rendering logic specific to the type of data they handle.
 * </p>
 *
 * @param <S> The type of the container, which must extend {@link Pane}. This container holds the graphical components of the scene.
 * @param <T> The type of the data used to build and render the scene.
 */
public abstract class SceneBuilder<S extends Pane, T> implements Renderable<T>{


    /**
     * The container used to hold the graphical components of the scene.
     */
    protected final S container;


    /**
     * The data used to build and render the scene.
     */
    protected T data;


    /**
     * Constructs a {@code SceneBuilder} with the specified container and data.
     *
     * @param container The graphical container that will hold the components of the scene.
     * @param data      The data used to build and render the scene.
     * @throws NullPointerException if either {@code container} or {@code data} is {@code null}.
     */
    public SceneBuilder(S container, T data){
        validate(container, data);
        this.container = container;
        this.data = data;
    }


    /**
     * Validates the container and data. Throws a {@link NullPointerException} if either is null.
     *
     * @param container The container to be validated.
     * @param data      The data to be validated.
     * @throws NullPointerException if either {@code container} or {@code data} is {@code null}.
     */
    private void validate(S container, T data){
        if(container == null)
            throw new NullPointerException("container is null");
        if(data == null)
            throw new NullPointerException("data is null");
    }


    /**
     * Returns the container associated with this scene builder.
     *
     * @return The container that holds the graphical components of the scene.
     */
    public S getContainer(){
        return this.container;
    }


    /**
     * Returns the data associated with this scene builder.
     *
     * @return The data used to build and render the scene.
     */
    public T getData(){return this.data;}

}
