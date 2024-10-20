package org.example.designpatterns.statepattern;

/**
 * 状态模式的重要辩证：
 * 从oo思想讲，状态是对象的内在属性，其变化应该由对象的动作而影响，例如电脑的状态变化，应该由开机/关机动作而影响
 * 所以状态修改api不应该直接暴露给外部，故状态模式下，暴露给外部的应该只有动作，不应该有 状态修改api
 * 由于是一个状态变换到另一个状态，状态之间显然不是解耦的，因此对开闭原则支持也不好
 */
public class Main {

    public static void main(String[] args) {
        Context context = new Context();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
    }
}
