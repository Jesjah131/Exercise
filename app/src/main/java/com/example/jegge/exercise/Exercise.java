package com.example.jegge.exercise;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jegge on 2016-12-11.
 */
public class Exercise extends RealmObject {
    @PrimaryKey
    private int eid;
    private String name;
    private String description;
   // private int reps;
   // private String duration;
    // public RealmList<Category> categories;

//EID
    public int getEid(){
        return eid;
    }

    public void setEid(int eid){
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
    public String getDescription(){return description;}

    public void setDescription(String description){
        this.description = description;
    }
    //REPS
    /*
    public int getReps(){
        return reps;
    }

    public void setReps(int reps){
        this.reps = reps;
    }
    //DURATION
    public String getDuration(){
        return duration;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }*/
}

