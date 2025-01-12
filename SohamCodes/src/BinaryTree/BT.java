package BinaryTree;

import java.util.*;

class node{
    int val;
    node left,right;
    public node(int val) {
        this.val = val;
    }
}

public class BT {
    public static void main(String[] args) {
        node root = new node(5);
        root.left=new node(10);
        root.left.left=new node(45);
        root.right=new node(15);
        root.right.left = new node(11);
        root.right.left.right = new node(23);
        root.right.right = new node(13);
            /*
                         5
                        / \
                      10   15
                     /    /  \
                   45    11   13
                          \
                           23
             */

//        inOrder(root);
//        System.out.println();
//        preOrder(root);
//        System.out.println();
//        postOrder(root);
//        System.out.println(minInTree(root));
//        System.out.println(heightOfBT(root));
//        System.out.println(areCousin(root,11,45));
//        ArrayList<Integer>ans=new ArrayList<>();
//        nodesAtLevelK(root,2,ans);
//        System.out.println(ans);
//        System.out.println(nodesAtLevelKIterative(root,2));
//        rightView(root,0,ans);
//        ans=levelOrder(root);
//        System.out.println(ans);
//        ArrayList<ArrayList<Integer>>ans = levelOrder2(root);
//        System.out.println(ans);
//        System.out.println(verticalOrder(root));
//        System.out.println(topView(root));
//        System.out.println(diameter(root));
//        System.out.println(diameterOfBT(root));
//        System.out.println(ancestor(root,23));
//        node ans = lca(root,23,13);
//        System.out.println(ans.val);
//        System.out.println(distBet2Node(root,23,45));
        System.out.println(distKfromTarget(root,11,1));


    }

    public static void inOrder(node root){
        if(root==null) return;
        inOrder(root.left);
        System.out.print(root.val+" ");
        inOrder(root.right);
    }

