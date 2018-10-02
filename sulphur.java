// you can also use imports, for example:
// import java.util.*;

// you can use System.out.println for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
       public int solution(int[] A, int[] B, int[] C) {
        //check if lines case or not
        boolean linee = true;
        int[] doppioni = new int[A.length];
        for(int i=0; i<A.length; i++) {
            if(C[i] != -1) {
                doppioni[C[i]] = doppioni[C[i]] +1;
                if(doppioni[C[i]] > 1) {
                    linee = false;
                    break;
                }
            }
        }

        int cont = 0;
        if(!linee) {
            int[] CARICHI = new int[A.length];
            //caso LINE
            int min_resistance = Integer.MAX_VALUE;
            int sum = 0;
            int parent;boolean esci;
            for(int i=0; i<A.length; i++) {
                    CARICHI[i] = B[i];
                    sum+=B[i];
                    
                    //if weight > durability
                    if(B[i] > A[i]) break;
                    //goup to chain until hook
                    if(C[i] != -1) {
                        parent = C[i];
                        esci = false;
                        while(parent != -1) {
                            CARICHI[parent] = CARICHI[parent]+B[i];
                            if(CARICHI[parent] > A[parent]) {
                                esci = true;
                                break;
                            }
                            parent = C[parent];
                        }
                        if(esci) break;
                        
                    }
                    if(A[i] < min_resistance) min_resistance = A[i];
                    cont++;
            }
        }else {
            int[] min_resistance_left = new int[A.length];
            for(int i=0; i<A.length; i++) {
                min_resistance_left[i] = Integer.MAX_VALUE;
            }
            int[] cur_resistance_left = new int[A.length];
            int[] linearray = new int[A.length];
            int currentline = -1;
            int lines = -1;
            
            for(int i=0; i<A.length; i++) {
                    //if weight > durability
                    if(B[i] > A[i]) break;
                    if(C[i] != -1) {
                        currentline = linearray[C[i]];
                        linearray[i] = currentline;
                        if(min_resistance_left[currentline] < B[i]) break;
                    }else {
                        lines++;   
                        linearray[i] = lines;
                        currentline = lines;
                    }
                    
                    cur_resistance_left[currentline] = A[i]-B[i];
                    
                    if(cur_resistance_left[currentline] < min_resistance_left[currentline]-B[i]) {
                        min_resistance_left[currentline] = cur_resistance_left[currentline];
                    }else min_resistance_left[currentline] = min_resistance_left[currentline] -B[i];
                    cont++;
            }
        }
        return cont;
    }
}
