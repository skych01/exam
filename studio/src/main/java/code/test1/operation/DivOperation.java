package code.test1.operation;

/**
 * 除法运算
 */
public class DivOperation extends  Operation {
    @Override
    public double getResult(double a, double b) {
        if(b==0) {
            try {
                throw new Exception("除数不能为0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return a / b;

    }
}
