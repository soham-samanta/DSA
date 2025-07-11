import java.util.*;

public class Hashing {
    public static void main(String[] args) {

    }

    public static int cntDistinct(int[]arr){
        Set<Integer>set= new HashSet<>();
        for(int e:arr){
            set.add(e);
        }
        return set.size();
    }

    public static void freq(int[]arr){
        Map<Integer,Integer>map=new HashMap<>();
        for (int e:arr) {
            if(map.containsKey(e)){
                map.put(e,map.get(e)+1);
            }else{
                map.put(e,1);
            }
        }
        for(Map.Entry<Integer,Integer>e:map.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }

        /*  Short Code

            for (int e : arr) {
                map.put(e, map.getOrDefault(e, 0) + 1);
            }
            map.forEach((k, v) -> System.out.println(k + " " + v));

         */
    }

    public boolean hasArrayTwoCandidates(int[] arr, int x){
        Set<Integer>set=new HashSet<>();
        for(int e:arr){
            if(set.contains(x-e)){
                return true;
            }
            set.add(e);
        }
        return false;
    }
    public int[] twoSumIndices(int[] arr, int x) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int c = x - arr[i];
            if (map.containsKey(c)) {
                return new int[]{map.get(c), i};
            }
            map.put(arr[i], i);
        }
        return new int[]{}; // or return null if no pair found
    }


    // Logic -> prefix sum - if(sum is repeated then present)
    public static int subArrayWithZeroSum(int[]arr){ // how many subArrays ?
        Set<Integer>set = new HashSet<>();
        int cs = 0;
        int cnt = 0;
        set.add(0);
        for(int e:arr){
            cs+=e;
            if(set.contains(cs)){
                cnt++;
            }
            set.add(cs);
        }
        return cnt;
    }

    public static int subArraySumCnt(int[]arr, int k){
        Set<Integer>set = new HashSet<>();
        int cs = 0;
        int cnt = 0;
        set.add(0);
        for(int e:arr){
            cs+=e;
            if(set.contains(cs-k)){ //
                cnt++;
            }
            set.add(cs);
        }
        return cnt;
    }
    /*** imp ***/
    public static ArrayList<Integer> subArraySum(int[] arr, int n, int k) {
        Map<Long, Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        long cs = 0;

        for (int i = 0; i < n; i++) {
            cs += arr[i];

            if (cs == k) {
                res.add(1);
                res.add(i + 1);
                return res;
            }

            if (map.containsKey(cs - k)) {
                res.add(map.get(cs - k) + 2); // +1 for next index, +1 for 1-based
                res.add(i + 1);
                return res;
            }

            map.put(cs, i);
        }

        res.add(-1);
        return res;
    }

    public int largestSubArray0sum(int[] arr){
        Map<Integer,Integer>map = new HashMap<>();
        int cs=0;
        int maxLen = 0;
        for(int i = 0; i < arr.length; i++) {
            cs+=arr[i];
            if(map.containsKey(cs)){
                maxLen=Math.max(i-map.get(cs),maxLen);
            }else{
                map.put(cs,i);
            }
        }
        return maxLen;
    }






}
