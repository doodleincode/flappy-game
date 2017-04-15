package net.awesomeapps.flappy.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Class to animate sprite objects. This gives us an easy way to animate
 * a sequence of objects inside a sprite image.
 *
 * Created by daniel on 3/4/17.
 */

public class Animation {

    private Array<TextureRegion> frames;

    // The length a frame will stay in view until the next frame is shown
    private float maxFrameTime;

    // Amount of time the animation has been in the current frame
    private float currFrameTime;

    // Number of frames in the animation
    private int frameCount;

    // Current frame
    private int currFrame;

    /**
     *
     * @param region        A TextureRegion that contains all the textures for the animation
     * @param frameCount    Number of frames in the region
     * @param cycleTime     Time to run all frames in the region
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        this(region, frameCount, cycleTime, 0);
    }

    /**
     *
     * @param region        A TextureRegion that contains all the textures for the animation
     * @param frameCount    Number of frames in the region
     * @param cycleTime     Time to run all frames in the region
     * @param spriteOffset  The offset between frames
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime, int spriteOffset) {
        frames = new Array<TextureRegion>();

        // Determine the width of each frame in the sprite
        // If there is a gap between frames in the sprite, we'll subtract that from the width of
        // the sprite to determine the actual frame width
        int frameWidth = (region.getRegionWidth() - (spriteOffset * (frameCount - 1)))/frameCount;

        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, (i * frameWidth) + (i * spriteOffset), 0,
                    frameWidth, region.getRegionHeight()));
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime/frameCount;
        currFrame = 0;
    }

    public void update(float deltaTime) {
        currFrameTime += deltaTime;

        if (currFrameTime > maxFrameTime) {
            currFrame++;
            currFrameTime = 0;
        }

        if (currFrame == frameCount) {
            currFrame = 0;
        }
    }

    public TextureRegion getCurrentFrame() {
        return frames.get(currFrame);
    }

}
