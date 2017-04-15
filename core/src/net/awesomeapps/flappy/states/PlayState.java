package net.awesomeapps.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import net.awesomeapps.flappy.sprites.Dog;
import net.awesomeapps.flappy.sprites.GameSprite;
import net.awesomeapps.flappy.sprites.Tube;

import java.util.Iterator;

/**
 * The play scene.
 *
 * Created by daniel on 3/4/17.
 */

public class PlayState extends State {

    // The space between tubes
    private static final int TUBE_SPACING = 125;

    // The number of tubes the game will have at any given moment
    private static final int TUBE_COUNT = 4;

    private Dog dog;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        init();
    }

    protected void init() {
        super.init();

        dog = new Dog(50, 200);
        tubes = new Array<Tube>();

        // Create the tubes
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            // Prevent the dog from jumping beyond the top of the viewport
            if ((dog.getPosition().y + dog.getTexture().getRegionHeight()) < camera.viewportHeight) {
                dog.jump();
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        updateGround();

        boolean hasCollided = false;

        dog.update(deltaTime);

        // Move the camera forward so that it will follow the movement of the dog
        // Use 80 to offset the camera from the dog
        camera.position.x = dog.getPosition().x + 80;

        Iterator<Tube> iter = tubes.iterator();

        while (iter.hasNext() && !hasCollided) {
            // We don't need to do this, but just to make it more clear that our
            // iterator is a tube object
            Tube tube = iter.next();

            // If tube is to the left of the screen, we'll reposition it
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getRegionWidth()) {
                // Position to the far right side
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            // Check if the dog has collided with a tube
            if (tube.hasCollided(dog.getBounds())) {
                hasCollided = true;
            }
        }

        // Determine collided based on the current collided state (whether collided with a tube)
        // or the dog has touched the ground
        hasCollided = hasCollided || dog.getPosition().y <= getGround().getRegionHeight() + GameSprite.GROUND_Y_OFFSET;

        // If the dog collided with a tube or has hit the ground, restart the game
        if (hasCollided) {
            gsm.set(new GameOverState(gsm));
        }

        // Tell libgdx that the camera position has changed
        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        preRender(batch);

        // Place the dog on the screen
        batch.draw(dog.getTexture(), dog.getPosition().x, dog.getPosition().y);

        // Place the tubes
        for (Tube tube: tubes) {
            batch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            batch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }

        postRender(batch);
    }

    @Override
    public void dispose() {
        dog.dispose();

        for (Tube tube: tubes) {
            tube.dispose();
        }
    }

}
