package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game;
import com.mygdx.game.sprites.Animation;
import com.mygdx.game.sprites.AnimationHelicopter;
import com.mygdx.game.sprites.Helicopter;
import com.mygdx.game.sprites.PilotedHelicopter;

import java.util.ArrayList;
import java.util.List;


public class HelicopterState extends State {

    private List<Helicopter> helicopterArray = new ArrayList<Helicopter>();
    private BitmapFont bmf = new BitmapFont();


    public HelicopterState(GameStateManager gsm, Helicopter ... helicopters){
        super(gsm);
        for (Helicopter helicopter : helicopters) {
            helicopterArray.add(helicopter);
        }

        cam.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.justTouched()) handleInput();
        for (int i = 0; i < helicopterArray.size()-1; i++) {
            if (helicopterArray.get(i) instanceof AnimationHelicopter) {
                for (int j = i+1; j < helicopterArray.size(); j++) {
                    if (helicopterArray.get(j) instanceof AnimationHelicopter) {
                        AnimationHelicopter iHelicopter = (AnimationHelicopter) helicopterArray.get(i);
                        AnimationHelicopter jHelicopter = (AnimationHelicopter) helicopterArray.get(j);
                        if (iHelicopter.collision(jHelicopter.getBounds())){
                            iHelicopter.changeDirection(jHelicopter);
                        }
                    }
                }
            }
        }
        for (Helicopter helicopter : helicopterArray) {
            helicopter.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        super.render(batch);
        for (Helicopter helicopter: helicopterArray) {
            if (helicopter instanceof AnimationHelicopter)
                batch.draw(((AnimationHelicopter) helicopter).getTextureRegion(), helicopter.getPosition().x, helicopter.getPosition().y);
            else
                batch.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);
            if (helicopter instanceof PilotedHelicopter) {
                bmf.draw(batch, helicopter.toString(), 10, 680);
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        for (Helicopter helicopter: helicopterArray) {
            helicopter.dispose();
        }
    }
}
