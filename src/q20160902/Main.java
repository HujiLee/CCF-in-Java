package q20160902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Vector;

class Pai {
    @Override
    public String toString() {
        String reString=  ""+isBought[0];
        for(int i =1;i<=4;i++){
            reString+=">"+isBought[i];
        }

        return reString;
    }

    public static final boolean F = false;
    private int initIndex;
    private boolean[] isBought = new boolean[] { F, F, F, F, F };
    private int empCount = 5;

    /**
     * initIndex = 1/6/11/16
     * @param initIndex
     */
    public  Pai(int initIndex) {
        this.initIndex = initIndex;
    }
    public int getEmpCount() {
        return empCount;
    }


    public int buySingle() {
        for(int i =0;i<=4;i++){
            if(isBought[i]==F){
                buy(i+initIndex);
                return i+initIndex;
            }
        }
        return 0;
    }
    /**
     *
     *
     * @param index
     *            = from 1 to 5/6 to 10/...
     * @return
     */
    public Pai buy(int index) {
        isBought[index - initIndex] = !F;
        empCount--;
        return this;
    }

    /**
     *
     * @param fromIndex
     *            = from 1 to 5/6 to 10/...
     * @param howMany
     * @return
     */
    public Pai buy(int fromIndex, int howMany) {
        for (int i = fromIndex; i <= fromIndex + howMany - 1; i++) {
            buy(i);
        }
        return this;
    }

    /**
     *
     *
     * @param from
     * @return
     */
    private int calcHoeManyConti(int from) {
        int count = 0;
        for (int i = from; i <= 4; i++) {
            if (isBought[i] == F) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /*
     * @return
     */
    public int[] maxContinue() {
        int[] result = new int[] { 0, 0 };
        if (empCount == 0) {
            return result;
        }

        int i = 0;
        do {
            if (isBought[i] == F) {
                int con = calcHoeManyConti(i);
                if (con > result[0]) {
                    result[0] = con;
                    result[1] = i + initIndex;
                }
                i += con;
            }else {
                i++;
            }
        } while (i <= 4);

        return result;
    }

}

class Chexiang{
    private static Chexiang singleI = null;
    public static Chexiang single() {
        if(singleI==null){
            singleI = new Chexiang();
            for(int i =0;i<=19;i++){
                singleI.pais[i] = new Pai(i*5+1);
            }
        }
        return singleI;
    }

    private Pai[] pais = new Pai[20];
    private Chexiang(){}
    private Chexiang buyOne(Vector<Integer> seats) {
        for(int i = 0;i<=19;i++){
            if(pais[i].getEmpCount()>0){
                seats.add(pais[i].buySingle());
                break;
            }
        }
        return this;
    }

    private static void printSeats(Vector<Integer> seats){
        int length = seats.size();
        for(int i =0;i<length;i++){
            if(i>0){
                System.out.print(" ");
            }
            System.out.print(seats.get(i));
        }
        System.out.println();

    }

    public Chexiang buy(int howMany) {
        Vector<Integer> seats = new Vector<Integer>();
        if(howMany==1){
            buyOne(seats);
        }else {
            boolean canBuyConti = false;
            for(int i =0;i<=19;i++){
                if(pais[i].getEmpCount()>=howMany){
                    int[] conti = pais[i].maxContinue();
                    if(conti[0]>=howMany){
                        pais[i].buy(conti[1], howMany);
                        for(int j = 0;j<howMany;j++){
                            seats.add(conti[1]+j);
                        }
                        canBuyConti = true;
                        break;
                    }
                }
            }
            if(!canBuyConti){
                for(int i =1;i<=howMany;i++){
                    buyOne(seats);
                }
            }
        }
        printSeats(seats);
        return this;
    }


}

public class Main {
    static StreamTokenizer sTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        Chexiang chexiang = Chexiang.single();
        sTokenizer.nextToken();
        int count =(int)sTokenizer.nval;
        for(int i =1;i<=count;i++){
            sTokenizer.nextToken();
            chexiang.buy((int)sTokenizer.nval);
        }


    }

}
