package com.company;

public class BST {


    //insert:

    private TreeNode root;

    public void insert(int val){
        root = insertHelper(root, val);
    }

    private TreeNode insertHelper(TreeNode root, int val){
        if (root == null){
            return new TreeNode(val);
        }
        if (val<root.val){
            root.left=insertHelper(root.left, val);
        }else{
            root.right=insertHelper(root.right, val);
        }
        return root;
    }

    // test(traverse):
    public void traverse(){
        traverseHelper(root);
    }
    private void traverseHelper(TreeNode root){
        if (root == null){
            return;
        }
        traverseHelper(root.left);
        System.out.println(root.val);
        traverseHelper(root.right);
    }

    //search:

    public boolean search(int val){
        return searchHelper(root, val);
    }

    public boolean searchHelper(TreeNode root, int val){
        if (root == null){
            return false;
        }
        if (val==root.val){
            return true;
        }else if (val < root.val){
            return searchHelper(root.left, val);
        }else{
            return searchHelper(root.right, val);
        }
    }





}
