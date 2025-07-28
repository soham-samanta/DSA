package Fraz.ControlFlow;

public class NumPattern2 {
    public static void main(String[] args) {
//        p1(5);
//        p2(5);
//        p3(5);
//        p4(7,7);
//        p5(5);
        p6(5);
    }

    static void p1(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n ; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    static void p2(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n ; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    static void p3(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j <= n+i-1 ; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    // assuming row == col
    static void p4(int r, int c){
//        for (int i = 1; i <= r ; i++) {
//            for (int j=(i*c)-(r-1); j<=c*i; j++) {  // fix-> j=(i-1)*c+1;
//                System.out.print(j+" ");
//            }
//            System.out.println();
//        }

        /* Corrected Code -> Simplified */
        int num = 1;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }
    }

    static void p5(int n){
        for (int i=n; i>=1; i--) {
            int lastDigit = 0;
            for (int j=n; j>=i; j--) {
                System.out.print(j+" ");
                lastDigit=j;
            }
            for (int j = 1; j <= i-1; j++) {
                System.out.print(lastDigit+" ");
            }
            System.out.println();
        }
    }

    static void p6(int n){
        for (int i = n; i >= 1 ; i--) {
            for (int j = 1; j <= i ; j++) {
                System.out.print(i+" ");
            }
            for (int j = i + 1; j <= n; j++) {  // imp
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }





}
