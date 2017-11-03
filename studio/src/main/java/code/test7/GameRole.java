package code.test7;

/**
 * Created by Administrator on 2017/10/23.
 */
public class GameRole {
    private int vit;//生命
    private int attack;//攻击力
    private int defense;//防御

    public GameRole(int vit, int attack, int defense) {
        this.vit = vit;
        this.attack = attack;
        this.defense = defense;
    }



    @Override
    public String toString() {
        return "GameRole{" +
                "vit=" + vit +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
