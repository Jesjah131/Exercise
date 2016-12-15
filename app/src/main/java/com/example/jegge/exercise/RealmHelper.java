package com.example.jegge.exercise;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jegge on 2016-12-15.
 */
public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    //Hämta alla workouts "pass" som strängar.
    public ArrayList<String> retrieveWorkouts(){
        ArrayList<String> workoutNames = new ArrayList<>();
        RealmResults<Workout> workouts = realm.where(Workout.class).findAll();

        for(Workout w: workouts){
            workoutNames.add(w.getName());
        }
        return workoutNames;
    }
}

