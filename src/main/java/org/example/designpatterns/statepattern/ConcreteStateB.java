package org.example.designpatterns.statepattern;

public class ConcreteStateB extends State {

    public ConcreteStateB(Context context) {
        this.context = context;
    }

    @Override
    public void handle() {
        System.out.println("ConcreteStateB handle");
        context.setState(new ConcreteStateC(context));
    }
}
