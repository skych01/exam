package code.test2.code2;


public class DiscountConsume implements Consume {

    private double discount;

    public DiscountConsume(double discount) {
        this.discount = discount;
    }

    @Override
    public double getPrice(double price, int num) {
        return price * num * discount;
    }
}
