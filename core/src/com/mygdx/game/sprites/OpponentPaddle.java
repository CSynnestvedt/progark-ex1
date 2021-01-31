package com.mygdx.game.sprites;

public class OpponentPaddle extends Paddle {

    private Ball ball;

    public OpponentPaddle(Ball ball) {
        super(false);
        this.ball = ball;
    }

    @Override
    public void update(float dt) {
        handleMovement(ball.getPos().y, dt);
    }
}
