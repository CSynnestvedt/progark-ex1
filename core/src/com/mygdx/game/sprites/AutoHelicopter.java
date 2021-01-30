package com.mygdx.game.sprites;

public class AutoHelicopter extends Helicopter {

    public AutoHelicopter(int x, int y) {
        super(x, y);
    }

    public void update(float dt) {
        if (wallHit()){
            velocity.x = -velocity.x;
            updateTexture();
        }
        if (roofFloorHit()){
            velocity.y = -velocity.y;
        }
        position.add(velocity.x*dt, velocity.y*dt);
    }
}
