package net.awesomeapps.flappy.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import net.awesomeapps.flappy.events.ActionListener;
import net.awesomeapps.flappy.graphics.TouchButton;
import net.awesomeapps.flappy.sprites.GameSprite;

/**
 * Main menu scene.
 *
 * Created by daniel on 3/4/17.
 */

public class MenuState extends State {

    private TextureRegion playBtnTexture;
    private TextureRegion logo;
    private TextureRegion dog1;
    private TextureRegion dog2;
    private TextureRegion dog3;
    private TouchButton playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        init();
    }

    protected void init() {
        super.init();

        playBtnTexture = new TextureRegion(gameTexture,
                GameSprite.MENU_BTN_X, GameSprite.MENU_BTN_Y, GameSprite.MENU_BTN_WIDTH, GameSprite.MENU_BTN_HEIGHT);
        logo = new TextureRegion(gameTexture,
                GameSprite.LOGO_X, GameSprite.LOGO_Y, GameSprite.LOGO_WIDTH, GameSprite.LOGO_HEIGHT);
        dog1 = new TextureRegion(gameTexture,
                GameSprite.DOG_X, GameSprite.DOG_Y, GameSprite.DOG_WIDTH, GameSprite.DOG_HEIGHT);
        dog2 = new TextureRegion(gameTexture,
                GameSprite.DOG_X + GameSprite.DOG_WIDTH + 22, GameSprite.DOG_Y, GameSprite.DOG_WIDTH, GameSprite.DOG_HEIGHT);
        dog3 = new TextureRegion(gameTexture,
                GameSprite.DOG_X + (GameSprite.DOG_WIDTH + 22) * 2, GameSprite.DOG_Y, GameSprite.DOG_WIDTH, GameSprite.DOG_HEIGHT);

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

        batch.draw(logo, camera.position.x - (logo.getRegionWidth()/2), 280);
        batch.draw(playBtn.getTexture(), playBtn.getBoundary().x, playBtn.getBoundary().y);
        batch.draw(dog1, camera.position.x - (dog1.getRegionWidth()/2) - 55, 195);
        batch.draw(dog2, camera.position.x - (dog1.getRegionWidth()/2), 220);
        batch.draw(dog3, camera.position.x - (dog1.getRegionWidth()/2) + 50, 205);

        postRender(batch);
    }

    @Override
    public void dispose() {

    }

}
