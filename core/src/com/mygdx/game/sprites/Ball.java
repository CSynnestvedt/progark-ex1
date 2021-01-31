package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

public class Ball {

    private static final float INITIALXPOS = (float) (Game.WIDTH/2 - 7.5);
    private static final float INITIALYPOS = (float) (Game.HEIGHT/2 - 7.5);
    private static final int INITIALSPEED = 200;

    private float timePassed = 0;

    private Vector2 pos;
    private Vector2 velocity;
    private Texture ball;
    private Rectangle bounds;
    private int bounceCount = 0;

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
        timePassed = 0;
    }

    public void startGame() {
        velocity = new Vector2(INITIALSPEED*getStartingDirection(), INITIALSPEED*getStartingDirection());
    }

    public void update(float dt){
        hitFrame();
        pos.add(velocity.x*dt, velocity.y*dt);
        bounds.setPosition(getPos().x, getPos().y);
        timePassed += dt;
        if (timePassed > 7) {
            System.out.println("Time passed");
            velocity.scl(1.05f);
            timePassed = 0;
        }
        for (int i=1; i<10; i++){
            if (bounceCount==5*i){
                velocity.add(25, 25);
            }
        }
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
            bounceCount++;
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
