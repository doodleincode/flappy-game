package net.awesomeapps.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.awesomeapps.flappy.sprites.GameSprite;
import net.awesomeapps.flappy.states.GameOverState;
import net.awesomeapps.flappy.states.GameStateManager;
import net.awesomeapps.flappy.states.MenuState;
import net.awesomeapps.flappy.states.PlayState;

/**
 * The main game entry point. This just adds the menu state and renders it.
 *
 * "FlappyDoge" sprite created by Cheeyon (http://cheeyoon.com/post/75677698649/flappy-doge). Our
 * game sprite was modified from the original by using "dog" in place of "doge".
 *
 *
 * Created by daniel on 3/5/17.
 */
public class FlappyDogGame extends ApplicationAdapter {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "Flappy Dog";

    private SpriteBatch batch;
    private GameStateManager gsm;
    private Music music;
	
	@Override
	public void create () {
        Gdx.gl.glClearColor(1, 1, 1, 1);

        batch = new SpriteBatch();
        gsm = new GameStateManager();

		// Setup the game music and play it
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

        // Add our game menu which is the first thing we need to display
        gsm.push(new MenuState(gsm));
//        gsm.push(new GameOverState(gsm));
//        gsm.push(new PlayState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
        gsm.dispose();
        music.dispose();

        // Dispose the main sprite texture
        GameSprite.INSTANCE.getTexture().dispose();
	}

}
