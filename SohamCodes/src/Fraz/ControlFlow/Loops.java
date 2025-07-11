package Fraz.ControlFlow;

import java.util.Arrays;
import java.util.List;

public class Loops {
    public static void main(String[] args) {
//        sumOf1stLast(248395);
//        prod(1234);
//        System.out.println(palindrome(12321));
//        System.out.println(rev(12345));
//        System.out.println(power(2,5));
//        fib(10);

//        printFactor(12);
//        System.out.println(isPrime(4));
//        printPrime(20);
//        printPrimeFactor(10);
        System.out.println(isArmstrong(371));



    }




    static void sumOf1stLast(int n){
        int lastDigit = n%10;
        while(n>=10){
            n/=10;
        }
//        System.out.println(n);
        System.out.println(n+lastDigit);
    }

    static void prod(int n){
        int ans=1;
        while(n>0){
            ans*=n%10;
            n/=10;
        }
        System.out.println(ans);
    }

    static int rev(int n){
        int rev=0;
        while(n!=0){
            int last = n%10;
            rev=rev*10+last;
            n/=10;
        }
        return rev;
    }

    static boolean palindrome(int n){
//        String s = Integer.toString(n);
//        char i = s.charAt(0);
//        char j = s.charAt(s.length()-1);
//        while(i<j){
//            if(i!=j){
//                System.out.println("not palindrome");
//                return;
//            }
//            i++;j--;
//        }
//        System.out.println("palindrome");
        int reversedNumber = rev(n);
        return reversedNumber == n;
    }

    static int power(int a, int b){
//        return (int)Math.pow(a,b);
        int ans=a;
        for (int i = 0; i < b-1; i++) {
            ans*=a;
        }
        return ans;
    }

    static void fib(int n){
        int a=0,b=1;
        System.out.print(a+" "+b+" ");
        for (int i = 0; i < n-2; i++) {
            int c=a+b;
            System.out.print(c+" ");
            a=b;
            b=c;
        }
    }


    /** Loops 2 */
    static void printFactor(int n){
        for (int i = 1; i <= n; i++) {
            if(n%i==0){
                System.out.print(i+" ");
            }
        }
    }

    static boolean isPrime(int n){
        return isPrimeHelper(n);
    }
    static boolean isPrimeHelper(int n){
        for (int i = 2; i <= n/2; i++) {
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

    static void printPrime(int n){
        for (int i = 2; i <= n ; i++) {
            if(isPrime(i)){
                System.out.print(i+" ");
            }
        }
    }

    static void printPrimeFactor(int n){
        for (int i = 2; i <= n ; i++) {
            if(n%i==0){
                if(isPrime(i)){
                    System.out.print(i+" ");
                }
            }
        }
    }


    /** Loops 3 */
    static boolean isArmstrong(int n) {
        int cnt = 0;
        // Count digits
        int temp = n;
        while (temp > 0) {
            temp /= 10;
            cnt++;
        }
        // Calculate Armstrong sum
        temp = n; //
        int armstrongSum = 0;
        while (temp > 0) {
            int digit = temp % 10;
            armstrongSum += (int) Math.pow(digit, cnt);
            temp /= 10;
        }
        return n == armstrongSum;
    }








}
