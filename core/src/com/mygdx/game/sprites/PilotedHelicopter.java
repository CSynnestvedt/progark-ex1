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
            // cam.unproject(touch); // Touch and position use different coordinate systems. Must unproject to match them.
            handleMovement(dt);
            updateTexture(); // In case copter is moving the other way now
        }
    }

    private void handleMovement(float dt) {
        touch.set(Gdx.input.getX(), Gdx.input.getY());
        float travelDistance = SPEED * dt;
        touch.y = Game.HEIGHT - touch.y;
        distanceRay.set(touch.x, touch.y).sub(position.x + TEXTURE_X_CENTRE, position.y + TEXTURE_Y_CENTRE); // Vector from touchpoint to player
        boolean canReachDest = distanceRay.len() <= travelDistance;
        if (canReachDest) {
            boolean touchToLeftOfCopter = touch.x < position.x;
            boolean touchAboveCopter = touch.y > position.y;
            velocity.x = touchToLeftOfCopter ? touch.x + TEXTURE_X_CENTRE : touch.x - TEXTURE_X_CENTRE;
            velocity.y = touchAboveCopter ? touch.y + TEXTURE_Y_CENTRE : touch.y - TEXTURE_Y_CENTRE;
        } else {
            distanceRay.nor().scl(travelDistance);
            System.out.println("Scaled " + distanceRay);
            velocity.x = -distanceRay.x;
            velocity.y = -distanceRay.y;
        }
        position.add(velocity);
    }

}
