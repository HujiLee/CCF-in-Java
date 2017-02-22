package q20160402;

import java.util.Scanner;

/**
 * Created by Administrator on 2016/12/18 0018.
 */
class CellChart {
    private Boolean[][] chart = new Boolean[15][10];

    CellChart(String[][] strings) {
        assert strings.length == 15;
        for (int i = 0; i <= 14; i++) {
            for (int o = 0; o <= 9; o++) {
                if (strings[i][o].equals("1")) {
                    chart[i][o] = true;
                }
            }
        }
    }

    void fallIn(Plate plate,int index){
        assert (index<=7&&index>=1);
        int[] oppoOffset = new int[4];
        for(int i = 0;i<=3;i++){
            oppoOffset[i] = plate.bottom1(i)+top(i+index-1);
        }
        int min = Integer.MAX_VALUE;
        for(int i:oppoOffset){
            if(i<min){
                min = i;
            }
        }

        fall(plate,index,min);

    }

    private Integer top(int index){
        assert (index>=0&&index<=9);
        for(int i = 0;i<=14;i++){
            if(Boolean.TRUE==chart[i][index]){
                return i;
            }
        }
        return 15;
    }

    private void fall(Plate plate,int index,int fallOffSet){
        int x,y;
        for(int i = 0;i<=3;i++){
            for(int o =0;o<=3;o++){
                x = o+index-1;
                y = (i-4)+fallOffSet-1;
                chart[y][x] = plate.cells[i][o];
            }
        }

    }

}

class Plate {
    final public Boolean[][] cells;
    Plate(String[][] strings) {
        assert strings.length == 4;
        cells = new Boolean[4][4];
        for (int i = 0; i <= 3; i++) {
            for (int o = 0; o <= 3; o++) {
                if (strings[i][o].equals("1")) {
                    cells[i][o] = true;
                }
            }
        }
    }

    Integer bottom1(int index){
        assert (index>=0&&index<=3);
        for(int i = 3;i>=0;i--){
            if(Boolean.TRUE.equals(cells[i][index])){
                return (4-i-1);
            }
        }
        return 4;
    }
}


public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String[][] ipt1 = new String[15][];
        for(int i=0;i<=14;i++){
            ipt1[i] = scanner.nextLine().split(" ");
        }
        CellChart cellChart = new CellChart(ipt1);
        String[][] ipt2 = new String[4][];
        for(int i = 0;i<=3;i++){
            ipt2[i] = scanner.nextLine().split(" ");
        }
        Plate plate = new Plate(ipt2);
        int moveOff = Integer.parseInt(scanner.nextLine());
        cellChart.fallIn(plate,moveOff);

    }
}
/**

 failed 有错误
 */