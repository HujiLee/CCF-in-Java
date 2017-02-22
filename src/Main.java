import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        for(int i =1;i<=0;i++){
            System.out.println(i);
        }

        // write your code here
        Set<Long> hashSet = new HashSet<Long>();
        hashSet.add(3L);
        hashSet.add(4L);
        hashSet.add(2L);
        hashSet.add(1L);
        Long number;
        number = Collections.max(hashSet);
        System.out.println("hashSet容器中的最大值是：" + number);
    }
}
/**
 *  * @from www.everycoding.com
 *  * @Description:Java Set容器找出其中元素的最大值
 *  
 */



