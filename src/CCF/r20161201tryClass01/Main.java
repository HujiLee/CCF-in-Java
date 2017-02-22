package CCF.r20161201tryClass01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 231294   最大波动    11-23 21:42 943B    JAVA    正确  
 * 100 
 * 156ms   
 * 148.4MB
 * @author Administrator
 *
 */

class Run{
    private StreamTokenizer sTokenizer; 
    public void run() throws IOException {
        sTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
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

public class Main {
  public static void main(String[] args) throws IOException {
      (new Run()).run();


    }

}
