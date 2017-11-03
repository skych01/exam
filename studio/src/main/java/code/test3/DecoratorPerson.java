package code.test3;


/**
 * Created by Administrator on 2017/10/18.
 */
public class DecoratorPerson extends Person {

    protected Person person;

    public DecoratorPerson(Person person) {
        this.person = person;
    }
    @Override
    public void show(){
        if(person!=null){
            person.show();
        }
    }

}
