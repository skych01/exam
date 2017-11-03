package code.test6.state;

import code.test6.Context;


public class ConcreteState2 implements IState {
    @Override
    public void handle(Context context) {
        System.out.println("concreteState2 handle ...");
    }
}
