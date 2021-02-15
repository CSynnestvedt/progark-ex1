package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;

import com.mygdx.game.sprites.PilotedHelicopter;


public class HelicopterController extends Controller {

    private PilotedHelicopter heli = new PilotedHelicopter(350 , 350);

    public HelicopterController(ControllerManager gsm){
        super(gsm);
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isTouched()) handleInput();
        heli.update(dt, touch, Gdx.input.isTouched());
    }

    public PilotedHelicopter getHelicopter() {
        return this.heli;
    }
}
