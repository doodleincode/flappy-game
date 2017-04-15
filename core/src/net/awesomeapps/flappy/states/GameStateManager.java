package net.awesomeapps.flappy.states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.Iterator;
import java.util.Stack;

/**
 * Manages the scene states of our game. This class works directly with LibGDX {@link ApplicationAdapter}
 * to render the scene.
 *
 * Created by daniel on 3/4/17.
 */

public class GameStateManager implements Disposable {

    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    /**
     * Replaces the top most state with another
     *
     * @param state
     */
    public void set(State state) {
        if (!states.empty()) {
            states.pop().dispose();
        }

        states.push(state);
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch spriteBatch) {
        // Render the state that was last added
        states.peek().render(spriteBatch);
    }

    @Override
    public void dispose() {
        Iterator<State> iter = states.iterator();

        // Go through any state objects in the slack and dispose them
        while (iter.hasNext()) {
            iter.next().dispose();
        }

        states.empty();
    }

}
