package code.test8_daili;

/**
 * 真实的请求对象
 */
public class RealSubject implements Subject {
    @Override
    public void giveFlowers() {
        System.out.println("送鲜花");
    }
}
