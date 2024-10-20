package org.example.designpatterns.statepattern;

public class Context {

    private State state;

    public Context() {
        state = new ConcreteStateA(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void handle() {
        state.handle();
    }
}
