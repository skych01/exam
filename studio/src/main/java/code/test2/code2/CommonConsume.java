package code.test2.code2;

/**
 * Created by Administrator on 2017/10/18.
 */
public class CommonConsume implements Consume {
    @Override
    public double getPrice(double price, int num) {
        return price * num;
    }
}
