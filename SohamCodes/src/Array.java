import java.util.*;

public class Array {
    public static void main(String[] args) {

        ArrayList<Integer>a=new ArrayList<>(Arrays.asList(1, 3, 2, 4, 5));
        System.out.println(subUnsort(a));

//        int[]arr={3,4,-1,-2,1};
//        System.out.println(firstMissingPositive(arr));


//        int[]arr={1,2,1,3,6,5};
//        findNumbers(arr);

//        int[]arr={1,2,3,4};
//        System.out.println(findPair(arr));

//        int[]arr = {7, 3, 2, 4, 9, 12, 56};
//        System.out.println(chocolateDistribution(arr,3));

//        int[]arr={10, 19, 1, 30, 32};
//        System.out.println(maxProfit2ptr(arr));

//        int[]arr = {1,2,3,4,5};
//        int[]b = {2,3};
//
//        rotateK(arr,3);
//
////        int[]ans= new int[a.length*2];
////        for (int i = 0; i < a.length; i++) {
////            ans[i] = a[i];                 // Copy the first occurrence
////            ans[i+a.length] = a[i];   // Copy the second occurrence
////        }
//        int[] ans = Arrays.copyOf(arr, arr.length * 2);
//        System.arraycopy(arr, 0, ans, arr.length, arr.length);


//        ArrayList<Integer>a = new ArrayList<>(Arrays.asList(2,4,3,1,7,5,6));
//        System.out.println(waveNonLexo(a));

//        int[]arr={12,9,8,2,11};
//        System.out.println(minimumAbsDifference(arr));

//        int[]arr={1,2,1,0,0,2,1,0,2,1,0,2,1,0};
//        System.out.println(Arrays.toString(DNFsort(arr)));

//        int[]arr = {3,4,5,2,0,1,6};
//        System.out.println(maxChunksToSorted(arr));

//        int[]arr = {3,1,4,6,5,8,7};
//        System.out.println(maxChunksToSortedII(arr));

//        int[]arr={1,2,5,6,7,2};
//        System.out.println(minSubArrayLen(7,arr));
//        int[]arr = {1,2,3,1,2,3};
//        System.out.println(containsNearbyDuplicate(arr,2));

//        int[]arr={4,2,-1,-3,0,1,2,3};
////        System.out.println(twoSum(arr,4,0));
//
//        Arrays.sort(arr);
//        List<List<Integer>>triplets=new ArrayList<>();
//        for (int i = 0; i < arr.length-2; i++) {
//            if(i>0 && arr[i]==arr[i-1]){
//                continue;
//            }
//            List<List<Integer>>temp = twoSum(arr,-arr[i],i+1);
//            for(List<Integer> e : temp){
//                e.addFirst(arr[i]); // put val in each list
//                triplets.add(e);
//            }
//        }
//
//        System.out.println(triplets);

//        int[][]arr2d={
//                {3,63,42},
//                {18,12,12},
//                {15,21,18},
//                {33,84,24}
//        };
//        minOperations(3,arr2d);

    }


