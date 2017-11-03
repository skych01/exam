package code.test3;

public class TShirtsDecorator extends DecoratorPerson {

    public TShirtsDecorator(Person person) {
        super(person);
    }

    @Override
    public void show(){
        System.out.println("体恤");
        person.show();
    }

}
