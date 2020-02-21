package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here

//        TreeNode root = buildTree();
//        preOrderTraverse(root);
//        inOrderTraverse(root);
//        postOrderTraverse(root);

//        LeafSumGetter leafSumGetter = new LeafSumGetter();
//        System.out.println(leafSumGetter.get(root));
//
//        TreeHeightGetter treeHeightGetter = new TreeHeightGetter();
//        System.out.println(treeHeightGetter.get(root));
//
//        RootToLeafPathCollector rootToLeafPathCollector = new RootToLeafPathCollector();
//        System.out.println(rootToLeafPathCollector.get(root));  //ArrayList has toString so it could be printed directly.

        runBSTExample();

    }

    public static void runHashtableExample(){
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("Alex", 1);
        hashtable.put("John", 2); // O(1)

        hashtable.get("Alex"); //1
        hashtable.remove("Alex");
        hashtable.get("Alex"); //// null

        //hashmap:
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Alex", 1);
        hashMap.put("John", 2); // O(1)

        hashMap.get("Alex"); //1
        hashMap.remove("Alex");
        hashMap.get("Alex"); //// null

    }

    public static void runBSTExample(){
        BST bst = new BST();
        bst.insert(8);
        bst.insert(4);
        bst.insert(10);
        bst.insert(3);
        bst.insert(12);
        bst.traverse();

        System.out.println(bst.search(8));
        System.out.println(bst.search(4));
        System.out.println(bst.search(10));
        System.out.println(bst.search(1));


    }

    public static TreeNode buildTree() {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(14);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(13);
        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.right = node6;

        node5.left = node7;
        node5.right = node8;

        node6.left = node9;

        return node1;


    }

    public static void preOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    public static void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrderTraverse(root.left);
        System.out.println(root.val);
        inOrderTraverse(root.right);
    }

    public static void postOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.println(root.val);
    }


    private static int sum = 0;    // 全局变量

    public static void getleafSum(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            sum += root.val;
        }
        getleafSum(root.left);
        getleafSum(root.right);
    }
}


class LeafSumGetter{
    private int sum;

    public int get(TreeNode root){
        sum = 0;
        traverse(root);
        return sum;
    }

    private void traverse(TreeNode root){
        if (root == null){
            return;
        }
        if (root.left == null && root.right == null ){
            sum +=root.val;
        }
        traverse(root.left);
        traverse(root.right);
    }
}



class TreeHeightGetter{
    private int maxDepth;
    public int get(TreeNode root){
        maxDepth =0;
        traverse(root,1);
        return maxDepth;
    }
    private void traverse(TreeNode root, int depth){
        if (root == null){
            return;
        }
        maxDepth = Math.max(depth, maxDepth);
        traverse(root.left, depth+1);
        traverse(root.right, depth+1);

    }
}


class RootToLeafPathCollector{

    private List<String> result;

    public List<String> get(TreeNode root){
        result = new ArrayList<>();
        // if root == null , valueOf cannot be used
        if (root != null) {
            traverse(root, String.valueOf(root.val));
        }
        return result;
    }
    private void traverse(TreeNode root, String curPath){
        if (root == null){
            return;
        }
        if (root.left == null && root.right == null ){
            result.add(curPath);
        }
        if (root.left != null){
            traverse(root.left, curPath+"->"+root.left.val);
        }
        if (root.right != null){
            traverse(root.right, curPath+"->"+root.right.val);
        }


    }

    //divide and conquer:
    //Lintcode 469 Same Tree
    public static boolean isIdentical(TreeNode root1, TreeNode root2){
        if(root1==null && root2 ==null){     //base case
            return true;
        }else if (root1 ==null || root2==null){
            return false;
        }

        if (root1.val!=root2.val){
            return false;
        }
        return isIdentical(root1.left, root2.left)&&isIdentical(root1.right,root2.right);
    }

    //validate balanced binary tree
    //Lintcode 93

    //isBalanced - time complexity:O(n2)
    public static boolean isBalanced(TreeNode root){
        if (root==null){
            return true;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if(Math.abs(leftHeight-rightHeight)>1){
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }
    public static int getHeight(TreeNode root){
        if (root==null){
            return 0;
        }
        return Math.max(getHeight(root.left),getHeight(root.right))+1;
    }


}










class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }
}
