package code.test2.code1;


import java.util.Scanner;

/**
 * 收银系统 客户端代码 版本1.0
 */
public class client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入单价(单位 元)");
        double price = scanner.nextDouble();

        System.out.println("请输入数量");
        double num = scanner.nextDouble();

        double result = price * num;

        System.out.println("最终价格为： "+ result +" 元");

    }
}
/**
 * 根据数量 和单价计算 可以完成 此时如果 举办打折促销
 */
