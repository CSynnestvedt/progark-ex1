package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.OpponentPaddle;
import com.mygdx.game.sprites.Paddle;
import com.mygdx.game.sprites.PlayerPaddle;

public class PongState extends State {

    private static final int MAXSCORE = 21;

    private int scorePlayer, scoreOpponent;
    private boolean inPlay = false;
    private boolean gameOver;

    private BitmapFont bmf = new BitmapFont();
    private float textWidth;
    private float goTextWidth;
    private Texture bg = new Texture("bg.png");


    private Ball ball;
    private Paddle playerPaddle;
    private Paddle opponentPaddle;

    public PongState(GameStateManager gsm) {
        super(gsm);
        ball = new Ball();
        playerPaddle = new PlayerPaddle();
        opponentPaddle = new OpponentPaddle(ball);
        cam.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
        bmf.getData().setScale(2);
        GlyphLayout layout = new GlyphLayout(bmf, toString());
        textWidth = layout.width;
        GlyphLayout goLayout = new GlyphLayout(bmf, handleGameOver());
        goTextWidth = goLayout.width;
        gameOver = false;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            super.handleInput();
            if (gameOver) {
                gsm.set(new MenuState(gsm));
            }
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
            ball.handleCollision(opponentPaddle.getBounds());
            if (ball.outOfPlay()) {
                goal();
            }
        }
        playerPaddle.update(dt);
        opponentPaddle.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(bg, 0, 0);
        super.render(batch);
        batch.draw(ball.getTexture(), ball.getPos().x, ball.getPos().y);
        bmf.draw(batch, toString(), 500 - textWidth / 2, 650);
        if(gameOver) {
            bmf.draw(batch, handleGameOver(), 500 - goTextWidth /2, 350);
        }
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

    public void goal() {
        if (ball.getPos().x <= 0){
            scoreOpponent++;
            if(scoreOpponent >= MAXSCORE) {
                gameOver = true;
            }
        } else {
            scorePlayer++;
            if(scorePlayer >= MAXSCORE) {
                gameOver = true;
            }
        }
        ball.reset();
        inPlay = false;
    }

    public String toString(){
        return(scorePlayer + "     " + scoreOpponent);
    }

    private String handleGameOver(){
        if (scorePlayer >= MAXSCORE) {
            return ("Game Over!\nYou win, tap anywhere to go back to main menu");
        }
        return ("Game Over!\nYou lose, tap anywhere to go back to main menu");
    }
}
