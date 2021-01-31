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
}
