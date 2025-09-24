package Fraz.TwoPointer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        int[]arr=new int[]{0,1,0,2,3,0,4,5,0};
//        System.out.println(Arrays.toString(arr));
//        revArr(arr);
//        String s = "madam";
//        revString(s);
//        System.out.println(hasPairSum(arr,22));
//        System.out.println(Arrays.toString(moveAll0toEnd(arr)));
//        moveZeros(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(isPalindrome(s));

//        int[]arr = new int[]{-7, -3, 2, 3, 11};
//        System.out.println(Arrays.toString(sortedSqure(arr)));

//        String a="abc";
//        String b="pqrSoham";
//        System.out.println(mergeAlter(a,b));
//        String s = "cabaabac";
//        System.out.println(minLengthAfterDeletingSimilarEnds(s));
//        int[]arr = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
//        System.out.println(trap(arr));

//        String s = "abcdefd";
//        revPrefix(s,'d');

//        String s = "   the sky is blue    ";
//        System.out.println(reverseWords(s));

//        int[]arr = new int[]{1,0,0}; String s = "001010101";
//        System.out.println(separateBalls(arr));
//        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
//        System.out.println(Arrays.toString(arr));
//        System.out.println(minimumRefill(arr, 5, 5));
//        System.out.println(wateringPlantsI(arr,5));
//        System.out.println(Arrays.toString(sortArrayByParityII(arr)));
//        rotate(arr,3);

//        System.out.println(areSentencesSimilarIII("My name is Haley","My Haley"));

