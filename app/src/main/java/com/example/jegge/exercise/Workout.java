package com.example.jegge.exercise;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jegge on 2016-12-12.
 */
public class Workout extends RealmObject {
    @PrimaryKey
    private long wid;
    private String name;

    //WID
    public long getEid(){
        return wid;
    }

    public void setEid(long wid){
        this.wid = wid;
    }
    //NAME
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
