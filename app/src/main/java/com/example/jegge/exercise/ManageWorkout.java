package com.example.jegge.exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmResults;

public class ManageWorkout extends AppCompatActivity {

    private ListView lv;
    private ArrayAdapter<String> listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_workout);

        populateListView();

    }

    //Populera listView med alla workouts - "pass"
    public void populateListView(){
        lv = (ListView) findViewById(R.id.listViewManageWorkout);

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Workout> wo = realm.where(Workout.class).findAll();

        ArrayList<String> workoutList = new ArrayList<String>();

        for(int i = 0; i < wo.size(); i++){
            String workout = (wo.get(i).getName()+ "\n" + wo.get(i).getExercises().size() + " övningar");
            workoutList.add(workout);
        }

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, workoutList);

        lv.setAdapter(listAdapter);
        lv.setItemsCanFocus(false);
    }
}
