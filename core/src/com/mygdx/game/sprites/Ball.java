package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

public class Ball {

    private static final float INITIALXPOS = (float) (Game.WIDTH/2 - 7.5);
    private static final float INITIALYPOS = (float) (Game.HEIGHT/2 - 7.5);
    private static final int INITIALSPEED = 250;

    private Vector2 pos;
    private Vector2 velocity;
    private Texture ball;
    private Rectangle bounds;

    public Ball() {
        ball = new Texture("ball.png");
        pos = new Vector2(INITIALXPOS, INITIALYPOS);
        velocity = new Vector2(0, 0);
        bounds = new Rectangle(pos.x, pos.y, ball.getWidth(), ball.getHeight());
    }

    public void handleInput(){
        startGame();
    }

    public void reset() {
        pos.x = INITIALXPOS;
        pos.y = INITIALYPOS;
    }

    public void startGame() {
        velocity = new Vector2(INITIALSPEED*getStartingDirection(), INITIALSPEED*getStartingDirection());
    }

    public void update(float dt){
        hitFrame();
        pos.add(velocity.x*dt, velocity.y*dt);
        bounds.setPosition(getPos().x, getPos().y);
    };

    public Vector2 getPos() {
        return pos;
    }

    public Texture getTexture() {
        return ball;
    }

    public void dispose() {
        ball.dispose();
    }

    public void handleCollision(Rectangle paddle) {
        if (bounds.overlaps(paddle)) {
            velocity.x = -velocity.x;
        }
    }

    public boolean outOfPlay(){
        if(getPos().x > Game.WIDTH-ball.getWidth() || getPos().x < 0) {
            return true;
        } else {
            return false;
        }
    }

    private int getStartingDirection(){
        if (Math.random() > 0.5) {
            return 1;
        } else {
            return -1;
        }
    }

    private void hitFrame(){
        if (getPos().y > Game.HEIGHT-ball.getWidth() || getPos().y < 0){
            velocity.y = -velocity.y;
        }
    }


}
