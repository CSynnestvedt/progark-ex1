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

    private PilotedHelicopter heli = new PilotedHelicopter(350 , 350);

    public HelicopterState(GameStateManager gsm){
        super(gsm);
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.justTouched()) handleInput();
        heli.update(dt, touch, Gdx.input.isTouched());
    }

    public PilotedHelicopter getHelicopter() {
        return this.heli;
    }
}
