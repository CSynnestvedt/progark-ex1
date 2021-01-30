package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PilotedHelicopter extends Helicopter {

    public PilotedHelicopter(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isTouched()) {
            handleMovement(dt, Gdx.input.getX(), Gdx.input.getY());
        }
    }

    private void handleMovement(float dt, int touchX, int touchY) {
        float travelDistance = SPEED * dt;
        Vector2 distanceRay = new Vector2(touchX, touchY).sub(position.x, position.y); // Vector from touchpoint to player
        boolean canReachDest = distanceRay.len() <= travelDistance;
        if (canReachDest) {
            position.x = touchX;
            position.y = touchY;
        }
        else {
            distanceRay.nor().scl(travelDistance);
            position.x += distanceRay.x;
            position.y += distanceRay.y;
        }
    }

}
