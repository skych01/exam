package code.test6.state;

import code.test6.Context;

/**
 * Created by Administrator on 2017/10/23.
 */
public class ConcreteState1 implements IState {
    @Override
    public void handle(Context context) {
        System.out.println("concreteState1 handle ...");
        context.setiState(new ConcreteState2());
        context.request();
    }
}
