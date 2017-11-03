package code.test3;

/**
 * Created by Administrator on 2017/10/18.
 */
public class Client {
    public static void main(String[] args) {
        Person person = new Person("小明");

        BigTrouserDecorator bigTrouserDecorator = new BigTrouserDecorator(person);//装饰person
        TShirtsDecorator tShirtsDecorator = new TShirtsDecorator(bigTrouserDecorator);//继续装饰

        tShirtsDecorator.show();
    }
}
