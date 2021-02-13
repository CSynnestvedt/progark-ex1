package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private static final GameStateManager gsm = new GameStateManager();
    private Stack<State> states;

    private GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }

    public static GameStateManager getInstance() {
        return gsm;
    }
}
