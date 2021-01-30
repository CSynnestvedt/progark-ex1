package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationHelicopter extends AutoHelicopter {

    private Animation animation;

    public AnimationHelicopter(int x, int y) {
        super(x, y);
        TextureRegion textureRegion = new TextureRegion(new Texture("animation.png"));
        animation = new Animation(textureRegion, 4, 0.4f);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        animation.update(dt);
    }

    @Override
    protected void updateTexture() {
        animation.toggleFlip();
    }

    public TextureRegion getTextureRegion() { // Optimally should've been texture but this doesn't work
        return this.animation.getFrame();
    }
}
