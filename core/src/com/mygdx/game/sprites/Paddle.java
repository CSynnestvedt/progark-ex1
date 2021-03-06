package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

public abstract class Paddle {

    protected int SPEED = 200; // Probably needs to be changed

    protected Texture texture;
    protected Rectangle bounds;

    protected Vector2 position;
    protected Vector2 velocity;

    public Paddle(boolean isLeftPaddle) {
        texture = new Texture("paddle.png");
        float xPos = isLeftPaddle ? 50 - texture.getWidth() / 2 : Game.WIDTH - 50 + texture.getWidth() / 2;
        float yPos = Game.WIDTH / 2 - texture.getHeight() / 2;
        position = new Vector2(xPos, yPos);
        bounds = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
        velocity = new Vector2(0, 0);
    }

    public void move(float ySpeed) {
        velocity.set(0, ySpeed);
        if (isBoundaryHit()) {
            velocity.set(0, 0);
        }
        position.add(velocity);
        bounds.setPosition(position.x, position.y);
    }

    protected boolean isBoundaryHit() {
        boolean roofHit = bounds.y + bounds.height >= Game.HEIGHT;
        boolean isMovingDown = velocity.y < 0;
        boolean floorHit = bounds.y <= 0;
        return (roofHit && (!isMovingDown)) || (floorHit && isMovingDown);
    }

    protected void handleMovement(float y, float dt) {
        final float MAX_MOVEMENT = SPEED * dt;
        float distance = position.y + bounds.height / 2 - y;
        boolean canReach = Math.abs(distance) <= MAX_MOVEMENT;
        float movement = canReach ? Math.abs(distance) : MAX_MOVEMENT;
        movement = distance > 0 ? -movement : movement;
        move(movement);
    }

    public Vector2 getPos() { return position; }
    public Texture getTexture() { return texture; }
    public abstract void update(float dt);
    public void dispose() { texture.dispose();}
    public Rectangle getBounds(){ return bounds; }

}
