package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

public abstract class State {

    protected GameStateManager gsm;
    protected Vector2 mouse;
    protected Vector2 touch = new Vector2(0, 0);

    public State(GameStateManager gsm) {
        this.gsm = gsm;
        mouse = new Vector2();
    }

    protected void handleInput() {
        touch.set(Gdx.input.getX(), Game.HEIGHT - Gdx.input.getY());
    }
    public abstract void update(float dt);
}
