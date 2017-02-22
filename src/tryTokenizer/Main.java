package tryTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Created by Administrator on 2016/12/18 0018.
 */
public class Main {
    static StreamTokenizer sTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        sTokenizer.nextToken();
        int i = (int) sTokenizer.nval;
        sTokenizer.nextToken();
        String s = String.valueOf(sTokenizer.nval);
        sTokenizer.nextToken();
        double d = sTokenizer.nval;

    }
}
