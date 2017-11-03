package code.test2.code2;

/**
 * Created by Administrator on 2017/10/18.
 */
public class ReturnConsume implements Consume {
    private double full;
    private double revert;

    public ReturnConsume(double full, double revert) {
        this.full = full;
        this.revert = revert;
    }

    @Override
    public double getPrice(double price, int num) {
        double resultPrice=price * num;
        if(price*num < full){
            return resultPrice;
        }else {
            return resultPrice - revert;
        }
    }
}
