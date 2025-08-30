package Fraz.ControlFlow;

public class TriNumPat3 {
    public static void main(String[] args) {
//        p1(5);
//        p2(5);
        p3(5);
//        p4(7);
    }


    static void p1(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i ; j++) {
                if(j%2==0) System.out.print(0+" ");
                else System.out.print(1+" ");
            }
            System.out.println();
        }
    }

    static void p2(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i ; j++) {
                if(i%2==0) System.out.print(0+" ");
                else System.out.print(1+" ");
            }
            System.out.println();
        }
    }

    static void p3(int n){
        int cnt=1;
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i ; j++) {
                if(cnt%2==0){
                    System.out.print(0);
                }else{
                    System.out.print(1);
                }
                cnt++;
            }
            System.out.println();
        }
    }

    static void p4(int n){
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i ; j++) {
                if(i==1 || i==n || j==1 || j==i){
                    System.out.print(1);
                }else{
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }

}
