package q20160401;

import java.util.Scanner;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);

    static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        int cont = Integer.parseInt(scanner.nextLine());
        final boolean down = true;
        Boolean[] lines = new Boolean[cont - 1];
        String[] ipt = scanner.nextLine().split(" ");
        for (int i = 0; i < cont - 1; i++) {
            if (stoi(ipt[i])<stoi(ipt[i+1])){
                lines[i] = !down;
            }else {
                lines[i]=down;
            }
        }
        int result = 0;
        for(int i =0 ;i<cont-1-1;i++){
            if(lines[i]!=lines[i+1]){
                result++;
            }
        }
        System.out.println(result);


    }
}
