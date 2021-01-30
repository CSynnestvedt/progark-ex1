package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

// Button in menu state for choosing which task to display
public class TaskButton {
    public static final int HEIGHT = 40;
    public static final int WIDTH = 70;

    private Rectangle buttonBounds;
    private Texture texture;
    private float x;
    private float y;
    private int taskNumber;

    public TaskButton(float x, float y, int taskNumber) {
        this.x = x;
        this.y = y;
        this.taskNumber = taskNumber;
        buttonBounds = new Rectangle(x, y, WIDTH, HEIGHT);
        String texturePath = "t" + taskNumber + "btn.png";
        this.texture = new Texture(texturePath);
    }

    public boolean isTouched(float x, float y) {
        return buttonBounds.contains(x, y);
    }

    public Vector2 getPos() {
        return new Vector2(x, y);
    }

    public Texture getTexture() {
        return this.texture;
    }

    public int getTaskNumber() {
        return this.taskNumber;
    }

    public void dispose() {
        texture.dispose();
    }
}
