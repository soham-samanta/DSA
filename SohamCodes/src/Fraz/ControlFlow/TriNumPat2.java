package Fraz.ControlFlow;

public class TriNumPat2 {
    public static void main(String[] args) {
//        p1(5);
//        p1_2(5);
//        p2(5);
//        p2_2(5);
//        p3(5);
//        p3_2(5);
//        p4(5);
//        p4_2(5);
        p5(5);
        p5_2(5);

    }

    static void p1(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j >= 1; j--) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    static void p1_2(int n){
        for (int i = n; i >= 1 ; i--) {
            for (int j = i; j >= 1; j--) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    static void p2(int n){
        for (int i = n; i >= 1 ; i--) {
            for (int j = n; j >= i ; j--) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    static void p2_2(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = n; j >= i ; j--) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    static void p3(int n){
        for (int i = n; i >= 1 ; i--) {
            for (int j = i; j <= n ; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    static void p3_2(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j <= n ; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    /** Imp */
    static void p4(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j <= 2*i-1; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    static void p4_2(int n){
        for (int i = n; i >= 1 ; i--) {
            for (int j = i; j <= 2*i-1; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    // iterates 2 * n times  --> not optimised
    static void p5s(int n){
        for (int i = 1; i <= 2*n ; i++) {
            for (int j = i; j <= 2*n-1 ; j+=2) {
                if(j%2!=0) System.out.print(j+" ");
            }
            System.out.println();
        }
    }
    static void p5(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 2*i+1; j <= 2*n-1; j += 2) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    static void p5_2(int n){
        for (int i = n; i >= 0; i--) {
            for (int j = 2*i+1; j <= 2*n-1; j += 2) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }








}
