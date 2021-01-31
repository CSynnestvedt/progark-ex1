package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.game.sprites.Ball;

public class PongState extends State {

    private Ball ball;
    private Paddle playerPaddle;
    private Paddle opponentPaddle;

    public PongState(GameStateManager gsm) {
        super(gsm);
        ball = new Ball();
        playerPaddle = new Paddle(true);
        opponentPaddle = new OpponentPaddle(false);
        cam.setToOrtho(false, Game.HEIGHT, Game.WIDTH);
    }

    @Override
    protected void handleInput() {
        playerPaddle.handleInput();
    }

    @Override
    public void update(float dt) {
        handleInput();
        ball.update(dt);
        playerPaddle.update(dt);
        opponentPaddle.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor( 0, 0, 0, 1);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(ball.getTexture(), ball.getPos().x, ball.getPos().y);
        batch.draw(playerPaddle.getTexture(), playerPaddle.getPos().x, playerPaddle.getPos().y);
        batch.draw(opponentPaddle.getTexture(), opponentPaddle.getPos().x, opponentPaddle.getPos().y);
        batch.end();
    }

    @Override
    public void dispose() {
        ball.dispose();
        playerPaddle.dispose();
        opponentPaddle.dispose();
    }
}
