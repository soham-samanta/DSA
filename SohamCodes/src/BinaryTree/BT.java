package BinaryTree;

import java.lang.reflect.Array;
import java.util.*;

class node {
    int val;
    node left,right;
    public node(int val) {
        this.val = val;
        left = null;
        right = null;
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

//        System.out.println(findMaxSubtree(root));

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
//        System.out.println(rightSideView(root));
//        ans=levelOrder(root);
//        System.out.println(ans);
//        ArrayList<ArrayList<Integer>>ans2 = levelOrder2(root);
//        System.out.println(ans2);
//        System.out.println(verticalOrder(root));
//        System.out.println(topView(root));
//        System.out.println(diameter(root));
//        System.out.println(diameterOfBT(root));
//        System.out.println(ancestor(root,23));
//        node x = lca(root,23,13);
//        System.out.println(x.val);
//        System.out.println(distBet2Node(root,23,45));
//        System.out.println(distKfromTarget(root,11,1));
        System.out.println(boundaryTraversal(root));


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

    /////// *** Imp ***  -> same level && diff parents
    static node xPar , yPar;
    static int xLevel , yLevel;
    public static boolean areCousin(node root, int x, int y){
        areCousinHelper(root,null,x,y,0);
        boolean sameLevel = xLevel==yLevel;
        boolean diffParent = xPar!=yPar;
        return sameLevel && diffParent;
    }
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


    // Nodes present at k distance from root -->
    //                           (VARIATION) node at k dist from any node
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
        node Node;
        int level;
        public Pair(node x, int level) {
            this.Node = x;
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
            if(curr.Node ==null) continue;
            if(curr.level==0){
                ans.add(curr.Node.val);
                continue;
            }

            stack.push(new Pair(curr.Node.right,curr.level-1));
            stack.push(new Pair(curr.Node.left,curr.level-1));

        }
        return ans;
    }

    public static List<Integer> rightSideView(node root) {
        ArrayList<Integer>ans=new ArrayList<>();
        rightViewHelper(root,0,ans);
        return ans;
    }
    public static void rightViewHelper(node root, int level, ArrayList<Integer>ans){
        if(root==null) return;
        if(level>=ans.size()){
            ans.add(root.val);
        }
        rightViewHelper(root.right,level+1,ans);
        rightViewHelper(root.left,level+1,ans);
    }

