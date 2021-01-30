package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Game;

public class PilotedHelicopter extends Helicopter {

    private static int TEXTURE_X_CENTRE;
    private static int TEXTURE_Y_CENTRE;

    Vector2 distanceRay = new Vector2();
    Vector2 touch = new Vector2();
    Camera cam;

    public PilotedHelicopter(int x, int y, Camera cam) {
        super(x, y);
        this.cam = cam;
        TEXTURE_X_CENTRE = texture.getWidth() / 2;
        TEXTURE_Y_CENTRE = texture.getHeight() / 2;
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isTouched()) {
            handleMovement(dt);
            updateTexture(); // In case copter is moving the other way now
        }
    }

    private void handleMovement(float dt) {
        touch.set(Gdx.input.getX(), Gdx.input.getY());
        touch.y = Game.HEIGHT - touch.y; // Flip touch coordinate system so it matches texture coord system
        float travelDistance = SPEED * dt;
        distanceRay.set(touch.x, touch.y).sub(position.x + TEXTURE_X_CENTRE, position.y + TEXTURE_Y_CENTRE); // Vector from touchpoint to helicopter

        boolean canReachDest = distanceRay.len() <= travelDistance;
        boolean touchToLeftOfCopter = touch.x < position.x;
        boolean touchAboveCopter = touch.y > position.y;
        if (canReachDest) {
            velocity.x = touchToLeftOfCopter ? touch.x + TEXTURE_X_CENTRE : touch.x - TEXTURE_X_CENTRE;
            velocity.y = touchAboveCopter ? touch.y + TEXTURE_Y_CENTRE : touch.y - TEXTURE_Y_CENTRE;
        } else {
            distanceRay.nor().scl(travelDistance);
            velocity.x = -distanceRay.x;
            velocity.y = -distanceRay.y;
        }
        // Check for potential wall hits after adding speed
        velocity.x = wallHit() ? 0 : velocity.x;
        velocity.y = roofFloorHit() ? 0 : velocity.y;
        position.add(velocity);
    }

}
