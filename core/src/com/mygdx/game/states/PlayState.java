package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Helicopter;
import com.mygdx.game.sprites.Heli;

public class PlayState extends State {

    private Heli heli;


    public PlayState(GameStateManager gsm){
        super(gsm);
        heli = new Heli(500 , 350);
        cam.setToOrtho(false, Helicopter.WIDTH, Helicopter.HEIGHT);
    }

    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        heli.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(heli.getTexture(), heli.getPosition().x, heli.getPosition().y);
        batch.end();
    }

    @Override
    public void dispose() {
        heli.dispose();
    }
}
