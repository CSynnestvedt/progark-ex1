package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.sprites.Helicopter;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.HelicopterState;
import com.mygdx.game.states.MenuState;
import com.mygdx.game.views.HelicopterView;

public class Game extends ApplicationAdapter {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	private GameStateManager gsm;
	private HelicopterState helicopterState;
	private HelicopterView helicopterView;


	@Override
	public void create () {
		gsm = GameStateManager.getInstance();
		gsm.set(new HelicopterState(gsm));
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
