package code.test4;

import java.io.IOException;
import java.util.Date;


/**
 * Created by Administrator on 2017/10/18.
 */
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Prototype prototype = new Prototype();
        Properties properties = new Properties("pro", 12);
        prototype.setProperties(properties);
        prototype.setName("哈哈");

        Prototype prototype1 = prototype.clone();
        prototype1.setProperties(new Properties("123",2));
        Prototype prototype2 = prototype.clone();



        System.out.println(prototype);
        System.out.println(prototype1);
        System.out.println(prototype2);

    }
}
