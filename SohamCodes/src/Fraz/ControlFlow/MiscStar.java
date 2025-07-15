package Fraz.ControlFlow;

public class MiscStar {
    public static void main(String[] args) {
//        p1singleLoop(5);
//        p2(5);
//        p3(5);
        p4(5);
    }

    static void p1(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j <= n-1  ; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= 2*i-1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        // Lower half
//        for (int i = n - 1; i >= 1; i--) {
//            for (int j = n; j >= i+1; j--) {
//                System.out.print("  ");
//            }
//            for (int j = 1; j <= 2 * i - 1; j++) {
//                System.out.print("* ");
//            }
//            System.out.println();
//        }
        for (int i = 2; i <= n ; i++) {
            for (int j = 1; j <= i-1 ; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= 2*n-(2*i-1) ; j++) {
                System.out.print("* ");
            }
            System.out.println("  ");
        }
    }
    /** imp */
    static void p1singleLoop(int n){
        int stars = 1;
        int spaces = n - 1;
        for(int i=1; i<2*n; i++) {
            for(int j=1; j<=spaces; j++)
                System.out.print(" ");
            for(int j=1; j<stars*2; j++)
                System.out.print("*");
            System.out.println();

            if(i<n) {
                spaces--;
                stars++;
            } else {
                spaces++;
                stars--;
            }
        }
    }

    static void p2(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 2*(i-1); j++) {
                System.out.print(" ");
            }
            for (int j = i; j <= n ; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // 2nd half
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= 2*(n-i) ; j++) {
                System.out.print(" ");
            }
            for (int j = 2; j <= i+1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void p3(int n){
        for (int i = 1; i <= n*2-1 ; i++) {
            for (int j = 1; j <= n*2-1 ; j++) {
                if(i==n || j==n){
                    System.out.print("+");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    static void p4(int n){
        for (int i = 1; i <= n*2-1 ; i++) {
            for (int j = 1; j <= n*2-1 ; j++) {
                if(i==j || j==n*2-i){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    static void p5(int n){

    }






}
