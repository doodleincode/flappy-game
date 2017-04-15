package net.awesomeapps.flappy;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import net.awesomeapps.flappy.graphics.BaseComponent;

/**
 * This is our input processor. This will handle touch events on components that
 * extends our {@link BaseComponent} class. Touch events outside of this will be
 * handled by LibGDX as normal.
 *
 * Created by daniel on 3/5/17.
 */

public class AppInputAdapter extends InputAdapter {

    private Array<BaseComponent> components;
    private Rectangle touchArea;
    private OrthographicCamera camera;

    public AppInputAdapter(OrthographicCamera camera) {
        this.camera = camera;
        components = new Array<BaseComponent>();

        // Create a touch area 1x1 pixels so that we can use it to determine
        // if a component was touched
        touchArea = new Rectangle(0, 0, 1, 1);
    }

    /**
     * This is our method that allows us to handle touch events that occur
     * within the bounds of the compoent.
     *
     * We implement touch boundaries by creating a transparent/hidden 1x1 pixel box
     * that is placed on screen at the location of the where the touch occurred. If the
     * component's boundary box overlaps the 1x1 pixel, we know that the touch
     * occurred inside the component.
     *
     * @param x
     * @param y
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        // Translate the screen coordinates to the "world" coordinates of the application
        // Since screen coordinate starts at top/left, we need to translate to "world"
        // coordinates which starts at bottom/left
        Vector3 coors = camera.unproject(new Vector3(x, y, 0));

        // Update the touch area coordinates with the touch point
        // so that we can determine if the touch area overlaps a component
        touchArea.setPosition(coors.x, coors.y);

        for (BaseComponent component: components) {
            // Call component action if touch within the bounds of the
            // component and a listener was attached
            if (touchArea.overlaps(component.getBoundary()) && component.getListener() != null) {
                component.getListener().handleAction();

                return true;
            }
        }

        return false;
    }

    public void registerComponent(BaseComponent component) {
        components.add(component);
    }

}
