package CCF.r20161203;

/**
 * 最終得分=60
 * 
 * 參考約定={
 * 前 20% 的评测用例召唤随从的位置都是战场的最右边。
　　前 40% 的评测用例没有 attack 操作。
　　前 60% 的评测用例不会出现随从死亡的情况。
 * }
 * 
 * 可以猜測是隨從死亡后出現了問題
 */

import java.util.Scanner;



abstract class Chara {
    public int getHP() {
        return HP;
    }

    public int getATK() {
        return ATK;
    }

    protected int HP;
    protected int ATK;

    public void attack(Chara another) {
        this.HP -= another.ATK;
        another.HP -= this.ATK;
    }

}

class Hero extends Chara {
    public Hero() {
        this.HP = 30;
        this.ATK = 0;
    }
}

class Servant extends Chara {
    public Servant(int hp, int atk) {
        this.ATK = atk;
        this.HP = hp;
    }
}

class Player {
    public boolean iDied() {
        return charas[0].getHP() <= 0;
    }

    public int getHp() {
        return charas[0].getHP();
    }

    @Override
    public String toString() {
        int howManySer = 7;
        for (int i = 1; i <= 7; i++) {
            if (charas[i] == null) {
                howManySer = i - 1;
                break;
            }
        }
        String reString = String.valueOf(howManySer);
        if (howManySer > 0) {
            for (int i = 1; i <= howManySer; i++) {
                reString += " " + charas[i].getHP();
            }
        }

        return reString;
    }

    static protected int nowRunningPlayer = 0;// 目前是否先手
    static public Player[] players = new Player[] { new Player(), new Player() };
    private Chara[] charas = new Chara[8];

    public Player() {
        this.charas[0] = new Hero();
    }

    public void summon(int index, int attack, int health) {
        Servant servant = new Servant(health, attack);
        if (charas[index] == null) {
            charas[index] = servant;
        } else {
            int nextNullIndex = -1;// 此处假定不会出现强行插入的现象
            for (int i = index + 1; i <= 7; i++) {
                if (charas[i] == null) {
                    nextNullIndex = i;
                    break;
                }
            }
            for (int i = nextNullIndex; i > index; i--) {
                charas[i] = charas[i - 1];
            }
            charas[index] = servant;
        }
    }

    public void attack(int meIndex, int youIndex) {
        this.charas[meIndex].attack(players[1 - nowRunningPlayer].charas[youIndex]);
        // 如果我方死了
        if (this.charas[meIndex].getHP() <= 0) {
            this.die(meIndex);
        }
        if (players[1 - nowRunningPlayer].charas[youIndex].getHP() <= 0) {
            players[1 - nowRunningPlayer].die(youIndex);
        }
    }

    protected void die(int index) {
        assert (charas[index].getHP() <= 0);
        int lastIndex = 7;
        for (int i = index + 1; i <= 7; i++) {
            if (charas[i] == null) {
                lastIndex = i - 1;
                break;
            }
        }
        for (int i = index; i <= lastIndex - 1; i++) {
            charas[i] = charas[i + 1];
        }

        charas[lastIndex] = null;

    }

    public void end() {
        nowRunningPlayer = 1 - nowRunningPlayer;
    }
}

public class Main {
    static int stoi(String s) {
        return Integer.parseInt(s);
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int howManyRun = Integer.parseInt(scanner.nextLine());
        String[] command;
        for (int i = 1; i <= howManyRun; i++) {
            command = scanner.nextLine().split(" ");
            switch (command[0]) {
            case "summon":
                Player.players[Player.nowRunningPlayer].summon(stoi(command[1]), stoi(command[2]), stoi(command[3]));
                break;
            case "attack":
                Player.players[Player.nowRunningPlayer].attack(stoi(command[1]), stoi(command[2]));
            case "end":
                Player.players[Player.nowRunningPlayer].end();
            default:

                break;
            }
        }
        {
            if (Player.players[0].iDied()) {
                System.out.println(-1);
            } else if (Player.players[1].iDied()) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
        System.out.println(Player.players[0].getHp());
        System.out.println(Player.players[0].toString());
        System.out.println(Player.players[1].getHp());
        System.out.println(Player.players[1].toString());

    }

}
