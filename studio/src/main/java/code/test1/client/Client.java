package code.test1.client;

import code.test1.operation.Operation;
import code.test1.operation.OperationFactory;
import sun.applet.Main;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/10/16.
 */
public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("欢迎进入计算系统 请输入 数字 a");
        double numA = scanner.nextDouble();
        System.out.println("请输入 运算符");
        int operator = scanner.nextInt();
        System.out.println(" 请输入 数字 b");
        double numB = scanner.nextDouble();

        Operation operation= OperationFactory.createOperation(operator);
        double result = operation.getResult(numA, numB);
        System.out.println(result);
    }
}
