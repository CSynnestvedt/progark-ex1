package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.states.ControllerManager;
import com.mygdx.game.states.HelicopterController;
import com.mygdx.game.views.HelicopterView;

public class Game extends ApplicationAdapter {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	private ControllerManager gsm;
	private HelicopterController helicopterState;
	private HelicopterView helicopterView;


	@Override
	public void create () {
		gsm = ControllerManager.getInstance();
		helicopterState = new HelicopterController(gsm);
		gsm.push(helicopterState);
		helicopterView = new HelicopterView(helicopterState.getHelicopter());
	}

	@Override
	public void render () {
		helicopterState.update(Gdx.graphics.getDeltaTime());
		helicopterView.render();
	}
	
	@Override
	public void dispose () {
		helicopterView.dispose();
	}
}
