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
    protected OrthographicCamera cam;

    private Texture backButton = new Texture("backbtn.png");
    private Rectangle backBounds = new Rectangle(930, 20, backButton.getWidth(), backButton.getHeight());
    private Vector2 touch = new Vector2(0, 0);

    public State(GameStateManager gsm) {
        this.gsm = gsm;
        mouse = new Vector2();
        cam = new OrthographicCamera();
    }

    protected void handleInput() {
        touch.set(Gdx.input.getX(), Game.HEIGHT - Gdx.input.getY());
        if (backBounds.contains(touch))
            gsm.set(new MenuState(gsm));
    }
    public abstract void update(float dt);
    public void render(SpriteBatch batch) {
        batch.draw(backButton, 930, 20);
    }
    public abstract void dispose();

}
