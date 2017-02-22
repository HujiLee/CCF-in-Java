package CCF.r20161203.v2;

/**
 * 256735   炉石传说    12-16 19:50 3.395KB JAVA    错误  90  265ms   151.0MB
 * 最後的10%不知道是漏了考慮什麽情況
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
abstract class Chara{
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

    public boolean iDied(){
        return HP<=0;
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
class Player{
    static Player[] players = new Player[]{new Player(),new Player()};
    static int nowPlay = 0;

    private LinkedList<Chara> charas = new LinkedList<Chara>();
    public Player(){
        this.charas.add(new Hero());
    }
    public void summon(int index,int attack,int health){
        Servant servant = new Servant(health,attack);
        charas.add(index,servant);//as promise,this will be ok
    }
    public void attack(int meIndex,int youIndex){
        charas.get(meIndex).attack(Player.players[1-nowPlay].charas.get(youIndex));
        if(charas.get(meIndex).iDied()&&meIndex>0/*>0 here important!!!*/){
            charas.remove(meIndex);
        }
        if (Player.players[1-nowPlay].charas.get(youIndex).iDied()&&youIndex>0){
            Player.players[1-nowPlay].charas.remove(youIndex);
        }

    }

    public void end(){
        nowPlay = 1-nowPlay;
    }

    public boolean heroDied(){
        return charas.get(0).iDied();
    }

    @Override
    public String toString() {
        String result = charas.get(0).getHP()+"\n";
        int livingServant = charas.size()-1;
        result+=livingServant;
        if(livingServant>0){
            Iterator<Chara> iterator =  charas.iterator();
            Chara temp;
            while (iterator.hasNext()){
                temp = iterator.next();
                if(temp instanceof Servant){
                    result+=" "+temp.getHP();
                }
            }
        }
        return result;
    }
}

public class Main {
    static int stoi(String s){
        return Integer.parseInt(s);
    }
    static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) {
        int howManyRun = Integer.parseInt(scanner.nextLine());
        String[] command;
        for (int i = 1; i <= howManyRun; i++) {
            command = scanner.nextLine().split(" ");
            switch (command[0]) {
                case "summon":
                    Player.players[Player.nowPlay].summon(stoi(command[1]), stoi(command[2]), stoi(command[3]));
                    break;
                case "attack":
                    Player.players[Player.nowPlay].attack(stoi(command[1]), stoi(command[2]));
                case "end":
                    Player.players[Player.nowPlay].end();
                default:
                    break;
            }
        }
        {
            if(Player.players[0].heroDied()){
                System.out.println(-1);
            }else if(Player.players[1].heroDied()){
                System.out.println(1);
            }else {
                System.out.println(0);
            }
        }
        System.out.println(Player.players[0].toString());
        System.out.println(Player.players[1].toString());
    }
}
