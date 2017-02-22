package CCF.r20161201AC;

/**
 * 231197   最大波动    11-23 20:02 
 * 836B    JAVA    正确  100 
 * 140ms   
 * 148.4MB
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

    static StreamTokenizer sTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws IOException {
        sTokenizer.nextToken();
        int count = (int)sTokenizer.nval;
        sTokenizer.nextToken();
        int num1 = (int)sTokenizer.nval;
        int num2;
        int maxDelta = Integer.MIN_VALUE;
        for(int i =1;i<=count-1;i++){
           sTokenizer.nextToken();
           num2 = (int)sTokenizer.nval;
           if(Math.abs(num1-num2)>maxDelta){
               maxDelta = Math.abs(num1-num2);
           }
           num1 = num2;
        }
        System.out.println(maxDelta);

    }

}
