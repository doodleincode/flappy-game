package net.awesomeapps.flappy.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import net.awesomeapps.flappy.events.ActionListener;

/**
 * Our custom component that supports bounded touch events.
 *
 * Currently only supports rectangular boundaries.
 *
 * LibGDX doesn't seem to provide native support for handling touch events on specific
 * elements drawn on the screen. This class allows us to create elements with a touch
 * boundary that will only handle events within its boundary.
 *
 * Touch boundaries are determined by our {@link net.awesomeapps.flappy.AppInputAdapter} which processes touch events.
 *
 * Created by daniel on 3/5/17.
 */

public abstract class BaseComponent {

    protected ActionListener listener = null;
    protected TextureRegion texture;
    protected Rectangle boundary;

    public TextureRegion getTexture() {
        return texture;
    }

    /**
     * Set the image used for this component
     *
     * @param texture
     */
    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public Rectangle getBoundary() {
        return boundary;
    }

    /**
     * Set the boundary box that the component will respond to touch events
     *
     * @param boundary
     */
    public void setBoundary(Rectangle boundary) {
        this.boundary = boundary;
    }

    public ActionListener getListener() {
        return listener;
    }

    /**
     * Set the callback handler for when a touch event happens within the boundary
     * of the component
     *
     * @param listener
     */
    public void setActionListener(ActionListener listener) {
        this.listener = listener;
    }

}
