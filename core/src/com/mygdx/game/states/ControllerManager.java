package com.mygdx.game.states;

import java.util.Stack;

public class ControllerManager {

    private static final ControllerManager gsm = new ControllerManager();
    private Stack<Controller> states;

    private ControllerManager() {
        states = new Stack<Controller>();
    }

    public void push(Controller state) {
        states.push(state);
    }

    public void set(Controller state){
        states.pop();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public static ControllerManager getInstance() {
        return gsm;
    }
}
