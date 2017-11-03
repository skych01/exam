package code.test2.code2;

/**
 * Created by Administrator on 2017/10/18.
 */
public class ConsumeContext {
    private Consume consume;

    //策略模式一般用法
    public ConsumeContext(Consume consume) {
        this.consume = consume;
    }

    /**
     * 与简单工厂模式结合
     * @param type
     */
    public ConsumeContext(int type) {
        switch (type){
            case 1:
                this.consume = new CommonConsume();
                break;
            case 2:
                this.consume = new DiscountConsume(0.7);
                break;
            case 3:
                this.consume = new ReturnConsume(300, 100);
                break;
            default:
                System.out.println("不支持的");
        }
    }

    public  double getResult(double price,int num){
       return consume.getPrice(price, num);
    }

}
