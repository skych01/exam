package code.test7;

/**
 * Created by Administrator on 2017/10/23.
 */
public class Client {

    public static void main(String[] args) {
        GameRole gameRole = new GameRole(100,100,100);

        System.out.println(gameRole);

        RoleStatueCareetaker roleStatueCareetaker = new RoleStatueCareetaker();
        roleStatueCareetaker.setGameRole(gameRole);

        gameRole.setVit(5);
        System.out.println(gameRole);

        gameRole = roleStatueCareetaker.getGameRole();
        System.out.println(gameRole);
    }
}
