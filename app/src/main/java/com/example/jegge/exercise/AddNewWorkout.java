package com.example.jegge.exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmResults;


public class AddNewWorkout extends AppCompatActivity {

public int nextID;
    private ListView lv;
    private ArrayAdapter<String> listAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_workout);


        addStandardExercises();

        populateListView();


    }

    //Populera listview med övningar - "exercises"
    public void populateListView(){
        lv = (ListView) findViewById(R.id.listViewExercises);

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Exercise> ex = realm.where(Exercise.class).findAll();
        ex.sort("name");

        String[] planets = new String[] { ex.get(0).getName() + " - " + ex.get(0).getDescription(), ex.get(1).getName() + " - " + ex.get(1).getDescription(), ex.get(2).getName() + " - " + ex.get(2).getDescription() };
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, planetList);

        lv.setAdapter(listAdapter);
    }

    //Första insättning av standard-övningar
    public void addStandardExercises(){
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Exercise> results = realm.where(Exercise.class).between("eid", 1, 3).findAll();
        if(results == null) {
            realm.beginTransaction();

            //Fungerar som auto-increment
            //nextID = (int) (realm.where(Exercise.class).max("eid").intValue() + 1);

            Exercise exercise1 = realm.createObject(Exercise.class, 1);
            exercise1.setName("Bänkpress");
            exercise1.setDescription("Standard bröstövning");

            Exercise exercise2 = realm.createObject(Exercise.class, 2);
            exercise2.setName("Marklyft");
            exercise2.setDescription("Standard Ryggövning");

            Exercise exercise3 = realm.createObject(Exercise.class, 3);
            exercise3.setName("Knäböj");
            exercise3.setDescription("Standard benövning");

            realm.commitTransaction();
        }
    }
}
