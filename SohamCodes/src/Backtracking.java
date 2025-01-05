

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;

public class Backtracking {

    public static void main(String[] args) {
        int [][]arr = {
                {1, 1, 1, 0},
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
        };
//        int n = arr.length;
//        int m = arr[0].length;
//        boolean[][] vis = new boolean[n][m];
//        vis[0][0] = true;
//        ratInAMaze(arr, vis,0,0, n, m,"");


        int[]a = {1,2,3};
        permute(a,0,a.length-1);

    }


        // Rat in a Maze Problem
    /*
    Base Case -> Destination (i,j = n,m)
    D L R U -> move in this fashion (get sorted order)
    Visited Matrix (to keep a track)
    */
    public static boolean isValid(int[][] arr, boolean[][] vis, int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m && arr[i][j] == 1 && !vis[i][j];
    }
    public static void ratInAMaze(int[][] arr, boolean[][] vis, int i, int j, int n, int m, String ans) {
        if (i == n - 1 && j == m - 1) { // Bottom-right corner reached
            System.out.println(ans);
            return;
        }

        // Direction: D L R U
        if (isValid(arr, vis, i + 1, j, n, m)) { // D
            vis[i + 1][j] = true;
            ratInAMaze(arr, vis, i + 1, j, n, m, ans + 'D');
            vis[i + 1][j] = false;
        }
        if (isValid(arr, vis, i, j - 1, n, m)) { // L
            vis[i][j - 1] = true;
            ratInAMaze(arr, vis, i, j - 1, n, m, ans + 'L');
            vis[i][j - 1] = false;
        }
        if (isValid(arr, vis, i, j + 1, n, m)) { // R
            vis[i][j + 1] = true;
            ratInAMaze(arr, vis, i, j + 1, n, m, ans + 'R');
            vis[i][j + 1] = false;
        }
        if (isValid(arr, vis, i - 1, j, n, m)) { // U
            vis[i - 1][j] = true;
            ratInAMaze(arr, vis, i - 1, j, n, m, ans + 'U');
            vis[i - 1][j] = false;
        }
    }



    static void permute(int[]arr , int l, int r){
        if(l==r){
            printArray(arr);
            return;
        }
        for (int i = l; i <=r; i++) {
            swap(arr,i,l);
            permute(arr,l+1,r);
            swap(arr,i,l); //backtrack
        }
    }
    static void printArray(int[]arr){
        for(int e:arr){
            System.out.print(e+" ");
        }
        System.out.println();
    }
    static void swap(int[]arr, int i, int j){
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }











}
