package BinaryTree;

class TreeNode {
	int val;
	TreeNode left,right;
	public TreeNode(int data) {
		this.val = data;
		left = null;
		right = null;
	}
}

class main{
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
        root.left=new TreeNode(10);
        root.left.left=new TreeNode(45);
        root.right=new TreeNode(15);
        root.right.left = new TreeNode(11);
        root.right.left.right = new TreeNode(23);
        root.right.right = new TreeNode(13);
            /*
                         5
                        / \
                      10   15
                     /    /  \
                   45    11   13
                          \
                           23
             */

		System.out.println(hasPathSum(root,33));


	}

//	static int sum=0;
//	public static boolean hasPathSum(TreeNode root, int targetSum){
//		if(root==null) return false;
//		boolean left = hasPathSum(root.left,targetSum);
//		boolean right = hasPathSum(root.right,targetSum);
//		if(left || right){
//			sum+=root.val;
//		}
//        return sum == targetSum;
//    }

	public static boolean hasPathSum(TreeNode root, int targetSum) {
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







}