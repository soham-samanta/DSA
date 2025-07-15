package Fraz.ControlFlow;

public class PyramidStar {
    public static void main(String[] args) {
        p1(5);
        p2(5);
        p3(5);
        p4(5);
    }


    static void p1(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j <= n-1 ; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= 2*i-1 ; j++) {  // i+(i-1) = 2*i-1
                System.out.print("* ");
            }
            System.out.println("  ");
        }
    }

    static void p2(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j <= n-1 ; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= 2*i-1 ; j++) {
                if(i==1 || i==n || j==1 || j==2*i-1){
                    System.out.print("* ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println("  ");
        }
    }

    static void p3(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i-1 ; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= 2*n-(2*i-1) ; j++) {
                System.out.print("* ");
            }
            System.out.println("  ");
        }
    }

    static void p4(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i-1 ; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= 2*n-(2*i-1) ; j++) {
                if(i==1 || i==n || j==1 || j==2*n-(2*i-1)){
                    System.out.print("* ");
                }else{
                    System.out.print("  ");
                }
            }

            System.out.println("  ");
        }
    }




}
