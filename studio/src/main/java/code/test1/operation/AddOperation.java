package code.test1.operation;

/**
 * 加法运算
 */
public class AddOperation  extends Operation {
    @Override
    public double getResult(double a, double b) {
        return a + b;
    }
}
