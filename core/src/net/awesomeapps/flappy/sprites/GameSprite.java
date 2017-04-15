package net.awesomeapps.flappy.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * A container for our game sprite and coordinate values for each object in the sprite
 *
 * Created by daniel on 3/5/17.
 */

public enum GameSprite {

    INSTANCE;

    public static final int BG_WIDTH = 288;
    public static final int BG_HEIGHT = 512;

    public static final int DAY_BG_X = 0;
    public static final int DAY_BG_Y = 0;

    public static final int NIGHT_BG_X = 292;
    public static final int NIGHT_BG_Y = 0;

    public static final int GROUND_X = 584;
    public static final int GROUND_Y = 0;
    public static final int GROUND_WIDTH = 336;
    public static final int GROUND_HEIGHT = 112;
    public static final int GROUND_Y_OFFSET = -50;  // The Y axis offset for the ground so that it's not too tall

    public static final int MENU_BTN_X = 702;
    public static final int MENU_BTN_Y = 234;
    public static final int MENU_BTN_WIDTH = 236;
    public static final int MENU_BTN_HEIGHT = 70;

    public static final int DOG_X = 6;
    public static final int DOG_Y = 982;
    public static final int DOG_WIDTH = 34;
    public static final int DOG_HEIGHT = 24;
    public static final int DOG_ANI_WIDTH = 146;
    public static final int DOG_ANI_HEIGHT = 24;

    public static final int LOGO_X = 702;
    public static final int LOGO_Y = 182;
    public static final int LOGO_WIDTH = 158;
    public static final int LOGO_HEIGHT = 48;

    public static final int GAMEOVER_X = 794;
    public static final int GAMEOVER_Y = 118;
    public static final int GAMEOVER_WIDTH = 184;
    public static final int GAMEOVER_HEIGHT = 42;

    public static final int TOP_TUBE_X = 112;
    public static final int TOP_TUBE_Y = 646;
    public static final int BOTTOM_TUBE_X = 168;
    public static final int BOTTOM_TUBE_Y = 646;
    public static final int TUBE_WIDTH = 52;
    public static final int TUBE_HEIGHT = 320;

    private Texture texture;

    GameSprite() {
        texture = new Texture(Gdx.files.internal("game_sprite.png"));
    }

    public Texture getTexture() {
        return texture;
    }

}
