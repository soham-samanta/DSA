package Fraz.ControlFlow;

import com.sun.security.jgss.GSSUtil;

public class TriangleStar {
    public static void main(String[] args) {
        p1(5);
        p2(7);
        p3(5);
        p4(7);
        p5(5);
    }

    static void p1(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    static void p2(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i ; j++) {
                if(i==1 || i==n || j==1 || j==i){
                    System.out.print("* ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    static void p3(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j <= n-1; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    static void p4(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j <= n-1 ; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= i ; j++) {
                if(i==1 || i==n || j==1 || j==i){
                    System.out.print("* ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    static void p5(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n-i+1; j++) {
                System.out.print("* ");
            }
            System.out.println("  ");
        }
    }



}

