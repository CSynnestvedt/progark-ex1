package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Helicopter;

public class Heli {

    private static final int SPEED = -150;

    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;

    public Heli(int x, int y){
        position = new Vector2(x, y);
        velocity = new Vector2(SPEED, SPEED);
        setTexture(velocity.x);
    }

    public void update(float dt) {

        if (wallHit(position.x)){
            velocity.x = -velocity.x;
            setTexture(velocity.x);
        }
        if (roofFloorHit(position.y)){
            velocity.y = -velocity.y;
        }
        position.add(velocity.x*dt, velocity.y*dt);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose() {
        texture.dispose();
    }

    private boolean wallHit(float pos) {
        return ((pos <= 0 && velocity.x < 0) || (pos >= Helicopter.WIDTH - texture.getWidth() && velocity.x > 0));
    }
    private boolean roofFloorHit(float pos) {
        return ((pos <= 0 && velocity.y < 0) || (pos >= Helicopter.HEIGHT-texture.getHeight() && velocity.y > 0));
    }

    private void setTexture(float speed) {
        if (speed < 0) {
            texture = new Texture("heli1.png");
        }
        else {
            texture = new Texture("heli1rev.png");
        }
    }
}
