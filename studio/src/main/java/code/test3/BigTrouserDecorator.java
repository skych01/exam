package code.test3;

/**
 * Created by Administrator on 2017/10/18.
 */
public class BigTrouserDecorator extends DecoratorPerson {
    public BigTrouserDecorator(Person person) {
        super(person);
    }
    @Override
    public void show(){
        System.out.println("大垮裤");
        person.show();
    }

}
