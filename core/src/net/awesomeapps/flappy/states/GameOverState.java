package net.awesomeapps.flappy.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import net.awesomeapps.flappy.events.ActionListener;
import net.awesomeapps.flappy.graphics.TouchButton;
import net.awesomeapps.flappy.sprites.GameSprite;

/**
 * The game over scene.
 *
 * Created by daniel on 3/4/17.
 */

public class GameOverState extends State {

    private TextureRegion gameOverTitle;
    private TextureRegion playBtnTexture;
    private TouchButton playBtn;

    public GameOverState(GameStateManager gsm) {
        super(gsm);

        init();
    }

    protected void init() {
        super.init();

        gameOverTitle = new TextureRegion(gameTexture,
                GameSprite.GAMEOVER_X, GameSprite.GAMEOVER_Y, GameSprite.GAMEOVER_WIDTH, GameSprite.GAMEOVER_HEIGHT);
        playBtnTexture = new TextureRegion(gameTexture,
                GameSprite.MENU_BTN_X, GameSprite.MENU_BTN_Y, GameSprite.MENU_BTN_WIDTH, GameSprite.MENU_BTN_HEIGHT);

        playBtn = new TouchButton();
        playBtn.setTexture(playBtnTexture);
        playBtn.setBoundary(new Rectangle(camera.position.x - (playBtnTexture.getRegionWidth()/2), 60,
                playBtnTexture.getRegionWidth(), playBtnTexture.getRegionHeight()));

        playBtn.setActionListener(new ActionListener() {

            @Override
            public void handleAction() {
                gsm.set(new PlayState(gsm));
            }

        });

        // Add our buttons to the input processor so that we can handle input events
        // and pass them off to the buttons as needed
        inputAdapter.registerComponent(playBtn);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        preRender(batch);

        batch.draw(gameOverTitle, camera.position.x - (gameOverTitle.getRegionWidth()/2), 280);
        batch.draw(playBtn.getTexture(), playBtn.getBoundary().x, playBtn.getBoundary().y);

        postRender(batch);
    }

    @Override
    public void dispose() {

    }

}
