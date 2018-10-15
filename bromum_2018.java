// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int N, int Q, int[] B, int[] C) {
        // write your code in Java SE 8
        HashMap<String, Integer> bucketColorMap = new HashMap<>();
        
        for(int i=0; i<B.length; i++) {
            int where = B[i];
            int color = C[i];
            String key  = where+"_"+color;
            
            int count = 0;
            if(bucketColorMap.containsKey(key)) {
                count = bucketColorMap.get(key) + 1;
            }else {
                count = 1;
            }
            bucketColorMap.put(key, count);
            
            if(count >= Q) return i;
        }
        
        return -1;
    }
}
