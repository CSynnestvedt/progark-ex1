package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

public abstract class Paddle {

    protected Texture texture;
    protected Rectangle bounds;

    protected Vector2 position;
    protected Vector2 velocity;

    public Paddle(boolean isLeftPaddle) {
        texture = new Texture("paddle.png");
        float xPos = isLeftPaddle ? 50 - bounds.getWidth() / 2 : Game.WIDTH - 50 + bounds.getWidth() / 2;
        float yPos = Game.WIDTH / 2 - bounds.getHeight() / 2;
        position = new Vector2(xPos, yPos);
        velocity = new Vector2(0, 0);
    }

    public Vector2 getPosition() { return position; }
    public Texture getTexture() { return texture; }
    public abstract void update(float dt);
    public void dispose() { texture.dispose();}

}
