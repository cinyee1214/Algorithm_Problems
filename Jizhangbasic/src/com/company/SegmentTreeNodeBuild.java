package com.company;

public class SegmentTreeNodeBuild {



    public SegmentTreeNode build(int start, int end){
        if(start>end){
            return null;
        }
        if(start == end){
            return new SegmentTreeNode(start, end);
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        int mid = start +(end-start)/2;
        root.left = build(start, mid);
        root.right = build(mid+1, end);
        return root;
    }




}
class SegmentTreeNode {
    public int start, end;
    public SegmentTreeNode left, right;
    public SegmentTreeNode(int start, int end){
        this.start = start;
        this.end = end;
        this.left = this.right =null;
    }
}
