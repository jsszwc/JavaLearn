package org.example.designpatterns.statepattern;

public class ConcreteStateA extends State {

    public ConcreteStateA(Context context) {
        this.context = context;
    }

    @Override
    public void handle() {
        System.out.println("ConcreteStateA handle");
        context.setState(new ConcreteStateB(context));
    }
}
