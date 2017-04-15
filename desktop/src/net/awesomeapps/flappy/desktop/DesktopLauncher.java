package net.awesomeapps.flappy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.awesomeapps.flappy.FlappyDogGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyDogGame.WIDTH;
		config.height = FlappyDogGame.HEIGHT;
		config.title = FlappyDogGame.TITLE;
		config.resizable = false;
		config.fullscreen = false;

		new LwjglApplication(new FlappyDogGame(), config);
	}
}