//        System.out.println(validPalindrome2("deeee"));

        System.out.println(Arrays.toString(shortestToChar("loveleetcode",'e')));


    }

    static void revArr(int[] arr) {
        int n = arr.length;
        int i = 0, j = n - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        System.out.println(Arrays.toString(arr));
    }

    static void revString(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
            char temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    static boolean hasPairSum(int[] arr, int k) { // 1 3 5 6 7 9  -> 3,5 = 8
        int n = arr.length;
        int i = 0;
        int j = n - 1;
        while (i < j) {
            if (arr[i] + arr[j] > k) {
                j--;
            } else if (arr[i] + arr[j] < k) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    //order doesn't matter
    static int[] moveAll0toEnd(int[] arr) {
        int n = arr.length;
        int i = 0, j = n - 1;

        while (i < j) {
            if (arr[i] != 0) {
                i++;
            } else { // i=0
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                j--;
            }
        }
        return arr;
    }

    // order matters
    static void moveZeros(int[] arr) {
        int pos = 0;
        for (int e : arr) {
            if (e != 0) {
                arr[pos++] = e;
            }
        }
        while (pos < arr.length) {
            arr[pos++] = 0;
        }
    }

    static boolean isPalindrome(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    static int[] mergeSortedArr(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;

        int[] ans = new int[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                ans[k++] = arr1[i++];
            } else {
                ans[k++] = arr2[j++];
            }
        }
        while (i < n1) ans[k++] = arr1[i++];
        while (j < n1) ans[k++] = arr2[j++];

        return ans;
    }

    static int[] sortedSqure(int[] arr) {
        //-4, -1, 0, 3, 10
        int n = arr.length;
        int[] ans = new int[n];
        int i = 0, j = n - 1, pos = n - 1;
        while (i < j) {
            int leftSq = arr[i] * arr[i];
            int rightSq = arr[j] * arr[j];

            if (leftSq > rightSq) {
                ans[pos--] = leftSq;
                i++;
            } else {
                ans[pos--] = rightSq;
                j--;
            }
        }
        return ans;
    }

    static String mergeAlter(String a, String b) {
        int n1 = a.length();
        int n2 = b.length();
//        int n = Math.max(n1,n2);

        int i = 0, j = 0;

        StringBuilder sb = new StringBuilder();

        while (i < n1 && j < n2) {
            sb.append(a.charAt(i++));
            sb.append(b.charAt(j++));
        }
        while (i < n1) {
            sb.append(a.charAt(i++));
        }
        while (j < n2) {
            sb.append(b.charAt(j++));
        }
        return sb.toString();

//        return IntStream.range(0, n)
//                .mapToObj(i ->
//                        (i < n1 ? String.valueOf(a.charAt(i)) : "") +
//                        (i < n2 ? String.valueOf(b.charAt(i)) : ""))
//                .reduce("", (x, y) -> x + y);
    }

    static int minLengthAfterDeletingSimilarEnds(String s) {
        /* aabccabba */
        int n = s.length();
        int i = 0, j = n - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            char c = s.charAt(i);
            while (i <= j && s.charAt(i) == c) i++;
            while (i <= j && s.charAt(j) == c) j--;
        }
        return j - i + 1;
    }

    static int maxArea(int[] arr) {
        int n = arr.length;
        int i = 0, j = n - 1;
        int ans = Integer.MIN_VALUE;
        while (i < j) {
            if (arr[i] < arr[j]) {
                ans = Math.max(ans, (j - i) * arr[i]);
                i++;
            } else {
                ans = Math.max(ans, (j - i) * arr[j]);
                j--;
            }
        }
        return ans;
    }

    static int trap(int[] arr) {
        int i = 0, j = arr.length - 1;
        int leftMax = 0, rightMax = 0;
        int ans = 0;
        // 0,1,0,2,1,0,1,3,2,1,2,1
        while (i < j) {
            if (arr[i] < arr[j]) {
                if (arr[i] >= leftMax) {
                    leftMax = arr[i];
                } else {
                    ans += leftMax - arr[i];
                }
                i++;
            } else {
                if (arr[j] >= rightMax) {
                    rightMax = arr[j];
                } else {
                    ans += rightMax - arr[j];
                }
                j--;
            }
        }
        return ans;
    }

    static void revPrefix(String s, char c) {
        char[] arr = s.toCharArray();
        int i = 0, j = 0;
        while (i < s.length()) {
            if (arr[i] == c) {
                j = i;
                break;
            }
            i++;
        }
        i = 0;
        while (i <= j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
        System.out.println(new String(arr));
//        System.out.println(Arrays.toString(arr));
    }

    static String reverseWords(String s) {
        String[] arr = s.trim().split(" +");
        int i = 0, j = arr.length - 1;
        while (i < j) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
        return String.join(" ", arr);
    }

    public String reverseVowels(String s) {
        char[]arr = s.toCharArray();
        int i=0,j=s.length()-1;
        while(i<j){
            while(i < j && !isVowel(arr[i]))i++;
            while(i < j && !isVowel(arr[j]))j--;

            if(i<j){
                char temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                i++;j--;
            }
        }
        return new String(arr);
    }
    static boolean isVowel(char c){
        Set<Character> set=new HashSet<>();
        Collections.addAll(set, 'a','e','i','o','u','A','E','I','O','U');
        return set.contains(c);
    }

    static int separateBalls(String s) {
        int[] arr = s.chars().map(c -> c - '0').toArray();
        // black - 1 , white - 0
        int moves = 0;
        int ones = 0;
        for (int e : arr) {
            if (e == 1) ones++;
            else moves += ones;
        }
        return moves;
    }


    // Watering Plant II
    static int minimumRefill(int[] arr, int capacityA, int capacityB) {
        int i = 0, j = arr.length - 1;
        int cnt = 0;
        int capA = capacityA, capB = capacityB;
        while (i < j) {
            if (arr[i] > capA) {
                cnt++;
                capA = capacityA;
            }
            capA -= arr[i];
            i++;

            if (arr[j] > capB) {
                cnt++;
                capB = capacityB;
            }
            capB -= arr[j];
            j--;
        }
        // Handle the middle plant if odd number of plants
        if (i == j) {
            if (Math.max(capA, capB) < arr[i]) cnt++;
        }
        return cnt;
    }

    static int wateringPlantsI(int[] arr, int capacity) {
        int i = 0;
        int cnt = 0;
        int cap = capacity;
        while (i < arr.length) {
            if (arr[i] > cap) {
                cnt += i * 2;
                cap = capacity;
            } else {
                cap -= arr[i];
                i++;
                cnt++;
            }
        }
        return cnt;
    }

    static int[] twoSum(int[] arr, int k){
        Map<Integer,Integer>map = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(map.containsKey(k-arr[i])){
                return new int[]{i,map.get(k-arr[i])};
            }
            map.put(arr[i],i);
        }
        return new int[]{};
    }


    static int[] sortArrayByParityI(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            while (i < j && arr[i] % 2 == 0) i++;
            while (i < j && arr[j] % 2 != 0) j--;

            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return arr;
    }

    // order is maintained
    public int[] sortArrayByParityStable(int[] arr) {
        int[] ans = new int[arr.length];
        int pos = 0;
        // copy evens
        for (int e : arr) {
            if (e % 2 == 0) ans[pos++] = e;
        }
        // copy odds
        for (int e : arr) {
            if (e % 2 != 0) ans[pos++] = e;
        }
        return ans;
    }

    static int[] sortArrayByParityII(int[] arr) {
        int i = 0, j = 1;
        int n = arr.length;
        while (i < n && j < n) {
            if (arr[i] % 2 == 0) {
                i += 2;
            } else if (arr[j] % 2 != 0) {
                j += 2;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i += 2;
                j += 2;
            }
        }
        return arr;
    }

    static int[] rearrangeElementBySign(int[] arr) {
        int[] ans = new int[arr.length];
        int pos = 0, neg = 1;
        for (int e : arr) {
            if (e > 0) {
                ans[pos] = e;
                pos += 2;
            } else {
                ans[neg] = e;
                neg += 2;
            }
        }
        return ans;
    }

    static void rotate(int[] arr, int k) {
        int n = arr.length;
        if (n == 0 || k == 0 || k % n == 0) return;
        // To handle cases where k is larger than n
        k = k % n;

        rev(arr, 0, n - 1);
        rev(arr, 0, k - 1);
        rev(arr, k, n - 1);

        System.out.println(Arrays.toString(arr));
    }

    static void rev(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    ///  imp
    static boolean areSentencesSimilarIII(String s1, String s2) {
        // Split sentences into lists of words
        String[] arr1 = s1.trim().split(" +");
        String[] arr2 = s2.trim().split(" +");

        // Ensuring that words1 should have the less length
        if (arr1.length > arr2.length) {
            String[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }

        // Initialize pointers for left and right ends
        int l1 = 0, l2 = 0;
        int r1 = arr1.length - 1, r2 = arr2.length - 1;

        // Match the prefix (left side)
        while (l1 < arr1.length && arr1[l1].equals(arr2[l2])) {
            l1++;
            l2++;
        }

        // Match the suffix (right side)
        while (r1 >= 0 && arr1[r1].equals(arr2[r2])) {
            r1--;
            r2--;
        }

        // Check if the entire sentence1 has been covered by matching prefix and suffix
        // The remaining part in the middle should only be extra words in the longer sentence
        return r1 < l1;
    }

    // Move pieces to obtain string
    static boolean canChange(String s1, String s2) {
        int n = s1.length();
        int i = 0, j = 0; // step1
        while (i < n || j < n) {
            // Skip blank spaces in 'start'
            while (i < n && s1.charAt(i) == '_') i++;
            // Skip blank spaces in 'target'
            while (j < n && s2.charAt(j) == '_') j++;

            // If both pointers exceed the length, we are done
            if (i == n && j == n) return true; // step3
            // If one string is exhausted while the other isn't, return false
            if (i == n || j == n) return false; // step4

            // Compare characters at the current position
            if (s1.charAt(i) != s2.charAt(j)) return false; // step5

            // Check if 'L' can only move left
            if (s1.charAt(i) == 'L' && i < j) return false; // step6
            // Check if 'R' can only move right
            if (s1.charAt(i) == 'R' && i > j) return false; // step7

            i++;
            j++;
        }
        return true; // step8
    }

    static int[] pivotArray(int[] arr, int k) { // 9,12,5,10,14,3,10  |(10) ->
        int n = arr.length;
        int[] ans = new int[n];
        int lessI = 0;
        int greaterI = n - 1;

        int i = 0, j = n - 1;
        while (i < n) {
            if (arr[i] < k) {
                ans[lessI] = arr[i];
                lessI++;
            }
            if (arr[j] > k) {
                ans[greaterI] = arr[j];
                greaterI--;
            }
            i++;
            j--;
        }
        while (lessI <= greaterI) {
            ans[lessI] = k;
            lessI++;
        }
        return ans;
    }


    static String lexoPalindrome(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            if (arr[i] != arr[j]) {
                arr[i] = arr[j] = (char) Math.min(arr[i], arr[j]);
            }
            i++;
            j--;
        }
        return new String(arr);
    }

    static boolean isPalindromeI(String s) {
//        return isPalindrome(s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase());
        int i = 0, j = s.length() - 1;
        while (i < j) {
            // Skip non-alphanumeric characters
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    ///  imp
    static boolean validPalindrome2(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }
    static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    ///  imp
    static int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        int cur = Integer.MAX_VALUE / 2; // large number
        // First pass (left → right)
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                cur = 0;
            } else {
                cur++;
            }
            ans[i] = cur;
        }
        // Second pass (right → left)
        cur = Integer.MAX_VALUE / 2;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                cur = 0;
            } else {
                cur++;
            }
            ans[i] = Math.min(ans[i], cur);
        }
        return ans;
    }

    static String reversePrefix(String s, char ch) {
        char[]arr=s.toCharArray();
        int i=0,j=s.indexOf(ch);
        while(i<j){
            char temp = arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;j--;
        }
        return new String(arr);
//        if (j != -1) {
//            return new StringBuilder(s.substring(0, j + 1)).reverse().toString()
//            + s.substring(j + 1);
//        }
    }









}
