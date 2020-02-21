package com.company;

public class PairBST {


    //insert:

    private PairTreeNode root;

    public void insert(String key, int val){
        Pair pair = new Pair(key, val);
        root = insertHelper(root, pair);
    }


    private PairTreeNode insertHelper(PairTreeNode root, Pair pair){
        if (root == null){
            return new PairTreeNode(pair);
        }
        if (pair.key.compareTo(root.pair.key)<0 ){
            root.left=insertHelper(root.left, pair);
        }else{
            root.right=insertHelper(root.right, pair);
        }
        return root;
    }

    // test(traverse):
//    public void traverse(){
//        traverseHelper(root);
//    }
//    private void traverseHelper(TreeNode root){
//        if (root == null){
//            return;
//        }
//        traverseHelper(root.left);
//        System.out.println(root.val);
//        traverseHelper(root.right);
//    }

    //search:

    public Integer search(String key){
        return searchHelper(root, key);
    }

    public Integer searchHelper(PairTreeNode root, String key){
        if (root == null){
            return null;
        }
        if (key.equals(root.pair.key)){
            return root.pair.value;
        }else if (key.compareTo(root.pair.key)<0){
            return searchHelper(root.left, key);
        }else{
            return searchHelper(root.right, key);
        }
    }


}

class PairTreeNode {
    public Pair pair;
    public PairTreeNode left;
    public PairTreeNode right;

    public PairTreeNode(Pair pair) {
        this.pair = pair;
    }
}