    // 1 2 3 4 5   ->  k=2  ->  3 4 5 1 2
    static void rotateK(int[]arr,int k){  // brute force
        for (int j = 0; j < k; j++) {
            int temp = arr[0];
            for (int i = 0; i < arr.length - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[arr.length - 1] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    // optimised sol below using reverse algo
           /* 1 2 3 4 5
              3 2 1 - 5 4
              4 5 1 2 3 */
    static void rotateKarr(int[] arr, int k) {  // O(n)
        int n = arr.length;
        k = k % n; // Handle cases where k >= n

        // Step 1: Reverse the first k elements
        reverse(arr, 0, k - 1);
        // Step 2: Reverse the remaining elements
        reverse(arr, k, n - 1);
        // Step 3: Reverse the entire array
        reverse(arr, 0, n - 1);

        System.out.println(Arrays.toString(arr));
    }
    static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;j--;
        }
    }


    // (n-k) times LR   ==  RR (Right Rotation)
    public int[][] multipleLeftRotation(int[] a, int[] b) {
        int n = a.length;
        int m =b.length;
        int[][]ans=new int[m][n];

        // 2x ans print -> 1,2,3,4,5, 1,2,3,4,5
        int[]temp=new int[n*2];
        for (int i = 0; i < n; i++) {
            temp[i]=a[i];
            temp[n+i]=a[i];
        }

        for (int i = 0; i < m; i++) {
            int offset = b[i]%n;
            for (int j = 0; j < n; j++) {
                ans[i][j]=temp[j+offset];
            }
        }
        return ans;
    }


    public static ArrayList<Integer> wave(ArrayList<Integer> a) {
        Collections.sort(a);
//        System.out.println(a);
        for (int i = 1; i < a.size(); i+=2) {
            swap(a,i,i-1);
        }
        return a;
    }
    private static void swap(ArrayList<Integer> a, int i, int j) {
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
    public static ArrayList<Integer> waveNonLexo(ArrayList<Integer>a){
        for (int i = 1; i < a.size()-1; i+=2) {
            if(a.get(i)>a.get(i-1)) swap(a,i,i-1);
            if(a.get(i)>a.get(i+1)) swap(a,i,i+1);
        }
        return a;
    }

    public static List<List< Integer>> minimumAbsDifference(int[] arr){
        List<List<Integer>>ans=new ArrayList<>();
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;

        for (int i = 1; i < arr.length; i++) {
            minDiff=Math.min(minDiff , arr[i]-arr[i-1]);
        }

        for (int i = 1; i < arr.length; i++) {
            if(minDiff==arr[i]-arr[i-1]){
                List<Integer>temp = new ArrayList<>();
                temp.add(arr[i-1]);
                temp.add(arr[i]);
                ans.add(temp);
            }
        }
        return ans;
    }

    public static int[] twoSum(int[] arr, int k) {
        int[] ans =new int[2];
        Map<Integer,Integer>map=new HashMap<>();

        for(int i=0;i<arr.length;i++){
            if(map.containsKey(k-arr[i])){
                ans[1]=i;
                ans[0]=map.get(k-arr[i]);
            }
            map.put(arr[i],i);
        }
        return ans;
    }

    // 2 Sum -> Using 2 pointer
    public static List<List<Integer>> twoSum(int[]arr, int sum, int startFrom){ //assuming arr is sorted;
        Arrays.sort(arr);
        List<List<Integer>>ans = new ArrayList<>();
        int l=startFrom;
        int r=arr.length-1;

        while(l<r){
            // handling duplicate
            if(l>startFrom && l==arr[l-1]){
                l++;
                continue;
            }
            if(r<arr.length-1 && r==arr[r+1]){
                r--;
                continue;
            }

            //main logic
            if(arr[l]+arr[r]>sum){
                r--;
            }else if(arr[l]+arr[r]<sum) {
                l++;
            }else{
                List<Integer>temp=new ArrayList<>();
                temp.add(arr[l]);
                temp.add(arr[r]);
                ans.add(temp);
                l++;r--;
            }
        }
        return ans;
    }
    //    public static List<List<Integer>> threeSum(int[] arr) {
//
//    }
    public List<List<Integer>> threeSum(int[] arr) {  // where k = 0 (target)
        int n=arr.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr);

        for (int i = 0; i < n - 2; i++) {
            if (i == 0 || ( arr[i] != arr[i - 1])) { // duplicate handling
                int l = i + 1;
                int r = n - 1;
                int sum = -arr[i];

                while (l < r) {
                    if (arr[l] + arr[r] == sum) {
                        ans.add(Arrays.asList(arr[i], arr[l], arr[r]));
                        //skip duplicates
                        while (l < r && arr[l] == arr[l + 1]) l++;
                        while (l < r && arr[r] == arr[r - 1]) r--;
                        l++;r--;
                    } else if (arr[l] + arr[r] < sum) {
                        l++;
                    } else { // arr[l] + arr[r] > sum
                        r--;
                    }
                }
            }
        }

        return ans;
    }

    // 1,2,1,0,0,2,1,0,2,1,0,2,1,0
    public static  int[] DNFsort(int[]arr){
        int low = 0;
        int mid = 0;
        int high = arr.length-1;

        while(mid<=high){
            if(arr[mid]==2){
                arr[mid]=arr[high];
                arr[high]=2;
                high--;
            }else if(arr[mid]==0){
                arr[mid]=arr[low];
                arr[low]=0;
                mid++;low++;
            }else{ // m==1
                mid++;
            }
        }
        return arr;
    }




    public static int maxChunksToSorted(int[] arr) {
        if(arr.length==0) return 0;

        int cnt=0;
        int maxSoFar=arr[0];

        for (int i = 0; i < arr.length; i++) {
            maxSoFar=Math.max(maxSoFar,arr[i]);
            if(i==maxSoFar) cnt++;
        }
        return cnt;
    }

    // 8 2 5 2
    // 8 8 8 8
    // 2 2 2 2
    /*
    6 3 5  8 7  12 9
    6 6 6  8 8  12 12
    3 3 7  7 7  9  9
    */
    public static int maxChunksToSortedII(int[] arr){
        int n = arr.length;
        int cnt = 0;
        int[]left = new int[n];
        int[]right = new int[n];
        left[0] = arr[0];
        right[n-1] = arr[n-1];

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i-1],arr[i]);
        }
        for (int i = n-2; i >= 0; i--) {
            right[i] = Math.min(right[i+1],arr[i]);
        }


        for (int i = 0; i < n-1; i++) {
            if(left[i]<=right[i+1]){
                cnt++;
            }
        }

        return cnt+1;
    }



    public static void Csort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int correct = arr[i]-1; // -1 for (1->n)indexing / else (0 -> n-1)
            if (arr[i] != arr[correct]) {
                swap(arr, i , correct);
            } else {
                i++;
            }
        }
    }
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    // Maximum Sum Sub Array --> Kadane's Algo
    public static int maxSubArraySum(int[] a) { //kadane
        int maxSoFar = Integer.MIN_VALUE; // Keeps track of the maximum sum found so far.
        int ans = 0; // Keeps track of the current subarray sum.

        for (int e : a) {
            ans += e; // Add the current element to the current sum.
            if (ans > maxSoFar) maxSoFar = ans;  // Update the maximum sum if the current sum is greater.
            if (ans < 0) ans = 0; // If the current sum becomes negative, reset it to 0.
        }
        return maxSoFar;
    }
    // below (Minimum Sum Sub Array)
    public static int minSubArrayLen(int[] a, int k){ // [2,3,1,2,4,3] k=7 , ans = 2 (4,3)
        int currSum=0;
        int l=0,r=0;
        int minLen=Integer.MAX_VALUE;
        while(r<a.length){
            currSum+=a[r];
            while(currSum>=k){
                minLen=Math.min(minLen,r-l+1);
                currSum -= a[l];
                l++;
            }
            r++;
        }
        if(minLen==Integer.MAX_VALUE){
            minLen=0;
        }
        return minLen;
    }


    public static boolean containsNearbyDuplicate(int[] arr, int k) { // arr[i]==arr[j] && ∣i−j∣ ≤ k
        int n = arr.length;
        // for (int i = 0; i < n - 1; i++) {
        //     for (int j = i + 1; j < n; j++) {
        //         if (arr[i] == arr[j]){
        //             if(Math.abs(i-j)<=k)
        //             return true;
        //         }
        //     }
        // }
        // return false;
        // ----------------------------------------
        // HashSet<Integer>set=new HashSet<>();
        // for(int i=0;i<n;i++){
        //     if(i>k) set.remove(arr[i-k-1]); // extra line -> for special case |i-j|<=k
        //     if(set.contains(arr[i])) return true;
        //     set.add(arr[i]);
        // }
        // return false;
        // ----------------------------------------
        Map<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<n;i++){
            // if(map.containsKey(e) && map.get(e)>=1)
            if(map.containsKey(arr[i]) && i-map.get(arr[i])<=k){
                return true;
            }
            // map.put(e, map.getOrDefault(e,0)+1);
            map.put(arr[i],i);
        }
        return false;
    }


