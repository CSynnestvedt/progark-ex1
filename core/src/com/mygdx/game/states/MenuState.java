package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.Helicopter;

import java.awt.Button;

public class MenuState extends State {


    private Texture texture;

    public MenuState(GameStateManager gsm){
        super(gsm);

        texture = new Texture("heli1.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(texture, 500, 350);
        batch.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
