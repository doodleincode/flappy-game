package net.awesomeapps.flappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;

/**
 * The tube obstacles.
 *
 * Created by daniel on 3/4/17.
 */

public class Tube implements Disposable {

    // Width of the tube graphic
    public static final int TUBE_WIDTH = 52;

    // The value range for random (between 0 - 130)
    private static final int FLUCTUATION = 130;

    // The gap between the top and bottom tubes
    private static final int TUBE_GAP = 100;

    private static final int LOWEST_OPENING = 120;

    private TextureRegion topTube, bottomTube;
    private Vector2 posTopTube, posBottomTube;
    private Random random;
    private Rectangle boundsTop, boundsBottom;

    public Tube(float posX) {
        Texture gameTexture = GameSprite.INSTANCE.getTexture();

        topTube = new TextureRegion(gameTexture,
                GameSprite.TOP_TUBE_X, GameSprite.TOP_TUBE_Y, GameSprite.TUBE_WIDTH, GameSprite.TUBE_HEIGHT);

        bottomTube = new TextureRegion(gameTexture,
                GameSprite.BOTTOM_TUBE_X, GameSprite.BOTTOM_TUBE_Y, GameSprite.TUBE_WIDTH, GameSprite.TUBE_HEIGHT);

        // We'll use this to randomly set the Y position
        random = new Random();

        posTopTube = new Vector2(posX, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube = new Vector2(posX, posTopTube.y - TUBE_GAP - bottomTube.getRegionHeight());

        // We'll create rectangle boundaries that will allow us to detect collision
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y,
                topTube.getRegionWidth(), topTube.getRegionHeight());
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y,
                bottomTube.getRegionWidth(), bottomTube.getRegionHeight());
    }

    /**
     * Repositions a tube that goes off screen (left side) to an area that the bird
     * will be approaching (right side)
     *
     * @param posX
     */
    public void reposition(float posX) {
        posTopTube.set(posX, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(posX, posTopTube.y - TUBE_GAP - bottomTube.getRegionHeight());

        // Need to reposition the boundaries
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y);
    }

    public boolean hasCollided(Rectangle player) {
        // If player (i.e. bird) overlaps the top, returns true
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    @Override
    public void dispose() {

    }

    public TextureRegion getTopTube() {
        return topTube;
    }

    public TextureRegion getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

}
