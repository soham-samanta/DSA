package Fraz.Math;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println(sumOfDigits(687));
//        System.out.println(rev(687));
//        System.out.println(isPalindrome(1213));
//        System.out.println(addDigitTill1(38));
//        System.out.println(reverseInteger(-687));
//        System.out.println(isArmstrong(1531));
//        System.out.println(fact(7));
//        System.out.println(findTrailingZeroInFact(4));
//        System.out.println(trailingZeroesOp(100));
//        System.out.println(cnt1(1321501));
//        System.out.println(occurrenceOf1(100));
//        fib(8);
//        System.out.println(isPrime(10));
//        closestPrimes(10,19);
//        System.out.println(Arrays.toString(closestPrimes(10,19)));
//        divisorOrFactors(12);
        System.out.println(isPowerOf(20));

    }

    static int sumOfDigits(int n){
        int ans = 0;
        while(n>0){
            int last=n%10;
            ans+=last;
            n/=10;
        }
        return ans;
    }

    static int rev(int n){
        int rev=0;
        while(n>0){
            int last = n%10;
            rev = rev*10+last;
            n/=10;
        }
        return rev;
    }

    static boolean isPalindrome(int n){
        int revN = rev(n);
        return revN == n;
    }

    /// LeetCode
    static int addDigitTill1(int n){
        int ans=0;
        while(n>0){
            ans+=n%10;
            n/=10;
        }
        return ans >= 10 ? addDigitTill1(ans) : ans;
    }

    static int reverseInteger(int n){ // only 32 bit + also rev (-)
        int ans = 0;
        boolean flg=true;
        if(n<0){
            n*=-1;
            flg=false;
        }
        while(n>0){
            if((Integer.MAX_VALUE / 10) < ans
                    || (Integer.MIN_VALUE / 10) > ans){
                return 0;
            }
            int last = n%10;
            ans=ans*10+last;
            n/=10;
        }
        if(!flg) return ans*-1;
        else return ans;
    }

    static boolean isArmstrong(int n){
        int storeN = n;
        int cnt=String.valueOf(n).length();

        int ans = 0;
        while(n>0){
            int last = n%10;
            ans+= (int) Math.pow(last,cnt);
            n/=10;
        }
        return ans==storeN;
    }

    static int fact(int n){
        int ans = 1;
        for (int i = n; i >= 1 ; i--) {
            ans*=i;
        }
        return ans;
    }

    static int findTrailingZeroInFact(int n){
        int fact = fact(n);
        int cnt=0;
        while(fact%10 == 0){
            cnt++;
            fact/=10;
        }
        return cnt;
    }
    static int trailingZeroesOp(int n){ // 2 & 5 are factor of 10
        int cnt=0;
        for (int i = 5; n/i >= 1 ; i*=5) {
            cnt+=n/i;
        }
        return cnt;
    }

    static int occurrenceOf1(int n){
        int ans=0;
        for (int i = 1; i <= n ; i++) {
            ans+=cnt1(i);
        }
        return ans;
    }
    static int cnt1(int n){
        int cnt=0;
        String s = String.valueOf(n);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch=='1') cnt++;
        }
        return cnt;
    }

    static void fib(int n){
        int a=0;
        int b=1;
        System.out.print(a+" "+b+" ");
        for (int i = 2; i < n ; i++) {
            int c = a+b;
            a=b;
            b=c;
            System.out.print(c+" ");
        }
    }

    static boolean isPrime(int n){
        if(n<2) return false;
        for (int i = 2; i <= Math.sqrt(n) ; i++) {
            if(n%i==0) return false;
        }
        return true;
    }
    /*static int countPrimes(int n) {
        int cnt=0;
        for (int i = 1; i < n ; i++) {
            if(isPrime(i)) cnt++;
        }
        return cnt;
    }*/  // TLE
    /// LeetCode
    static int countPrimes(int n) { // Sieve of Eratosthenes
        if(n<=2) return 0;
        boolean[]arr=new boolean[n];
        Arrays.fill(arr,true);
        arr[0]=false;
        arr[1]=false;

        for (int i = 2; i*i < n; i++) {
            if(arr[i]){
                for (int j = i*i; j < n ; j+=i) {
                    arr[j]=false;
                }
            }
        }

        int cnt=0;
        for (int i = 2; i < n ; i++) {
            if(arr[i]) cnt++;
        }
        return cnt;
    }

    // brute force
    static int[] closestPrimes(int l, int r) {
        ArrayList<Integer>ans = new ArrayList<>();
        for (int i = l; i <= r ; i++) {
            if(isPrime(i)){
                ans.add(i);
            }
        }
        System.out.println(ans);
        int n1=-1;
        int n2=-1;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < ans.size(); i++) {
            int diff = ans.get(i)-ans.get(i-1);
            if(diff<minDiff){
                minDiff=diff;
                n1=ans.get(i-1);
                n2=ans.get(i);
            }
        }
        return new int[]{n1,n2};
    }
    // optimised using Sieve of Eratosthenes
    public int[] closestPrimesOp(int l, int r) {
        // Step 1: Generate all primes up to r using sieve
        boolean[] arr = new boolean[r + 1];
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;

        for (int i = 2; i * i <= r; i++) {
            if (arr[i]) {
                for (int j = i * i; j <= r; j += i) {
                    arr[j] = false;
                }
            }
        }

        // Step 2: Filter primes in range [left, right]
        List<Integer> ans = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (arr[i]) ans.add(i);
        }

        // Step 3: Find the closest prime pair
        int minDiff = Integer.MAX_VALUE;
        int[] result = {-1, -1};

        for (int i = 1; i < ans.size(); i++) {
            int diff = ans.get(i) - ans.get(i - 1);
            if (diff < minDiff) {
                minDiff = diff;
                result[0] = ans.get(i - 1);
                result[1] = ans.get(i);
            }
        }
        return result;
    }

    static void divisorOrFactors(int n){
        List<Integer>ans = new ArrayList<>();
        for (int i = 1; i*i <= n ; i++) {
            if(n%i==0){
                ans.add(i);
            }
            if(i!=n/i){ // Check if i and n/i are different to avoid duplicates
                ans.add(n/i); // Add the paired divisor n / i to the list
            }
        }
        Collections.sort(ans);
        System.out.println(ans);
    }

    static boolean isPowerOfTwo(int n) {
        if (n < 1) return false;
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }

    static boolean isPowerOf(int n){
        if(n<2) return false;
        for (int i = 2; i <= Math.sqrt(n) ; i++) {
            int x = n;
            while(x%i==0){
                x/=i;
            }
            if(x==1) return true;
        }
        return false;
    }








}