    public static List<List<Integer>> lOrder(node root){
        List<List<Integer>>ans = new ArrayList<>();
        Queue<node>q = new LinkedList<>();
        if(root==null) return ans;
        q.add(root);
        q.add(null); //
        ArrayList<Integer>temp = new ArrayList<>();
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

    public static ArrayList<ArrayList<Integer>> levelOrder2(node root){// [ [],[],[] ]
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
                if(q.isEmpty()) break;  // prevents adding an extra null at the end of traversal.
                temp=new ArrayList<>(); //
                q.add(null);            //
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

        int diameterMiddle = lh+rh; // diameter = lh + rh

        int ld = diameter(root.left);
        int rd = diameter(root.right);

        return Math.max(Math.max(ld,rd),diameterMiddle);
    }

    // Can be solved by modifying (height code) only -> Diameter = LeftHeight + RightHeight
    public static int diameter = 0;
    public static int diameterOfBT(node root){
        if(root==null) return 0;
        int lh = diameterOfBT(root.left);
        int rh = diameterOfBT(root.right);
        diameter = Math.max(diameter,lh+rh); // extra
        return Math.max(diameter,(Math.max(lh,rh)+1));
    }

    public static ArrayList<Integer> ancestor(node root, int target){
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

    public static node lca(node root, int a , int b){  // *** IMP ***
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
    public static Integer dist(node root, int x){ // Integer coz -> use of null
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
    static void distKdown(node root, int k, List<Integer>ans){//same code->nodesAtLevelK
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



    //////////////////////////// HomeWork ///////////////////////////////////

    public static boolean hasPathSum(node root, int targetSum) {
        // Base case: if the root is null, no path exists
        if (root == null) return false;

        // Check if we are at a leaf node
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Recursively check the left and right subtrees, reducing targetSum by the current node's value
        boolean left = hasPathSum(root.left, targetSum - root.val);
        boolean right = hasPathSum(root.right, targetSum - root.val);

        // If any path matches, return true
        return left || right;
    }

    public static ArrayList<ArrayList<Integer>> ZigZag(node root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Queue<node> q = new LinkedList<>();
        if (root == null) return ans;
        q.add(root);
        q.add(null);
        ArrayList<Integer> temp = new ArrayList<>();
        boolean leftToRight = true; // Start with left-to-right traversal

        while (!q.isEmpty()) {
            node curr = q.poll();
            if (curr == null) {
                if (!leftToRight) Collections.reverse(temp); // Reverse the level if needed
                ans.add(temp);
                if (q.isEmpty()) break;
                temp = new ArrayList<>();
                q.add(null);
                leftToRight = !leftToRight; // Toggle direction
            } else {
                temp.add(curr.val);
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }
        return ans;
    }

    public static List<String> binaryTreePaths(node root) {
        List<String> ans = new ArrayList<>();
        String s = "";
        traverseTreeHelper(root,s,ans);
        return ans;
    }
    static void traverseTreeHelper(node root, String s, List<String> ans) {
        if (root == null) return;
        s += root.val;
        if (root.left == null && root.right == null) { // leaf
            ans.add(s);
        } else {
            s += "->";
            traverseTreeHelper(root.left, s, ans);
            traverseTreeHelper(root.right, s, ans);
        }
    }










    /////////////////////////////// Assignment ///////////////////////////////////

    private static int ans = Integer.MIN_VALUE;
    public static int findMaxSubtree(node root) {
        ans = Integer.MIN_VALUE; // Reset for new tree
        findMaxSubtreeHelper(root);
        return ans;
    }
    public static int findMaxSubtreeHelper(node root){
        if(root==null) return 0;
        int leftMax = findMaxSubtreeHelper(root.left);
        int rightMax = findMaxSubtreeHelper(root.right);
        int currSum = leftMax+rightMax+root.val;
        ans = Math.max(ans,currSum);
        return currSum;
    }

    public static boolean isSymmetric(node root){
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }
    private static boolean isMirror(node l, node r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;

        return (l.val == r.val) &&
            isMirror(l.left, r.right) &&
            isMirror(l.right, r.left);
    }

    public int minDepth(node root) {
        if (root == null) return 0;
        int ld = minDepth(root.left);
        int rd = minDepth(root.right);
        // If one of the subtrees is null,return the depth of the other subtree + 1 (current node).
        if (root.left == null || root.right == null) {
            return Math.max(ld, rd) + 1;
        }
        // If both subtrees exist, return the minimum of the two depths + 1 (current node).
        return Math.min(ld, rd) + 1; // min
    }

    public static boolean hasDuplicateValues(node root) {
        HashSet<Integer> set = new HashSet<>();
        return checkDuplicates(root, set);
    }
    private static boolean checkDuplicates(node root, HashSet<Integer> set) {
        if (root == null) return false;
        if (set.contains(root.val)) return true;
        set.add(root.val);
        return checkDuplicates(root.left, set) || checkDuplicates(root.right, set);
    }


    // ------------------------------ Hard & Medium Qs ---------------------------->
    public static ArrayList<Integer> boundaryTraversal(node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        // Step 1: Add root node to result
        ans.add(root.val);
        // Step 2: Add left boundary (excluding leaf nodes)
        addLeftBoundary(root.left, ans);
        // Step 3: Add leaf nodes
        addLeaves(root, ans);
        // Step 4: Add right boundary (excluding root and leaf nodes)
        addRightBoundary(root.right, ans);

        return ans;
    }
    private static void addLeftBoundary(node root, ArrayList<Integer> ans) {
        while (root != null) {
            if (root.left != null || root.right != null) {
                ans.add(root.val);
            }
            if(root.left != null) root = root.left;
            else root = root.right;
        }
    }
    private static void addRightBoundary(node root, ArrayList<Integer> ans) {
        ArrayList<Integer> temp = new ArrayList<>();
        while (root != null) {
            if (root.left != null || root.right != null) {
                ans.add(root.val);
            }
            if (root.right != null) root = root.right;
            else root = root.left;
        }
        // Add right boundary in reverse order
        for(int i=temp.size()-1; i>=0; i--) {
            ans.add(temp.get(i));
        }
    }
    private static void addLeaves(node root, ArrayList<Integer> ans) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            ans.add(root.val);
        }
        addLeaves(root.left, ans);
        addLeaves(root.right, ans);
    }


    public static node sufficientSubset(node root, int limit) {
        return prune(root, 0, limit);
    } // Qs -> Remove path with (sum>=limit)
    private static node prune(node root, int sum, int limit) {
        if (root == null) return null;
        sum += root.val;

        if (root.left == null && root.right == null) {    // Leaf check
            return sum >= limit ? root : null;
        }

        root.left = prune(root.left, sum, limit);
        root.right = prune(root.right, sum, limit);

        //If a Node’s Children are Removed, Remove the Node
        if (root.left == null && root.right == null) return null;
        return root;
    }

    public static boolean isBalanced(node root) {
        return checkHeight(root) != -1;
    }
    private static int checkHeight(node root) {  // same as heightOfBT code
        if (root == null) return 0;

        int lh = checkHeight(root.left);
        if (lh == -1) return -1; //
        int rh = checkHeight(root.right);
        if (rh == -1) return -1; //

        if (Math.abs(lh - rh) > 1) return -1; //
        return Math.max(lh, rh) + 1;
    }

    public static boolean checkEqualTree(node root) {
        HashSet<Integer> set = new HashSet<>();
        int totalSum = getTreeSum(root, set, root);
        if (totalSum % 2 != 0) return false; // if odd
        return set.contains(totalSum / 2);
    }
    private static int getTreeSum(node curr, HashSet<Integer> set, node root) {
        if (curr == null) return 0;

        int leftSum = getTreeSum(curr.left, set, root);
        int rightSum = getTreeSum(curr.right, set, root);
        int subtreeSum = curr.val + leftSum + rightSum;

        // Avoid considering the sum of the entire tree
        if (curr != root) {
            set.add(subtreeSum);
        }
        return subtreeSum;
    }

    public node mergeTrees(node root1, node root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        // Merge the root nodes
        node merged = new node(root1.val + root2.val);

        // Recursively merge left and right subtrees
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);

        return merged;
    }

    public int longestConsecutive(node root) {
        if (root == null) return 0;
        int ans = dfs(root, null, 0);
        if (ans <= 1) return -1;
        else return ans;
    }
    public int dfs(node curr, node parent, int len) {
        if (curr == null) return len;
        if (parent != null && curr.val == parent.val + 1) len = len + 1;
        else len = 1;

        int leftLen = dfs(curr.left, curr, len);
        int rightLen = dfs(curr.right, curr, len);

        if (parent != null && curr.val == parent.val + 1) {
            return Math.max(len, Math.max(leftLen, rightLen));
        }
        return Math.max(leftLen, rightLen);
    }

    public static node buildTreePreIn(List<Integer> preorder, List<Integer> inorder) {
        if (preorder == null || inorder == null || preorder.size() != inorder.size() || preorder.isEmpty()) {
            return null;
        }
        // Store inorder values and their indices for quick lookup
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            map.put(inorder.get(i), i);
        }
        // Recursive function to build the tree
        return constructTree(preorder, 0, preorder.size() - 1,
                map, 0, inorder.size() - 1);
    }
    private static node constructTree(List<Integer> preorder, int preStart, int preEnd,
                          Map<Integer,Integer> map, int inStart, int inEnd) {
        // Base case
        if (preStart > preEnd || inStart > inEnd) return null;

        // Get root value and create TreeNode
        int rootValue = preorder.get(preStart);
        node root = new node(rootValue);

        // Find root index in inorder array
        int rootIndex = map.get(rootValue);
        int leftSubtreeSize = rootIndex - inStart;  // Number of nodes in left subtree

        // Recursively build left and right subtrees
        root.left = constructTree(preorder, preStart + 1, preStart + leftSubtreeSize,
                map, inStart, rootIndex - 1);

        root.right = constructTree(preorder, preStart + leftSubtreeSize + 1, preEnd,
                map, rootIndex + 1, inEnd);

        return root;
    }


    public static node buildTreePostIn(List<Integer> inorder, List<Integer> postorder) {
        if (inorder == null || postorder == null || inorder.size() != postorder.size() || inorder.isEmpty()) {
            return null;
        }

        // Store inorder values and their indices for quick lookup
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            inorderMap.put(inorder.get(i), i);
        }

        // Recursive function to build the tree
        return constructTree(inorder, 0, inorder.size() - 1,
                postorder, 0, postorder.size() - 1,
                inorderMap);
    }
    private static node constructTree(List<Integer> inorder, int inStart, int inEnd,
                              List<Integer> postorder, int postStart, int postEnd,
                              Map<Integer, Integer> inorderMap) {
        // Base case
        if (postStart > postEnd || inStart > inEnd) return null;

        // Get root value from postorder (last element)
        int rootValue = postorder.get(postEnd);
        node root = new node(rootValue);

        // Find root index in inorder array
        int rootIndex = inorderMap.get(rootValue);
        int leftSubtreeSize = rootIndex - inStart;  // Number of nodes in left subtree

        // Recursively build left and right subtrees
        root.left = constructTree(inorder, inStart, rootIndex - 1,
                postorder, postStart, postStart + leftSubtreeSize - 1,
                inorderMap);

        root.right = constructTree(inorder, rootIndex + 1, inEnd,
                postorder, postStart + leftSubtreeSize, postEnd - 1,
                inorderMap);

        return root;
    }


    public static boolean isSumTree(node root) {
        if (root == null) return true; // An empty tree is a sum tree
        if (root.left == null && root.right == null) return true; // A leaf node is a sum tree

        int leftSum = sum(root.left);
        int rightSum = sum(root.right);

        // Check if current node follows sum tree property
        if (root.val == leftSum + rightSum
                && isSumTree(root.left)
                && isSumTree(root.right)) {
            return true;
        }

        return false;
    }
    private static int sum(node root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }
















}


