package code.test8_daili;

/**
 * Created by Administrator on 2017/10/24.
 */
public class Proxy implements Subject {
    private RealSubject realSubject = new RealSubject();



    @Override
    public void giveFlowers() {
        realSubject.giveFlowers();
    }
}
