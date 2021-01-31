package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;

public class PlayerPaddle extends Paddle {

    public PlayerPaddle() {
        super(true);
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isTouched()) {
            float yInGameCoords = Game.HEIGHT - Gdx.input.getY();
            handleMovement(yInGameCoords, dt);
        }
    }

    private void handleMovement(float y, float dt) {
        final float MAX_MOVEMENT = SPEED * dt;
        float distance = position.y + bounds.height / 2 - y;
        boolean canReach = Math.abs(distance) <= MAX_MOVEMENT;
        float movement = canReach ? Math.abs(distance) : MAX_MOVEMENT;
        movement = distance > 0 ? -movement : movement;
        move(movement);
    }
}
