package org.example.designpatterns.statepattern;

/**
 * 状态模式中使用抽象类表示状态更好一点
 */
public abstract class State {

    Context context;

    public abstract void handle();
}
