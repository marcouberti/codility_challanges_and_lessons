import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class Solution {

    @Test
    public void testSolution() {
        int[] A = new int[] {0, 0, 2, 1, 8, 8, 2, 0};
        int[] B = new int[] {8, 5, 2, 4, 0, 0, 0, 2};
        assertEquals(31, solution(A, B));

        A = new int[] {1, 1, 2, 4, 3};
        B = new int[] {2, 2, 2, 3, 2};
        assertEquals(3, solution(A, B));

        A = new int[] {1, 1};
        B = new int[] {1, 0};
        assertEquals(-1, solution(A, B));
    }


    /**
     *
     * @param A stones array configuration
     * @param B stones array configuration
     * @return count minimum steps required to get from A -> B
     *
     * The key point here is to understand how to solve the problem logically: the idea is to do two
     * pass of the delta array, where in the first pass we move the stones we are sure that will be
     * moved to the right, and in the second we move the stones we are sure that will be
     * moved to the left. How to be sure? Well, if we have more exceeding stones than holes to the left
     * we are sure the exceeding ones have to be moved to the right and vice versa.
     * During the pass we move the stones step by step (1 or 2 indices) until they are all placed.
     *
     * Pay attention to use long instead of int to avoid errors with big numbers. 
     */
    public int solution(int[] A, int[] B) {

        long count = 0;
        Integer[] DELTA = new Integer[A.length];
        int sum = 0;

        for(int i=0; i<A.length; i++) {
            DELTA[i] = A[i] - B[i];
            sum += DELTA[i];
        }

        if(sum != 0) return -1;

        // first pass: move exceeding stones to the right
        count += pass(DELTA);

        // second pass: move exceeding stones to the left
        reverseArray(DELTA);
        count += pass(DELTA);

        return (int)(count % 1000000007);
    }

    private long pass(Integer[] DELTA) {
        long count = 0;
        int exceedingStones = 0;
        for(int i=0; i<DELTA.length; i++) {
            if(exceedingStones + DELTA[i] > 0) {
                int stones = exceedingStones + DELTA[i];
                if(i+1 < DELTA.length && DELTA[i+1] < 0) {
                    if(stones + DELTA[i+1] >= 0) {
                        count += -DELTA[i+1];
                        stones += DELTA[i+1];
                        DELTA[i] += DELTA[i+1];
                        DELTA[i+1] = 0;
                    }else {
                        DELTA[i] -= stones;
                        DELTA[i+1] += stones;
                        count += stones;
                        stones = 0;
                    }
                }
                if(stones > 0 && i+2 < DELTA.length) {
                    DELTA[i] -= stones;
                    DELTA[i+2] += stones;
                    count += stones;
                }
                exceedingStones = 0;
            }else {
                exceedingStones += DELTA[i];
            }
        }
        return count;
    }

    private void reverseArray(Integer[] arr) {
        List<Integer> list = Arrays.asList(arr);
        Collections.reverse(list);
        list.toArray(arr);
    }
}
