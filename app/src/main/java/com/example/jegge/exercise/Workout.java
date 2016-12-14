package com.example.jegge.exercise;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jegge on 2016-12-12.
 */
public class Workout extends RealmObject {
    @PrimaryKey
    private long wid;
    private String name;
   // private String startTime;
   // private String endTime;
   // private String date;
    public RealmList<Exercise> exercises;

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
    //STARTTIME
    public RealmList<Exercise> getExercises(){
        return exercises;
    }

    public void setExercises(RealmList<Exercise> exercises){
        this.exercises = exercises;
    }
    /*
    //ENDTIME
    public String getEndTime(){
        return endTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    //DATE
    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }*/

}
