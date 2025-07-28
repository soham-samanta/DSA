package Fraz.ControlFlow;

public class NumPattern1 {
    public static void main(String[] args) {
//        p1(6,5);
//        p2(6,5);
//        p3(6,5);
//        p4(5,5);
//        p5(5);
//        p6(5);
        p7(5);
    }

    static void p1(int r, int c){
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c ; j++) {
                if(i%2==0){
                    System.out.print("0");
                }else{
                    System.out.print("1");
                }
            }
            System.out.println();
        }
    }

    static void p2(int r, int c){
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c ; j++) {
                if(j%2==0){
                    System.out.print("1");
                }else{
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    static void p3(int r, int c){
        for (int i = 1; i <= r ; i++) {
            for (int j = 1; j <= c ; j++) {
                if(i==1 || i==r || j==1 || j==c){
                    System.out.print("1");
                }else{
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    static void p4(int r, int c){  // chess board
        for (int i = 1; i <= r ; i++) {
            for (int j = 1; j <= c ; j++) {
                if(i%2==0){
                    if(j%2==0){
                        System.out.print("1 ");
                    }else{
                        System.out.print("0 ");
                    }
                }else{
                    if(j%2==0){
                        System.out.print("0 ");
                    }else{
                        System.out.print("1 ");
                    }
                }
            }
            System.out.println();
        }
    }

    static void p5(int n){
        int cond = 0;
        if(n%2==0) cond = n/2;
        else cond = (n/2)+1;
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n ; j++) {
                if(i==cond || j==cond){
                    System.out.print("0");
                }else{
                    System.out.print("1");
                }
            }
            System.out.println();
        }
    }

    static void p6(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n ; j++) {
                if(i==j || j==n-i+1){
                    System.out.print("1 ");
                }else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    static void p7(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n ; j++) {
                if((i==1 || i==n) && (j!=1 && j!=n) ||
                        (j==1 || j==n) && (i!=1 && i!=n) ){
                    System.out.print("1 ");
                }else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }









}
