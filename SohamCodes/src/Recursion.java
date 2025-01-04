

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
//        System.out.println("\t\t\t*** Coding with Prime 3 ***");
//        System.out.println(sumOfN(10));
//        System.out.println(fib(9));
//        System.out.println(josephus(5,3));
//        System.out.println(fact(5));
//        System.out.println(isPalindromeRec("racecasr"));
//        System.out.println(findSubstrings("abc"));
        System.out.println(power(3,4));
        System.out.println(Math.pow(3,4));
    }

    public static boolean isPalindrome(String s) {
        char[] arr = s.toCharArray();
        int i=0;
        int j = arr.length-1;
        boolean flg = true;
        while(i<j){
            if(arr[i]!=arr[j]){
                flg=false;
                break;
            }
            i++;j--;
        }
        return flg;
    }

    public static boolean isPalindromeRec(String s){
        return palindrome(s,0,s.length()-1);
    }
    private static boolean palindrome(String s, int l , int r){
        if(l>=r){
            return true;
        }
        if(s.charAt(l)==s.charAt(r)) {
            return palindrome(s, l + 1, r - 1);
        }
        return false;
    }

    public static int sumOfN(int n){
        if(n == 1) return n;
        return n + sumOfN(n-1);
    }

    public static int fib(int n){
        if(n<2) return n;
        return fib(n-1)+fib(n-2);
    }

    public static int fact(int x){
        if(x==1) return x;
        return x*fact(x-1);
    }

    // (sub + k) % n -> relation
    public static int josephus(int n , int k){

        if(n==1) return 0;

        return (josephus(n-1,k) + k ) % n;
    }



    public static int nCr(int n, int r) {
//        return fact(n) / (fact(n-r) * fact(r));   // Brute Force
//        if (n < r) {
//             throw new IllegalArgumentException("n should be greater than or equal to r");
////            return 0;
//        }

        // Base cases
        if(n<r) return 0;
        if (n == r || r == 0) return 1;
        // Recursive calculation using Pascal's identity    -->  ncr = (n-1 [C] r-1 )+ (n-1 [C] r)
        return nCr(n - 1, r - 1) + nCr(n - 1, r);

    }

    public static int cntOccurrence(String s, String t){
        return cntOccurrenceHelper(0,s,t);
    }
    public static int cntOccurrenceHelper(int i, String s, String t){
        if(i> s.length()-t.length()) return 0;

        int ans = cntOccurrenceHelper(i+1,s,t);
//        boolean match = s.substring(i,i+t.length()).equals(t);
        boolean match = s.startsWith(t, i);
        if(match) return ans+1;
        else return ans;
    }


    public static List<String> findSubstrings(String s){
        List<String>ans = new ArrayList<>();
        findSubstringsHelper(s,0,"",ans);
        ans.remove("");
        Collections.sort(ans);
        return ans;
    }
    private static void findSubstringsHelper(String s, int i , String curr, List<String>ans){
        if(i==s.length()) {
//            System.out.print(curr+ " ");
            ans.add(curr);
//            if (!curr.isEmpty()) { // Exclude empty substring
//                ans.add(curr);
//            }
            return;
        }
        char ch = s.charAt(i);
        findSubstringsHelper(s,i+1,curr+ch,ans);
        findSubstringsHelper(s,i+1,curr,ans);
    }


    public static boolean arraySortedOrNot(int []arr, int n){
        return arraySortedOrNotHelper(arr,n,0);
    }
    private static boolean arraySortedOrNotHelper(int[]arr,int n, int i){
        if(i==arr.length-1){
            return true;
        }
        if(arr[i]>arr[i+1]){
            return false;
        }
        return arraySortedOrNotHelper(arr,n,i+1);
    }

    public static int numberOfPaths(int m, int n){
        if(n==1 || m==1) return 1;
        return numberOfPaths(m-1,n)+numberOfPaths(m,n-1);
    }

    public double powerMod(double x, int n, int p){
        return Math.pow(x,n)%p;
    }


    public static int power(int a, int b){
        if(b==0) return 1;
        return power(a,b-1)*a;
    }

    public double myPow(double a, int b, int p) {
        if (b == 0) return 1.0;

        // Handling negative exponent
        if (b < 0) {
            a = 1 / a;
            b = -b;
        }

        // Recursive calculation
        double half = myPow(a, b / 2, p) % p;
        double result = (half * half) % p;

        // If b is odd, multiply by a once more
        if (b % 2 != 0) {
            result = (result * a) % p;
        }

        // Ensure result is positive and within bounds
        return (result + p) % p;


    }


}
