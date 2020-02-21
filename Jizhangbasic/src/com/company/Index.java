package com.company;

import java.util.ArrayList;
import java.util.List;

public interface Index {

    Integer get(String key);

    void put(String key, int val);

    Integer remove(String key);


}

class Pair{
    String key;
    int value;
    public Pair(String key, int value){
        this.key = key;
        this.value = value;
    }
}

class IndexArrayListImpl implements Index {
    private List<Pair> data;
    public IndexArrayListImpl(){
        data = new ArrayList<>();
    }

    @Override
    public Integer get(String key){
        for(Pair pair : data){
            if (pair.key.equals(key)){
                return pair.value;
            }
        }
        return null;    //Integer object  //*int 不能返回null
    }

    @Override
    public void put(String key, int val) {
        data.add(new Pair(key, val));
    }

    @Override
    public Integer remove(String key) {
        for (Pair pair: data){
            if (pair.key.equals(key)){
                int idx = data.indexOf(pair);
                //int idx = data.indexOf(new Pair(pair));             //no
//                int idx = data.indexOf(new Pair(pair.key, pair.value));     //Ok
                /// ????????????????????
                return data.remove(idx).value;
            }
        }
        return null;
    }
}

class IndexBSTImpl implements Index{
    private PairBST bst;
    public IndexBSTImpl(){
        bst = new PairBST();   //bst 初始化
    }

    @Override
    public Integer get(String key) {
        return bst.search(key);
    }

    @Override
    public void put(String key, int val) {
        bst.insert(key, val);
    }

    @Override
    public Integer remove(String key) {
        return 0;
    }
}



