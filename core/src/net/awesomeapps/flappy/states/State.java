package net.awesomeapps.flappy.states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import net.awesomeapps.flappy.AppInputAdapter;
import net.awesomeapps.flappy.FlappyDogGame;
import net.awesomeapps.flappy.graphics.BaseComponent;
import net.awesomeapps.flappy.sprites.GameSprite;

/**
 * This provides a way to create state in our game. "States" in this sense are really
 * scenes that the game has (play, pause, game over, etc) and not states of objects such as
 * flying, diving, etc.
 *
 * This allows us to easily transition between scenes and tear them down. We use our
 * {@link GameStateManager} class to manage the states.
 *
 * Our states work in conjunction with LibGDX {@link com.badlogic.gdx.ApplicationAdapter} class
 * through its {@link ApplicationAdapter#create()} and {@link ApplicationAdapter#render()} methods.
 *
 * Created by daniel on 3/4/17.
 */

public abstract class State implements Disposable {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;
    protected AppInputAdapter inputAdapter;
    protected Texture gameTexture;
    private TextureRegion background;
    private TextureRegion ground;
    private Vector2 groundPos1, groundPos2;

    public State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
        inputAdapter = new AppInputAdapter(camera);
        gameTexture = GameSprite.INSTANCE.getTexture();
    }

    protected abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch batch);

    protected void init() {
        // Add our custom input handler to libgdx
        Gdx.input.setInputProcessor(inputAdapter);

        // The games main background that will be on every scene
        background = new TextureRegion(gameTexture,
                GameSprite.DAY_BG_X, GameSprite.DAY_BG_Y, GameSprite.BG_WIDTH, GameSprite.BG_HEIGHT);

        // The ground that will be common to all scenes
        ground = new TextureRegion(gameTexture,
                GameSprite.GROUND_X, GameSprite.GROUND_Y, GameSprite.GROUND_WIDTH, GameSprite.GROUND_HEIGHT);

        // Place first ground on screen
        groundPos1 = new Vector2(camera.position.x - (camera.viewportWidth/2), GameSprite.GROUND_Y_OFFSET);

        // This will put the second position just off screen on the right
        groundPos2 = new Vector2(
                camera.position.x - (camera.viewportWidth/2) + ground.getRegionWidth(), GameSprite.GROUND_Y_OFFSET);

        // Set the camera to have a zoomed in viewport
        camera.setToOrtho(false, FlappyDogGame.WIDTH/2, FlappyDogGame.HEIGHT/2);
    }

    protected void preRender(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(background, camera.position.x - (camera.viewportWidth/2), 0);
    }

    protected void postRender(SpriteBatch batch) {
        batch.draw(ground, groundPos1.x, groundPos1.y);
        batch.draw(ground, groundPos2.x, groundPos2.y);

        batch.end();
    }

    protected void updateGround() {
        // If the ground is off screen, update it's position off screen to the right
        // so that we are continuously place the ground ahead of the viewport to make it
        // appear like it's moving
        if (camera.position.x - (camera.viewportWidth/2) > groundPos1.x + ground.getRegionWidth()) {
            groundPos1.add(ground.getRegionWidth() * 2, 0);
        }

        if (camera.position.x - (camera.viewportWidth/2) > groundPos2.x + ground.getRegionWidth()) {
            groundPos2.add(ground.getRegionWidth() * 2, 0);
        }
    }

    public TextureRegion getGround() {
        return ground;
    }

}
