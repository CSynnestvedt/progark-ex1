package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

public abstract class Helicopter {

    protected static final int SPEED = -150;

    protected Texture texture;
    protected Vector2 position;
    protected Vector2 velocity;

    public Helicopter(int x, int y){
        position = new Vector2(x, y);
        velocity = new Vector2(SPEED, SPEED);
        updateTexture();
    }

    public abstract void update(float dt);

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose() {
        texture.dispose();
    }

    protected boolean wallHit(float pos) {
        return ((pos <= 0 && velocity.x < 0) || (pos >= Game.WIDTH - texture.getWidth() && velocity.x > 0));
    }
    protected boolean roofFloorHit(float pos) {
        return ((pos <= 0 && velocity.y < 0) || (pos >= Game.HEIGHT-texture.getHeight() && velocity.y > 0));
    }

    protected void updateTexture() {
        if (velocity.x < 0) {
            texture = new Texture("heli1.png");
        }
        else {
            texture = new Texture("heli1rev.png");
        }
    }
}