//    min ops to make all element equal to matrix
    public static int minOperations(int k, int[][]arr){
        int n=arr.length;
        int m=arr[0].length;
        if(n==0) return 0;
        int[]b=new int[n*m];

        // 2d -> 1d
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                b[i*m+j]=arr[i][j];
            }
        }
//        Arrays.stream(arr).flatMapToInt(Arrays::stream).toArray();  // 1 liner (2d->1d)
        Arrays.sort(b);

        // Check feasibility
        int mod = b[0] % k;
        for (int val : b) {
            if (val % k != mod) return -1;
        }

        int median = b[(n * m)/2];

//        for (int i = 0; i < b.length; i++) {
//            System.out.println(i+" "+b[i]+" " + makeMatrixEqualHelper(b,b[i],k));
//        }
//
//        return 0;

        return makeMatrixEqualHelper(b,median,0);

    }
    public static int makeMatrixEqualHelper(int[]b , int m, int k){
        int cnt=0;
        for (int e : b) {
            cnt += Math.abs(e - m) / k;
        }
        return cnt;
    }




    public int maxArr(ArrayList<Integer> A) {  // Amazon Qs
        int max1 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE, min2 = Integer.MAX_VALUE;

        for (int i = 0; i < A.size(); i++) {
            int valueWithIndex = A.get(i) + i;
            int valueWithoutIndex = A.get(i) - i;

            max1 = Math.max(max1, valueWithIndex);
            min1 = Math.min(min1, valueWithIndex);

            max2 = Math.max(max2, valueWithoutIndex);
            min2 = Math.min(min2, valueWithoutIndex);
        }

        return Math.max(max1 - min1, max2 - min2);
    }




    // -------------------------- Assignments -------------------------------

    public int maxProfit(int[] arr) {  // I/P : [10, 19, 1, 30, 32]  --> o/p = 31
        int maxTillNow = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]-arr[i] > maxTillNow){
                    maxTillNow=Math.max(maxTillNow,arr[j]-arr[i]);
                }
            }
        }
        if(maxTillNow==Integer.MIN_VALUE) return 0;
        return maxTillNow;
    }

    public static int maxProfit2ptr(int[] arr){ // optimised -> O(n)
        if (arr == null || arr.length < 2) return 0; // No profit can be made

        int minPrice = Integer.MAX_VALUE;
        int ans = 0; // To store the maximum profit

        for (int e : arr) {
            minPrice = Math.min(minPrice, e);
            int profit = e - minPrice;
            ans = Math.max(ans, profit);
        }
        return ans; 
    }

    // 7, 3, 2, 4, 9, 12, 56
    // 2, 3, 4, 7, 9, 12, 56
    public static int chocolateDistribution(int[] arr, int m){
        Arrays.sort(arr);
        int i=0;
        int j=m-1;
        int minDiff = Integer.MAX_VALUE;
        while(j<arr.length){
            minDiff=Math.min(minDiff,arr[j]-arr[i]);
            i++;j++;
        }
        return minDiff;
    }

    public static int distributeCandies(int[] arr) { // LeetCode Qs
        int docAdvised = arr.length/2;
        int ans = 0;

        Set<Integer>set = new HashSet<>();
        for(int e:arr){
            set.add(e);
        }
        int uniqueCandy = set.size();

        if(uniqueCandy==docAdvised) ans=uniqueCandy;
        else if(uniqueCandy<docAdvised) ans=uniqueCandy;
        else ans=docAdvised;

        return ans;
    }

    public static String longestCommonPrefix(String[] arr) { // imp
        if (arr == null || arr.length == 0) {
            return "";
        }

        String prefix = arr[0];
        for (int i = 1; i < arr.length; i++) {
            while (arr[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    // i/p: {2,8,7,1,5} --> o/p: [[2, 5], [7, 1]]
    public static List<List<Integer>> findPair(int[] arr){
        List<List<Integer>>ans=new ArrayList<>();
        Set<Integer>set=new HashSet<>();

        for(int e:arr){
            set.add(e);
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
//                List<Integer>temp = new ArrayList<>();
                if(set.contains(arr[i]+arr[j])){
//                    temp.add(arr[i]);
//                    temp.add(arr[j]);
                    List<Integer> temp = new ArrayList<>(Arrays.asList(arr[i], arr[j]));
                    ans.add(temp);
                }
            }
        }
//        ans.sort(Comparator.comparingInt((List<Integer> a) -> a.get(0)).thenComparingInt(a -> a.get(1)));
        ans.sort((a, b) -> {
            if (a.get(0).equals(b.get(0))) {
                return a.get(1) - b.get(1);  // Sort by second element if first is equal
            }
            return a.get(0) - b.get(0);  // Sort by first element
        });
        return ans;
    }

    // i/p:1, 1, 1, 2, 2, 6, 8, 8, 8 (k=2)  --> o/p: 6 and 8 (true)
    public boolean diffPossible(int[] arr, int k){
        int i=0,j=1;
        while(j<arr.length){
            if(arr[j]-arr[i]==k && i!=j) return true;
            else if(arr[j]-arr[i] < k) j++;
            else i++;
            // Ensure i != j
            if (i == j) j++;
        }
        return false;
    }

    //i/p: [321, 123, 89, 32, 11] --> o/p: [123, 321]
    public static List<Integer> find123Digits(int[] arr, int n){
        List<Integer>ans=new ArrayList<>();
        for(int e:arr){
            if(contains123(e)){
                ans.add(e);
            }
        }
        Collections.sort(ans);
        return ans;
    }
    private static boolean contain123(int n) {
        String s = String.valueOf(n);
        Set<Character>set=new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set.contains('1') && set.contains('2') && set.contains('3');
    }
    private static boolean contains123(int num) {
        boolean has1 = false, has2 = false, has3 = false;
        // Check the digits of the number
        while (num > 0) {
            int digit = num % 10;
            if (digit == 1) has1 = true;
            if (digit == 2) has2 = true;
            if (digit == 3) has3 = true;
            num /= 10;
            // Stop checking early if all digits are found
            if (has1 && has2 && has3) return true;
        }
        return false;
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2){
        // Check if rec1 is to the left or right of rec2
        if (rec1[2] <= rec2[0] || rec1[0] >= rec2[2]) return false;
        // Check if rec1 is above or below rec2
        if (rec1[3] <= rec2[1] || rec1[1] >= rec2[3]) return false;

        return true; // Rectangles overlap
    }

    public static Vector<Integer> findNumbers(int[] arr) {
        Vector<Integer>ans=new Vector<>();
        Csort(arr);
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]!=i+1){
                ans.add(i+1);
                ans.add(arr[i]);
            }
        }
        return ans;
    }

    public int maximumGap(int[] arr) {
        if(arr.length<2) return 0;
        Arrays.sort(arr);
        int max= Integer.MIN_VALUE;
        for(int i=1;i<arr.length;i++){
            if(arr[i]-arr[i-1]>max){
                max=Math.max(max,arr[i]-arr[i-1]);
            }
        }
        return max;
    }
    public static int maximumGapBucket(int[] arr) {
        if (arr.length < 2) return 0;

        int n = arr.length;
        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();

        if (min == max) return 0; // All elements are the same.

        // Calculate bucket size and number of buckets
        int bucketSize = Math.max(1, (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        // Initialize buckets
        int[][] buckets = new int[bucketCount][2];
        for (int[] bucket : buckets) {
            bucket[0] = Integer.MAX_VALUE; // min
            bucket[1] = Integer.MIN_VALUE; // max
        }

        // Place each number in a bucket
        for (int num : arr) {
            int index = (num - min) / bucketSize;
            buckets[index][0] = Math.min(buckets[index][0], num); // Update min
            buckets[index][1] = Math.max(buckets[index][1], num); // Update max
        }

        // Calculate maximum gap
        int maxGap = 0;
        int previousMax = min;

        for (int[] bucket : buckets) {
            if (bucket[0] == Integer.MAX_VALUE) continue; // Empty bucket
            maxGap = Math.max(maxGap, bucket[0] - previousMax);
            previousMax = bucket[1];
        }

        return maxGap;
    }

    public static List<int[]> mergeIntervals(int[][] arr) {
        // Step 1: Sort arr by start time
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> ans = new ArrayList<>();
        int n = ans.size();

        // Step 2: Iterate and merge arr
        for (int[] e : arr) {
            // If the ans list is empty or there is no overlap, add the interval
            if (ans.isEmpty() || ans.get(n-1)[1] < e[0]) {
                ans.add(e);
            } else {
                // Merge the current interval with the last interval in ans
                ans.get(n-1)[1] = Math.max(ans.get(n-1)[1], e[1]);
            }
        }
        return ans;

//        return ans.toArray(new int[ans.size()][]);
//        if return type is int[][]
    }

    public long trap(int[] arr) {
        if (arr == null || arr.length < 3) return 0;

        int l = 0, r = arr.length - 1;
        int leftMax = 0, rightMax = 0; // Max heights on the left and right
        long ans = 0;

        while (l <= r) {
            if (arr[l] < arr[r]) {
                // Water trapped depends on the left side
                if (arr[l] >= leftMax) {
                    leftMax = arr[l]; // Update the left max height
                } else {
                    ans += leftMax - arr[l]; // Calculate trapped water
                }
                l++;
            } else {
                // Water trapped depends on the right side
                if (arr[r] >= rightMax) {
                    rightMax = arr[r]; // Update the right max height
                } else {
                    ans += rightMax - arr[r]; // Calculate trapped water
                }
                r--;
            }
        }
        return ans; // Return the total trapped water as a long
    }


//    public static int firstMissingPositive(int[] arr){
//        Arrays.sort(arr);
//        int i=0;
//        while(i<arr.length-1){
//            if(arr[i]<=0){
//                i++;
//            }
//            if(arr[i+1]!=arr[i]+1){
//                return arr[i]+1;
//            }else{
//                i++;
//            }
//        }
//        return arr[i]+1;
//    }
    public static int firstMissingPositive(int[] arr) {
        int n = arr.length;
        // Step 1: Place each number in its correct position
        for (int i = 0; i < n; i++) {
            while (arr[i] > 0 && arr[i] <= n
                    && arr[arr[i] - 1] != arr[i]) {
                // Swap arr[i] with arr[arr[i] - 1]
                int temp = arr[i];
                arr[i] = arr[temp - 1];
                arr[temp - 1] = temp;
            }
        }
        // Step 2: Find the first index where arr[i] != i + 1
        for (int i = 0; i < n; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }
        // If all numbers are in place, return n + 1
        return n + 1;
    }


    // A = [1, 3, 2, 4, 5] --> [1,2]indices
    public static ArrayList<Integer> subUnsort(ArrayList<Integer> A) {
        int n = A.size();
        int l = 0, r = n - 1;

        // Step 1: Find the first element from the left that is out of order
        while (l < n - 1 && A.get(l) <= A.get(l + 1)) {
            l++;
        }
        // If the entire array is sorted
        if (l == n - 1) {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(-1);
            return result;
        }

        // Step 2: Find the first element from the right that is out of order
        while (r > 0 && A.get(r) >= A.get(r - 1)) {
            r--;
        }

        // Step 3: Find the min and max in the unsorted region
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = l; i <= r; i++) {
            min = Math.min(min, A.get(i));
            max = Math.max(max, A.get(i));
        }

        // Step 4: Expand the left and right boundaries
        while (l > 0 && A.get(l - 1) > min) {
            l--;
        }
        while (r < n - 1 && A.get(r + 1) < max) {
            r++;
        }

        // Step 5: Return the result
        ArrayList<Integer> result = new ArrayList<>();
        result.add(l);
        result.add(r);
        return result;
    }

    ///////////// ************** couldn't understand
    public static int getMinDiff(int[] A, int n, int k) {
        // Step 1: Sort the array
        Arrays.sort(A);

        // Step 2: Calculate the initial difference
        int minDiff = A[n - 1] - A[0];

        // Step 3: Iterate through the array
        int smallest = A[0] + k;
        int largest = A[n - 1] - k;

        for (int i = 0; i < n - 1; i++) {
            int minHeight = Math.min(smallest, A[i + 1] - k);
            int maxHeight = Math.max(largest, A[i] + k);
            minDiff = Math.min(minDiff, maxHeight - minHeight);
        }

        return minDiff;
    }
    /////////////////////////////////////////////////




}
