package Fraz.ControlFlow;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        int[]arr={2,5,13,7,1,9};
//        greatestOf3(arr);
//        whichChar('S');
//        cashRemain(3674);

//        checkTriangle(2,4,2);

        electricityUnit(459);
//        System.out.println(200/500);



    }

    static void electricityUnit(int unit){
        float amount = 0;
        if(unit<=50){
            amount = unit*0.5f;
        }else if(unit<=150){
            amount = 25+ ((unit-50)*0.75f);
        }else if(unit<=250){
            amount = 100+ ((unit-150)*1.20f);
        }else{
            amount = 220+ ((unit-250)*1.50f);
        }

        float surge = amount*0.20f;
        float totalAmount = amount + surge;

        System.out.println(totalAmount);
    }

    static void checkTriangle(int a, int b, int c){
        if(a == b && b == c){
            System.out.println("equilateral");
        }else if(a==b || a==c || b==c){
            System.out.println("isosceles");
        }else{
            System.out.println("Scalene");
        }
    }

    static void cashRemain(int n){
        int[] arr = {500, 100, 50, 20, 10, 5, 2, 1};
        for (int e : arr) {
            int cnt = n / e;
            if (cnt > 0) {
                System.out.println(e + " rupee note(s): " + cnt);
                n %= e;
            }
        }
//        int ans = 0;
//        ans = n/500;
//        int rem = 0;
//        rem = n%500;
//
//        System.out.println("500 notes -> "+ans);
//
//        if(rem>=200){
//            ans = rem/200;
//            rem = rem%200;
//            System.out.println("200 notes -> "+ans);
//        }
//
//        if(rem>=100){
//            ans = rem/100;
//            rem = rem%100;
//            System.out.println("100 notes -> "+ans);
//        }
//
//        if(rem>=50){
//            ans = rem/50;
//            rem = rem%50;
//            System.out.println("50 notes -> "+ans);
//        }
//
//        if(rem>=20){
//            ans = rem/20;
//            rem = rem%20;
//            System.out.println("20 notes -> "+ans);
//        }
    }

    static void whichChar(char ch){
        if((ch>='a' && ch<= 'z') || (ch>='A' && ch<= 'Z')){
            System.out.println("Alphabet");
        }else if(ch>='0' && ch<='9'){
            System.out.println("Digit");
        }else{
            System.out.println("Special Character");
        }
    }

    static void greatestOf3(int[]arr){
//        int max = Integer.MIN_VALUE;
//        for (int e:arr) {
//            max = Integer.max(max,e);
//        }
//        System.out.println(max);
        Arrays.sort(arr);
        System.out.println(arr[arr.length-1]);
    }








}
