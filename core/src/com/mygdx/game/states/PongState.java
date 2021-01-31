package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.Paddle;
import com.mygdx.game.sprites.PlayerPaddle;

public class PongState extends State {

    private int scorePlayer, scoreOpponent;
    private boolean inPlay = false;

    private BitmapFont bmf = new BitmapFont();

    private Ball ball;
    private Paddle playerPaddle;
    private Paddle opponentPaddle;

    public PongState(GameStateManager gsm) {
        super(gsm);
        ball = new Ball();
        playerPaddle = new PlayerPaddle();
        // opponentPaddle = new OpponentPaddle();
        cam.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
    }

    @Override
    protected void handleInput() {
        //playerPaddle.handleInput();
        if (Gdx.input.justTouched()) {
            if (!inPlay) {
                ball.handleInput();
                inPlay = true;
            }
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
        if (inPlay) {
            ball.update(dt);

            ball.handleCollision(playerPaddle.getBounds());
            if (ball.outOfPlay()) {
                goal();
            }
        }
        playerPaddle.update(dt);
        // opponentPaddle.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor( 0, 0, 0, 1);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(ball.getTexture(), ball.getPos().x, ball.getPos().y);
        //batch.draw(opponentPaddle.getTexture(), opponentPaddle.getPos().x, opponentPaddle.getPos().y);
        bmf.draw(batch, toString(), 500, 650);
        batch.draw(playerPaddle.getTexture(), playerPaddle.getPos().x, playerPaddle.getPos().y);
        // batch.draw(opponentPaddle.getTexture(), opponentPaddle.getPos().x, opponentPaddle.getPos().y);
        batch.end();
    }

    @Override
    public void dispose() {
        ball.dispose();
        playerPaddle.dispose();
        //opponentPaddle.dispose();
    }

    public void goal() {
        if (ball.getPos().x <= 0){
            scoreOpponent++;
        } else {
            scorePlayer++;
        }
        ball.reset();
        inPlay = false;
    }

    public String toString(){
        return(scorePlayer + "  -  " + scoreOpponent);
    }
}
