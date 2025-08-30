package Fraz.ControlFlow;

public class TriNumPat1 {
    public static void main(String[] args) {
//        p1(5);
//        p2(5);
//        p3(5);
//        p4(5);
//        p4_1(5);
//        p5(5);
        p5_1(5);

    }

    static void p1(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i ; j++) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    static void p2(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n-i ; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= i ; j++) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    static void p3(int n){
        for (int i = n; i >= 1 ; i--) {
            for (int j = 1; j <= i ; j++) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    static void p4(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j <= n ; j++) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    static void p4_1(int n){
        for (int i = n; i >= 1 ; i--) {
            for (int j = n; j >= i ; j--) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    static void p5(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i ; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    static void p5_1(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n-i+1 ; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }





}
