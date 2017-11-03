package code.test6;

import code.test6.state.ConcreteState1;

/**
 * Created by Administrator on 2017/10/23.
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteState1());

        context.request();
        context.request();
        context.request();
        context.request();


    }
}
