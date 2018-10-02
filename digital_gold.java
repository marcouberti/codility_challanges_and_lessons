// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
import java.util.Arrays;

class Solution {
    public int solution(int N, int M, int[] X, int[] Y) {

        // no mines scenario
        if(X.length == 0 || Y.length == 0) return N-1 + M-1;

        // odd gold mines scenario
        if(X.length % 2 != 0) return 0;
        if(Y.length % 2 != 0) return 0;

        int sum = 0;

        // first sort the X and Y arrays
        Arrays.sort(X);
        Arrays.sort(Y);

        int xMin = X[X.length/2 -1];
        int xMax = X[X.length/2];

        int yMin = Y[Y.length/2 -1];
        int yMax = Y[Y.length/2];

        sum += countLinesInBetween(xMin, xMax);
        sum += countLinesInBetween(yMin, yMax);
        return sum;
    }

    private int countLinesInBetween(int min, int max) {
        return max - min;
    }
}
