package code.test2.code2;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/10/18.
 */
public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入单价(单位 元)");
        double price = scanner.nextDouble();

        System.out.println("请输入数量");
        int num = scanner.nextInt();

        //普通策略模式
        //ConsumeContext context = new ConsumeContext(new DiscountConsume(0.7));

        //与简单工厂结合
        System.out.println("请输入消费类型 1:...,2:...,3:...");
        int type = scanner.nextInt();
        ConsumeContext context = new ConsumeContext(type);

        double result = context.getResult(price, num);
        System.out.println("最终价格为： "+ result +" 元");
    }
}
