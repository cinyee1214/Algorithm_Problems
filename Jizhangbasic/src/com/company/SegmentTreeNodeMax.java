package com.company;

public class SegmentTreeNodeMax {
    public int start, end, max;
    public SegmentTreeNodeMax left, right;
    public SegmentTreeNodeMax(int start, int end, int max){
        this.start = start;
        this.end = end;
        this.max = max;
        this.left = this.right =null;
    }

}

class RangeMax{
    public SegmentTreeNodeMax build(int start, int end, int[] A){
        if (start>end){
            return null;
        }
        if (start==end){
            return new SegmentTreeNodeMax(start, end, A[start]);
        }
        SegmentTreeNodeMax root = new SegmentTreeNodeMax(start, end, A[start]);
        int mid = start +(end-start)/2;
        root.left = build(start, mid, A);
        root.right = build(mid+1, end, A);
        root.max = Math.max(root.left.max, root.right.max);
        return root;
    }
    public void modify(SegmentTreeNodeMax root, int index, int value){
        if (root.start ==index && root.end == index){
            root.max = value;
            return;
        }
        int mid = root.start +(root.end-root.start)/2;
        if(index>=root.start && index <=mid){
            modify(root.left, index, value);
        }
        if(index<=root.end && index > mid){
            modify(root.right, index, value);
        }
        root.max = Math.max(root.right.max, root.left.max);
    }
    public int query(SegmentTreeNodeMax root, int start, int end){
        if (start==root.start && end == root.end){
            return root.max;
        }
        int mid = root.start +(root.end-root.start)/2;
        int leftmax = Integer.MIN_VALUE, rightmax = Integer.MIN_VALUE;
        if (end <=mid){
            leftmax = query(root.left, start, end);
        }
        if(start <= mid && end > mid){
            leftmax = query(root.left, start, mid);
            rightmax = query(root.right, mid+1, end);
        }
        if (start > mid){
            rightmax = query(root.right, start, end);
        }
        return Math.max(leftmax, rightmax);
    }

}
class RangeSum{
    public SegmentTreeNodeMax build(int start, int end, int[] A){
        if (start>end){
            return null;
        }
        if (start==end){
            return new SegmentTreeNodeMax(start, end, A[start]);
        }
        SegmentTreeNodeMax root = new SegmentTreeNodeMax(start, end, A[start]);
        int mid = start +(end-start)/2;
        root.left = build(start, mid, A);
        root.right = build(mid+1, end, A);
        root.max = root.left.max+root.right.max;
        return root;
    }
    public void modify(SegmentTreeNodeMax root, int index, int value){
        if (root.start ==index && root.end == index){
            root.max = value;
            return;
        }
        int mid = root.start +(root.end-root.start)/2;
        if(index>=root.start && index <=mid){
            modify(root.left, index, value);
        }
        if(index<=root.end && index > mid){
            modify(root.right, index, value);
        }
        root.max = root.right.max+ root.left.max;
    }
    public int query(SegmentTreeNodeMax root, int start, int end){
        if (start==root.start && end == root.end){
            return root.max;
        }
        int mid = root.start +(root.end-root.start)/2;
        int leftsum = 0, rightsum = 0;
        if (end <=mid){
            leftsum = query(root.left, start, end);
        }
        if(start <= mid && end > mid){
            leftsum = query(root.left, start, mid);
            rightsum = query(root.right, mid+1, end);
        }
        if (start > mid){
            rightsum = query(root.right, start, end);
        }
        return leftsum+rightsum;
    }

}