    public static void preOrder(node root){
        if(root==null) return;
        System.out.print(root.val+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void postOrder(node root){
        if(root==null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val+" ");
    }

    public static int minInTree(node root){
        if(root==null) return Integer.MAX_VALUE;
        int minFromLeft = minInTree(root.left);
        int minFromRight = minInTree(root.right);
        return Math.min(Math.min(minFromLeft,minFromRight),root.val);
    }

    public static int heightOfBT(node root){
        if(root==null) return 0;
        int lh = heightOfBT(root.left);
        int rh = heightOfBT(root.right);
        return Math.max(lh,rh)+1;
    }

    /////// *** Imp ***
    static node xPar , yPar;
    static int xLevel , yLevel;
    public static void areCousinHelper(node root, node par, int x, int y, int level){
        if(root==null) return;
        if(root.val==x){
            xPar = par;
            xLevel = level;
        }
        if(root.val==y){
            yPar = par;
            yLevel = level;
        }
        areCousinHelper(root.left,root,x,y,level+1);
        areCousinHelper(root.right,root,x,y,level+1);
    }
    public static boolean areCousin(node root, int x, int y){
        areCousinHelper(root,null,x,y,0);
        boolean sameLevel = xLevel==yLevel;
        boolean diffParent = xPar!=yPar;
        return sameLevel && diffParent;
    }

    // Nodes present at k distance from root -->
    //                                      (VARIATION) node at k dist from any node
    public static void nodesAtLevelK(node root, int k, ArrayList<Integer>ans){// k->level
        if(root==null || k<0)return;
        if(k==0){
            ans.add(root.val);
            return;
        }
        nodesAtLevelK(root.left,k-1,ans);
        nodesAtLevelK(root.right,k-1,ans);
    }

    static class Pair{
        node x;
        int level;
        public Pair(node x, int level) {
            this.x = x;
            this.level = level;
        }
    }
    public static ArrayList<Integer>  nodesAtLevelKIterative(node root, int level){
        ArrayList<Integer>ans=new ArrayList<>();
        ArrayDeque<Pair>stack=new ArrayDeque<>();

        Pair first = new Pair(root,level);
        stack.push(first);

        while(!stack.isEmpty()){
            Pair curr = stack.poll();
            if(curr.x==null) continue;
            if(curr.level==0){
                ans.add(curr.x.val);
                continue;
            }

            stack.push(new Pair(curr.x.right,curr.level-1));
            stack.push(new Pair(curr.x.left,curr.level-1));

        }
        return ans;
    }

    public static void rightView(node root, int level, ArrayList<Integer>ans){
        if(root==null) return;
        if(level>=ans.size()){
            ans.add(root.val);
        }
        rightView(root.right,level+1,ans);
        rightView(root.left,level+1,ans);
    }


    //------------------------------------------------------------------------------
    public static ArrayList<Integer> levelOrder(node root){
        ArrayList<Integer>ans= new ArrayList<>();
        Queue<node>q = new LinkedList<>();
        if(root==null) return ans;
        q.add(root);
        while(!q.isEmpty()){
            node curr = q.poll();
            ans.add(curr.val);
            if(curr.left!=null) q.add(curr.left);
            if(curr.right!=null) q.add(curr.right);
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> levelOrder2(node root){ // [ [],[],[] ]
        ArrayList<ArrayList<Integer>>ans= new ArrayList<>();
        Queue<node>q = new LinkedList<>();
        if(root==null) return ans;
        q.add(root);
        q.add(null); //
        ArrayList<Integer>temp=new ArrayList<>(); //
        while(!q.isEmpty()){
            node curr = q.poll();
            if(curr==null){
                ans.add(temp);
                if(q.isEmpty()) break;
                temp=new ArrayList<>();
                q.add(null);
            }else{
                temp.add(curr.val);
                if(curr.left!=null) q.add(curr.left);
                if(curr.right!=null) q.add(curr.right);
            }
        }
        return ans;
    }

    // Vertical Order Traversal
    static class Pairr{
        int hd; // horizontal distance
        node Node;
        public Pairr(int hd, node node) {
            this.hd = hd;
            Node = node;
        }
    }
    public static ArrayList<ArrayList<Integer>> verticalOrder(node root){
        Queue<Pairr> q = new LinkedList<>();
        TreeMap<Integer,ArrayList<Integer>>map=new TreeMap<>();

        q.add(new Pairr(0,root));

        while(!q.isEmpty()){
            Pairr curr = q.poll();

            if(!map.containsKey(curr.hd)){
                map.put(curr.hd,new ArrayList<>());
            }
            map.get(curr.hd).add(curr.Node.val); //

            if(curr.Node.left!=null){
                q.add(new Pairr(curr.hd-1,curr.Node.left));
            }
            if(curr.Node.right!=null){
                q.add(new Pairr(curr.hd+1,curr.Node.right));
            }
        }
        return new ArrayList<>(map.values());
    }

    public static ArrayList<Integer>topView(node root){
        Queue<Pairr> q = new LinkedList<>();
        TreeMap<Integer,Integer>map=new TreeMap<>();

        q.add(new Pairr(0,root));

        while(!q.isEmpty()){
            Pairr curr = q.poll();
//            if(!map.containsKey(curr.hd)){
//                map.put(curr.hd,curr.Node.val); //
//            }
            map.putIfAbsent(curr.hd,curr.Node.val);
//            map.get(curr.hd).add(curr.Node.val);

            if(curr.Node.left!=null){
                q.add(new Pairr(curr.hd-1,curr.Node.left));
            }
            if(curr.Node.right!=null){
                q.add(new Pairr(curr.hd+1,curr.Node.right));
            }
        }
        return new ArrayList<>(map.values());
    }

    public static int diameter(node root){ // O(N^2)  -> calling height for each node
        if(root==null) return 0;
        int lh = heightOfBT(root.left);
        int rh = heightOfBT(root.right);

        int diameterMiddle = lh+rh;
        int ld = diameter(root.left);
        int rd = diameter(root.right);

        return Math.max(Math.max(ld,rd),diameterMiddle);
    }

    // Can be solved by modifying height code only -> Diameter = LeftHeight + RightHeight
    public static int diameter = 0;
    public static int diameterOfBT(node root){
        if(root==null) return 0;
        int lh = diameterOfBT(root.left);
        int rh = diameterOfBT(root.right);
        diameter = Math.max(diameter,lh+rh); // extra
        return Math.max(diameter,(Math.max(lh,rh)+1));
    }

    public static ArrayList<Integer> ancestor(node root,int target){
        ArrayList<Integer>ans = new ArrayList<>();
        ancestorHelper(root,ans,target);
        return ans;
    }
    public static boolean ancestorHelper(node root, ArrayList<Integer>ans, int k){
        if(root==null) return false;
        if(root.val==k) return true;
        boolean isPresentLeft = ancestorHelper(root.left,ans,k);
        boolean isPresentRight = ancestorHelper(root.right,ans,k);
        if(isPresentLeft || isPresentRight){
            ans.add(root.val);
            return true;
        }
        return false;
    }

    public static node lca(node root, int a , int b){
        if(root==null) return null;
        if(root.val==a || root.val==b) return root;

        node left = lca(root.left,a,b);
        node right = lca(root.right,a,b);

        if(left==null) return right;
        if(right==null) return left;

        return root;
    }

    // dist(lca,a) + dist(lca,b) --> one way
    // other way -> dist(root,a) + dist(root,b) - 2 * dist(root,lca)
    public static int distBet2Node(node root, int a , int b){
        node lca = lca(root,a,b);
        Integer distA = dist(root,a);
        Integer distB = dist(root,b);
        Integer distLCA = dist(root,lca.val);

        return distA+distB-(2*distLCA);

    }
    public static Integer dist(node root, int x){
        if(root==null) return null;
        if(root.val==x) return 0;

        Integer leftDist = dist(root.left,x);
        Integer rightDist = dist(root.right,x);

        if(leftDist==null && rightDist==null) return null;

        if(leftDist==null) return rightDist+1;
        if(rightDist==null) return leftDist+1;

        return null;
    }



    // Nodes Distance K in a Binary Tree Code ->    ***** IMP ******
    static void distKdown(node root, int k, List<Integer>ans){//same->nodesAtLevelK
        if(root==null || k<0) return;
        if(k==0){
            ans.add(root.val);
            return;
        }
        distKdown(root.left,k-1,ans);
        distKdown(root.right,k-1,ans);
    }
    static List<Integer> distKfromTarget(node root, int target, int k){
        List<Integer>ans = new ArrayList<>();
        distKfromTarget(root,target,k,ans);
        return ans;
    }
    // return value is the distance of target from the root node
    static int distKfromTarget(node root, int target, int k, List<Integer>ans){
        if(root==null) return -1;

        if(root.val==target){
            distKdown(root,k,ans);
            return 0;
        }

        // dl -> dist of left node from target (if -1 -> no node found)
        int dl = distKfromTarget(root.left,target,k,ans);
        if(dl!=-1){
            if(dl+1==k){
                ans.add(root.val);
            }
            distKdown(root.right,k-dl-2,ans);
            return dl+1;
        }
        // dr -> dist of right node from target (if -1 -> no node found)
        int dr = distKfromTarget(root.right,target,k,ans);
        if(dr!=-1){
            if(dr+1==k){
                ans.add(root.val);
            }
            distKdown(root.left,k-dr-2,ans);
            return dr+1;
        }
        return -1;
    }


    // Convert BT -> Doubly LL              *** IMP ***

    // 1way-> 'InOrder' Traversal -> store in doubly LL (Solved)
    // but needs to be solved inPlace -> Modify tree structure
    // 2way-> root -> left -> right-right-right-null x (predecessor)
    //             -> right -> left-left-left-null x (successor)
    static node convert(node root){
        if(root==null) return null;
        convertBT2DLL(root);
        while (root.left!=null){
            root = root.left;
        }
        return root;
    }
    static node convertBT2DLL(node root){
        if(root==null) return null;

        if(root.left!=null){
            node leftRoot = convertBT2DLL(root.left);
            while(leftRoot.right!=null){
                leftRoot = leftRoot.right;
            }
            leftRoot.right = root;
            root.left = leftRoot;
        }

        if(root.right!=null){
            node rightRoot=convertBT2DLL(root.right);
                while(rightRoot.left!=null){
                    rightRoot=rightRoot.left;
                }
                rightRoot.left = root;
                root.right = rightRoot;
            }

        return root;
    }








}


