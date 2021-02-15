package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.game.sprites.Helicopter;
import com.mygdx.game.sprites.PilotedHelicopter;

public class HelicopterView {

    private BitmapFont bmf = new BitmapFont();
    protected OrthographicCamera cam;
    private PilotedHelicopter helicopter;
    SpriteBatch batch;

    public HelicopterView(PilotedHelicopter helicopter) {
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
        this.helicopter = helicopter;
    }

    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);
        bmf.draw(batch, helicopter.toString(), 10, 680);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        helicopter.dispose();
    }

}
