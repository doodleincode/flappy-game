package net.awesomeapps.flappy.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import net.awesomeapps.flappy.graphics.Animation;

/**
 * Our flying dog.
 *
 * Created by daniel on 3/4/17.
 */

public class Dog implements Disposable {

    // The amount of forward movement
    private static final int MOVEMENT = 100;

    // The amount of vertical velocity the dog will go
    private static final int JUMP_VELOCITY = 250;

    // Fall rate
    private static final int GRAVITY = -15;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private TextureRegion dogTextureRegion;
    private Animation animation;
    private Sound flapSound;

    public Dog(int startX, int startY) {
        position = new Vector3(startX, startY, 0);
        velocity = new Vector3(0, 0, 0);

        dogTextureRegion = new TextureRegion(GameSprite.INSTANCE.getTexture(),
                GameSprite.DOG_X, GameSprite.DOG_Y, GameSprite.DOG_ANI_WIDTH, GameSprite.DOG_ANI_HEIGHT);
        animation = new Animation(dogTextureRegion, 3, 0.5f, 22);

        bounds = new Rectangle(startX, startY, GameSprite.DOG_WIDTH, GameSprite.DOG_HEIGHT);

        flapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float deltaTime) {
        // Only add gravity if bird hasn't reached the bottom
        if (position.y > 0) {
            // Make the bird fall using our gravity value
            velocity.add(0, GRAVITY, 0);
        }

        // Need to scale the velocity to the change in time so that
        // it falls correctly
        velocity.scl(deltaTime);

        // Move the bird
        // Move forward by 100 relation to time
        // Make bird fall
        position.add(MOVEMENT * deltaTime, velocity.y, 0);

        // If the bird falls below the screen, reset y to be absolute bottom
        // so that it can't go off screen
        if (position.y < 0) {
            position.y = 0;
        }

        // Reset the velocity scaling
        velocity.scl(1/deltaTime);

        bounds.setPosition(position.x, position.y);

        // Update the bird animation
        animation.update(deltaTime);
    }

    public void jump() {
        velocity.y = JUMP_VELOCITY;
        flapSound.play(0.3f);
    }

    @Override
    public void dispose() {
        flapSound.dispose();
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return animation.getCurrentFrame();
    }

    public Rectangle getBounds() {
        return bounds;
    }

}
