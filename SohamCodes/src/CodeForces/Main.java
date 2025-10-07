package CodeForces;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc=sc.nextInt();
        while(tc-->0){
            int n = sc.nextInt();
            int[]arr=new int[n];
            for (int i = 0; i < n; i++) {
                arr[i]=sc.nextInt();
            }
            int ans = unconventionalPair(arr);
            System.out.println(ans);
        }
    }

    static int bePositive(int[]arr){
        int negCnt=0, zeroCnt=0;
        for (int e : arr) {
            if (e < 0) negCnt++;
            else if (e == 0) zeroCnt++;
        }
        if(negCnt%2==0){
            return zeroCnt;
        }else{
            return zeroCnt+2;
        }
    }

    static int unconventionalPair(int[]arr){
        Arrays.sort(arr);
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < arr.length-1; i+=2) {
            maxPQ.add(arr[i+1]-arr[i]);
        }
        return maxPQ.poll();
    }


}
