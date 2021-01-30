package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game;
import com.mygdx.game.sprites.TaskButton;

public class MenuState extends State {

    private Array<TaskButton> taskButtons = new Array<>();
    private Array<State> states = new Array<>();

    public MenuState(GameStateManager gsm) {
        super(gsm);
        final int BUTTON_PADDING = 40;
        final int FIRST_BUTTON_X = 300;
        for (int i = 1; i < 5; i++) {
            float x = FIRST_BUTTON_X + (i - 1) * (TaskButton.WIDTH + BUTTON_PADDING);
            float y = Game.HEIGHT / 2 - TaskButton.HEIGHT / 2; // To center the buttons
            TaskButton btn = new TaskButton(x, y, i);
            taskButtons.add(btn);
        }
        states.add(new HelicopterState(gsm)); // Add more states here as they are created
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            for (int i = 0; i < taskButtons.size; i++) {
                TaskButton btn = taskButtons.get(i);
                if (btn.isTouched(Gdx.input.getX(), Gdx.input.getY()))
                    gsm.set(states.get(btn.getTaskNumber() - 1));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        for (TaskButton btn : taskButtons) {
            batch.draw(btn.getTexture(), btn.getPos().x, btn.getPos().y);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        for (TaskButton btn : taskButtons) {
            btn.dispose();
        }
    }
}
