package com.example.jegge.exercise;

import io.realm.RealmObject;

/**
 * Created by Jegge on 2016-12-12.
 */
public class Category extends RealmObject {
    private long cid;
    private String name;

    //EID
    public long getCid(){
        return cid;
    }

    public void setCid(long cid){
        this.cid = cid;
    }
    //NAME
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
