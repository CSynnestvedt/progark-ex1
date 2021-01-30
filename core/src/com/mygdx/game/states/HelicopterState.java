package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.game.sprites.AutoHelicopter;
import com.mygdx.game.sprites.Helicopter;

public class HelicopterState extends State {

    private Helicopter helicopter;


    public HelicopterState(GameStateManager gsm, Helicopter helicopter){
        super(gsm);
        this.helicopter = helicopter;
        cam.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
    }

    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);
        batch.end();
    }

    @Override
    public void dispose() {
        helicopter.dispose();
    }
}
