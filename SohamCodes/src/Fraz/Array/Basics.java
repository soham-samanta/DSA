package Fraz.Array;

import java.util.*;

public class Basics {
    public static void main(String[] args) {
        int[]arr={1,2,3,4,5,6};
        System.out.println(Arrays.toString(arr));

//        print(arr);
//        System.out.println(Arrays.toString(rev(arr)));
        System.out.println(Arrays.toString(printAlternate(arr)));
//        System.out.println(Arrays.toString(dup(arr)));
//        sumProd(arr);
//        System.out.println(isSorted(arr));
//        cntEle(arr);
//        cntUniqueDup(arr);
//        System.out.println(Arrays.toString(twoSum(arr,19)));
//        System.out.println(maxEle(arr));
//        System.out.println(max2nd(arr));
//        System.out.println(min2nd(arr));
//        System.out.println(Arrays.toString(del(arr,3)));



    }

    static void print(int[]arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Value at index "+i+" is "+arr[i]);
        }
    }

    static int[] rev(int[]arr){
        int n = arr.length;
        int[]res = new int[n];
        for (int i = 0; i < n ; i++) {
            res[i] = arr[n-1-i];
        }
        return res;
    }

    static int[] printAlternate(int[]arr){
        int n = arr.length;
        int[]ans = new int[(n+1)/2];
        int j=-1;
        for (int i = 0; i < n; i+=2) {
            ans[++j]=arr[i]; // pre increment (if we use post increment j++ then j=0 not -1)
        }
        return ans;
    }

    static int[] dup(int[]arr){
        return Arrays.copyOf(arr,arr.length);
    }

    static void sumProd(int[]arr){
        int sum=0;
        int prod=1;
        for (int e:arr) {
            sum+=e;
            prod*=e;
        }
        System.out.println(sum+" "+prod);
    }

    static void cntEle(int[]arr){
        Map<Integer,Integer>map = new HashMap<>();
        for(int e:arr){
            map.put(e,map.getOrDefault(e,0)+1);
        }
        for(Map.Entry<Integer,Integer>e:map.entrySet()){
            System.out.println(e.getKey()+" - "+e.getValue()+" times");
        }
    }

    static void cntUniqueDup(int[]arr){
        Map<Integer,Integer>map = new HashMap<>();
        for(int e:arr){
            map.put(e,map.getOrDefault(e,0)+1);
        }
        int uniqueCnt=0;
        int dupCnt=0;
        for(Map.Entry<Integer,Integer>e:map.entrySet()){
            if(e.getValue()>1) dupCnt++;
            else uniqueCnt++;
        }
//        for(int e:map.values()){
//            if(e==1) uniqueCnt++;
//            else dupCnt++;
//        }
        System.out.println("Unique Count = "+uniqueCnt+" | Duplicate Count = "+dupCnt);

    }

    static boolean isSorted(int[]arr){
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]<arr[i-1]){
                return false;
            }
        }
        return true;
    }

    static int[] twoSum(int[]arr , int k){ // 1 2 3 4 5 ,k=6
        int n = arr.length;
        int[]ans=new int[2];

        Set<Integer>set=new HashSet<>();
        for(int e:arr){
            if(set.contains(k-e)){
                ans[0]=e;
                ans[1]=k-e;
                return ans;
            }
            set.add(e);
        }

        return new int[]{};
    }

    static int maxEle(int[]arr){
//        int ans = Integer.MIN_VALUE;
//        for (int j : arr) {
//            ans = Math.max(ans, j);
//        }
//        return ans;
        Arrays.sort(arr);
        return arr[arr.length-1];
    }

    static int max2nd(int[]arr){ // 5,2,3,3,4,1
        int max=Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int e : arr) {
            if (e > max) {
                secondMax = max;
                max = e;
            } else if (e > secondMax && e < max) {
                secondMax = e;
            }
        }
        return secondMax;
    }

    static int min2nd(int []arr){
        int minEle = Integer.MAX_VALUE;
        int secMinEle = Integer.MAX_VALUE;
        for (int e:arr) {
            if(e<minEle){
                secMinEle=minEle;
                minEle=e;
            } else if (e<secMinEle && e>minEle) {
                secMinEle=e;
            }
        }
        return secMinEle;
    }

    static int[] del(int[]arr, int x){
        int[]ans=new int[arr.length-1];
        for (int i = x-1; i < arr.length-1; i++) {
            arr[i]=arr[i+1];
        }
        return Arrays.copyOfRange(arr,0,arr.length-1);
    }





}
