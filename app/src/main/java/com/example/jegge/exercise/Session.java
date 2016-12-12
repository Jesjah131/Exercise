package com.example.jegge.exercise;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jegge on 2016-12-11.
 */
public class Session extends RealmObject {
    @PrimaryKey
    private long sid;
    private String name;
    //Many-to-Many
    public RealmList<Exercise> exercises;
    public RealmList<Workout> workouts;

//SID
    public long getSid(){
        return sid;
    }

    public void setSid(long sid){
        this.sid = sid;
    }
//NAME
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
