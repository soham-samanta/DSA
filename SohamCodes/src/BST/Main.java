package BST;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

class node {
    int val;
    node left,right;
    public node(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}
public class Main {
    public static void main(String[] args) {
        node root = new node(15);
        root.left=new node(10);
        root.left.left=new node(5);
        root.right=new node(20);
        root.right.left = new node(18);
        root.right.left.right = new node(19);
        root.right.right = new node(28);
            /*
                         15
                        / \
                      10   20
                     /    /  \
                   5    18   28
                          \
                           19
             */


//        System.out.println(inOrderIterative(root));
//        System.out.println(printInRange(root,17,22));
        System.out.println(kthSmallest(root,4));
    }

    public node searchBST(node root, int k){
        if(root==null) return null;
        if(k<root.val){
            return searchBST(root.left,k);
        }
        if(k>root.val){
            return searchBST(root.right,k);
        }
        return root;
    }

    public node insert(node root, int k){
        if(root==null){
            return new node(k);
        }
        if(k>root.val){
            root.right=insert(root.right,k);
        } else if(k<root.val){
            root.left=insert(root.left,k);
        }
        return root;
    }

    /*
        delete - Case 3 ->
            1. Get the inorder successor
            2. Replace the node with inorder successor
            3. remove the inorder successor from its original position
     */
    public node delete(node root, int k){
        if(root==null) return null;
        if(k<root.val){
            root.left=delete(root.left,k);
        }else if(k>root.val){
            root.right=delete(root.right,k);
        }else{ // root.val == key (reached)
            if(root.left==null) return root.right;
            if(root.right==null) return  root.left;

            node inOrderSucc = findSmallest(root.right);
            root.val=inOrderSucc.val;
            root.right=delete(root.right,inOrderSucc.val);
        }
        return root;
    }
    static node findSmallest(node root){
        node temp=root;
        while(temp.left!=null){
            temp=temp.left;
        }
        return temp;
    }

