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
    Vector3 touch = new Vector3();
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
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            // cam.unproject(touch); // Touch and position use different coordinate systems. Must unproject to match them.
            handleMovement(dt, touch);
            updateTexture(); // In case copter is moving the other way now
        }
    }

    private void handleMovement(float dt, Vector3 touch) {
        float travelDistance = SPEED * dt;
        float xSubtractionVal = position.x + TEXTURE_X_CENTRE;
        touch.y = Game.HEIGHT - touch.y;
        distanceRay.set(touch.x, touch.y).sub(xSubtractionVal, position.y + TEXTURE_Y_CENTRE); // Vector from touchpoint to player
        boolean canReachDest = distanceRay.len() <= travelDistance;
        if (canReachDest) {
            boolean touchToLeftOfCopter = touch.x < position.x;
            boolean touchAboveCopter = touch.y > position.y;
            position.x = touchToLeftOfCopter ? touch.x + TEXTURE_X_CENTRE : touch.x - TEXTURE_X_CENTRE;
            position.y = touchAboveCopter ? touch.y + TEXTURE_Y_CENTRE : touch.y - TEXTURE_Y_CENTRE;
        } else {
            distanceRay.nor().scl(travelDistance);
            System.out.println("Scaled " + distanceRay);
            position.x -= distanceRay.x;
            position.y -= distanceRay.y;
        }
    }

}
