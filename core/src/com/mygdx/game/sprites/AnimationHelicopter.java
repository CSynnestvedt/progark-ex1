package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

public class AnimationHelicopter extends AutoHelicopter {

    private Animation animation;
    private Rectangle bounds;
    private Vector2 velocity;

    public AnimationHelicopter(Vector2 pos, Vector2 velocity) {
        super((int)pos.x, (int)pos.y);
        this.velocity = velocity;
        TextureRegion textureRegion = new TextureRegion(new Texture("animation.png"));
        animation = new Animation(textureRegion, 4, 0.4f);
        if(velocity.x > 0) {
            updateTexture();
        }
        bounds = new Rectangle(pos.x, pos.y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void update(float dt) {
        if (wallHit()){
            velocity.x = -velocity.x;
            updateTexture();
        }
        if (roofFloorHit()){
            velocity.y = -velocity.y;
        }
        position.add(velocity.x*dt, velocity.y*dt);
        animation.update(dt);
        bounds.setPosition(position.x, position.y);
    }

    @Override
    protected void updateTexture() {
        animation.toggleFlip();
    }

    public TextureRegion getTextureRegion() { // Optimally should've been texture but this doesn't work
        return this.animation.getFrame();
    }

    public boolean collision(Rectangle helicopter) {
        return helicopter.overlaps(bounds);
    }

    public void setXVelocity(float xvelocity){
        this.velocity.x = xvelocity;
    }

    public void setYVelocity(float yVelocity) {
        this.velocity.y = yVelocity;
    }

    public float getXVelocity() {
        return velocity.x;
    }

    public float getYVelocity(){
        return velocity.y;
    }


    public void changeDirection(AnimationHelicopter helicopter) {
        if ((velocity.x > 0 && helicopter.velocity.x > 0 && position.x > helicopter.position.x) || (velocity.x < 0 && helicopter.velocity.x < 0 && position.x < helicopter.position.x)) {
            helicopter.setXVelocity(-helicopter.getXVelocity());
            helicopter.updateTexture();
        }
        else if ((velocity.x > 0 && helicopter.velocity.x < 0) || (velocity.x < 0 && helicopter.velocity.x > 0)) {
            velocity.x = -velocity.x;
            helicopter.setXVelocity(-helicopter.getXVelocity());
            updateTexture();
            helicopter.updateTexture();
        }
        else if ((velocity.x > 0 && helicopter.velocity.x > 0 && position.x < helicopter.position.x) || (velocity.x < 0 && helicopter.velocity.x < 0 && position.x > helicopter.position.x)){
            velocity.x = -velocity.x;
            updateTexture();
        }
        if ((velocity.y > 0 && helicopter.velocity.y > 0 && position.y > helicopter.position.y) || (velocity.y < 0 && helicopter.velocity.y < 0 && position.y < helicopter.position.y)) {
            helicopter.setYVelocity(-helicopter.getYVelocity());
        }
        else if ((velocity.y > 0 && helicopter.velocity.y < 0) || (velocity.y < 0 && helicopter.velocity.y > 0)) {
            velocity.y = -velocity.y;
            helicopter.setYVelocity(-helicopter.getYVelocity());
        }
        else if ((velocity.y > 0 && helicopter.velocity.y > 0 && position.y < helicopter.position.y) || (velocity.y < 0 && helicopter.velocity.y < 0 && position.y > helicopter.position.y)) {
            velocity.y = - velocity.y;
        }

    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    protected boolean wallHit() {
        return ((position.x<= 0 && velocity.x < 0) || (position.x >= Game.WIDTH - texture.getWidth() && velocity.x > 0));
    }

    @Override
    protected boolean roofFloorHit() {
        return ((position.y <= 0 && velocity.y < 0) || (position.y >= Game.HEIGHT-texture.getHeight() && velocity.y > 0));
    }
}