    public boolean isValidBST(node root){
        return validBSThelper(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private boolean validBSThelper(node root, int min, int max) {
        if(root==null) return true;
        if(root.val<=min || root.val>=max) return false;

        return validBSThelper(root.left,min,root.val)
                && validBSThelper(root.right, root.val, max);
    }

    public static ArrayList<Integer> inOrderIterative(node root){
        ArrayDeque<node>st=new ArrayDeque<>();
        ArrayList<Integer>ans = new ArrayList<>();
        if(root==null) return ans;
        node curr = root;
        while(!st.isEmpty() || curr!=null){
            while(curr!=null){
                st.push(curr);
                curr=curr.left;
            }
            node temp = st.pop();
            ans.add(temp.val);
            curr=temp.right;
        }
        return ans;
    }

    // Easy Way -> inOrder Traversal & store in Array then basic 2Sum Qs
    static boolean twoSumIV(node root, int k){
        boolean done1=false, done2=false;
        int val1=0, val2=0;
        node curr1=root, curr2=root;
        ArrayDeque<node> st1 = new ArrayDeque<>();
        ArrayDeque<node> st2 = new ArrayDeque<>();

        while(true){
            // LNR
            while(done1==false){
                if(curr1!=null){
                    st1.push(curr1);
                    curr1=curr1.left;
                }else{
                    if(st1.isEmpty()){
                        done1=true;
                    }else{
                        node temp= st1.pop();
                        val1=temp.val;
                        curr1=temp.right;
                        done1=true;
                    }
                }
            }
            // RNL
            while(done2==false){
                if(curr2!=null){
                    st2.push(curr2);
                    curr2=curr2.right;
                }else{
                    if(st2.isEmpty()){
                        done2=true;
                    }else{
                        node temp= st2.pop();
                        val2=temp.val;
                        curr2=temp.left;
                        done2=true;
                    }
                }
            }

            // if you are here then done1 & done2 are true
            if(val1!=val2 && val1+val2==k){
                return true;
            }else if(val1+val2<k){
                done1=false;
            } else if(val1+val2>k) {
                done2=false;
            }
            if(val1>=val2) return false;
        }
    }


    static node createBalancedBSTfromSortedArray(int[]arr, int l, int r){
        if(l>r) return null;
        int m = (l+r)/2;
        node root = new node(arr[m]);
        root.left=createBalancedBSTfromSortedArray(arr,l,m-1);
        root.right=createBalancedBSTfromSortedArray(arr,m+1,r);
        return root;
    }

    static node createBSTfromPreOrder(int[]arr, int l, int r){
        if(l>r) return null;
        node root = new node(arr[l]);
        int i=l+1;
        for(;i<r;i++){
            if(arr[i]>arr[l]){
                break;
            }
        }
        root.left = createBSTfromPreOrder(arr,l+1,i-1);
        root.right = createBSTfromPreOrder(arr,i,r);
        return root;
    }

    // -- AVL --- Not Coded

    static ArrayList<Integer> printInRange(node root, int l, int h){
        ArrayList<Integer>ans = new ArrayList<>();
        printInRangeHelper(root,l,h,ans);
        return ans;
    }
    static void printInRangeHelper(node root, int l, int h,ArrayList<Integer>ans){ // low,high
        if(root==null) return; // inOrder Code
        if(l< root.val) printInRangeHelper(root.left,l,h,ans);
        if(root.val>l && root.val<h) ans.add(root.val);
        if(h> root.val) printInRangeHelper(root.right,l,h,ans);
    }

    public static int rangeSumBST(node root, int l, int h) {
        if(root==null) return 0;
        int ans = 0;

        if(l<root.val) ans+=rangeSumBST(root.left,l,h);
        if(root.val>=l && root.val<=h) ans+=root.val;
        if(h>root.val) ans+=rangeSumBST(root.right,l,h);

        return ans;
    }


    static node prevv = null;
    static int ans =Integer.MAX_VALUE;
    static int minAbsDiff(node root){
        // Reset static variables for each test case
        prevv = null;
        ans = Integer.MAX_VALUE;

        minAbsBSTUtil(root);
        return ans;
    }
    static void minAbsBSTUtil(node root){ // inOrder
        if(root==null) return;

        minAbsBSTUtil(root.left);
        if(prevv !=null){
            ans = Math.min(ans, root.val- prevv.val);
        }
        prevv =root;
        minAbsBSTUtil(root.right);
    }


    static node prev, left, mid, right;
    public static void recoverBST(node root) {
        prev = left = mid = right = null; // Reset global variables

        recoverUtil(root);
        if (left != null && right != null) { // Non adjacent
            swap(left, right);
        }
        if (left != null && right == null) { //Two adjacent
            swap(left, mid);
        }
    }
    private static void swap(node x, node y) {
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }
    static void recoverUtil(node root) { // InOrder
        if (root == null) return;

        recoverUtil(root.left);
        if (prev != null && root.val < prev.val) {
            if (left == null) { // 1 anomaly
                left = prev;
                mid = root;
            } else { // 2 anomaly
                right = root;
            }
        }
        prev = root; // Update prev for next comparison
        recoverUtil(root.right);
    }


    // ---- HomeWork + Assignment

    /*imp*/
    public static boolean canRepresentBST(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int lastPopped = Integer.MIN_VALUE;  // Tracks the last popped element
        for (int e : arr) {
            // If we find a smaller element in the right subtree, it's not a BST
            if (e < lastPopped) {
                return false;
            }
            // Pop from stack until we find the correct parent for arr[i]
            while (!st.isEmpty() && e > st.peek()) {
                lastPopped = st.pop();
            }
            st.push(e);
        }
        return true;
    }

    public static int findFloor(node root, int x) {
        int ans = -1; // Default value if no floor exists
        while (root != null) {
            if (root.val == x) {
                return root.val; // Exact match, return x
            } else if (root.val < x) {
                ans = root.val; // Possible floor found
                root = root.right; // Move right to find a closer value
            } else {
                root = root.left; // Move left as current value is greater than x
            }
        }
        return ans;
    }

    public static int findCeil(node root, int x) {
        int ans = -1; // Default value if no Ceil exists
        while (root != null) {
            if (root.val == x) {
                return root.val; // Exact match, return x
            } else if (root.val > x) {
                ans = root.val; // Possible Ceil found
                root = root.left; // Move left to find a closer value
            } else {
                root = root.right; // Move right as current value is smaller than x
            }
        }
        return ans;
    }


/*
    public static int findFloorRec(node root, int x) {
        return findFloorHelper(root, x, -1);
    }
    private static int findFloorHelper(node root, int x, int ans) {
        if (root == null) return ans;
        if (root.val == x) return root.val; // Exact match
        if (root.val < x) {
            // Store current node as potential floor & move right
            return findFloorHelper(root.right, x, root.val);
        } else {
            // Move left as current value is greater than x
            return findFloorHelper(root.left, x, ans);
        }
    }

    public static int findCeilRec(node root, int x) {
        return findCeilHelper(root, x, -1);
    }
    private static int findCeilHelper(node root, int x, int ans) {
        if (root == null) return ans;
        if (root.val == x) return root.val; // Exact match
        if (root.val > x) {
            // Store current node as potential ceil & move left
            return findCeilHelper(root.left, x, root.val);
        } else {
            // Move right as current value is smaller than x
            return findCeilHelper(root.right, x, ans);
        }
    }

*/

    public node increasingBST(node root){
        if(root==null) return null;

        ArrayList<Integer> ans=new ArrayList<>();
        inOrderHelp(root,ans);

        node newRoot = new node(ans.getFirst());
        node curr=newRoot;

        for(int i=1;i<ans.size();i++){
            curr.right=new node(ans.get(i));
            curr=curr.right;
        }
        return newRoot;

    }
    public void inOrderHelp(node root,ArrayList<Integer> ans){
        if(root==null) return;
        inOrderHelp(root.left,ans);
        ans.add(root.val);
        inOrderHelp(root.right,ans);
    }

    // ***  optimised solution  -->
    private node curr; // Pointer to track rightmost node
    public node increasingBSTop(node root) {
        node dummy = new node(0); // Dummy node
        curr = dummy;
        inOrder(root);
        return dummy.right; // Return new root
    }
    private void inOrder(node root) {
        if (root == null) return;

        inOrder(root.left); // Left subtree

        // Process current node
        root.left = null;  // Remove left child
        curr.right = root; // Attach current node to right
        curr = root;       // Move forward

        inOrder(root.right); // Right subtree
    }

    public static int kthSmallest(node root, int k){
        if(root==null) return -1;
        ArrayList<Integer>ans = new ArrayList<>();
        inorder(root,ans);

        // Check if k is within valid range
        if (k > ans.size() || k <= 0) return -1;

        return ans.get(k-1);
    }
    public static void inorder(node root, ArrayList<Integer>ans){
        if(root==null) return;
        inorder(root.left,ans);
        ans.add(root.val);
        inorder(root.right,ans);
    }

    // optimised code --> Kth Small Element
    public static int kthSmallestOp(node root, int k) {
        Stack<node> st = new Stack<>();
        node curr = root;
        int cnt = 0; //
        while (!st.isEmpty() || curr != null ) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            node temp = st.pop();
            cnt++; //
            if (cnt == k) return temp.val; // Found kth smallest element
            curr = temp.right;
        }
        return -1; // If k is out of bounds
    }










}
