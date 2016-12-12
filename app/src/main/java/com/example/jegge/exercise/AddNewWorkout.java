package com.example.jegge.exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;

public class AddNewWorkout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_workout);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Exercise exercise = realm.createObject(Exercise.class);
        exercise.setName("Bänkpress");
        exercise.setDescription("Standard bröstövning");

        realm.commitTransaction();

    }
}
