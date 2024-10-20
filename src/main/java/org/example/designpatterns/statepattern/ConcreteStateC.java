package org.example.designpatterns.statepattern;

public class ConcreteStateC extends State {

    public ConcreteStateC(Context context) {
        this.context = context;
    }

    @Override
    public void handle() {
        System.out.println("ConcreteStateC handle");
        context.setState(new ConcreteStateA(context));
    }
}
