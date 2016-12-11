package com.example.jegge.exercise;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jegge on 2016-12-11.
 */
public class Exercise extends RealmObject {
    @PrimaryKey
    private long eid;
    private String name;
    private String description;

//EID
    public long getEid(){
        return eid;
    }

    public void setEid(long eid){
        this.eid = eid;
    }
//NAME
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
//DESCRIPTION
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
