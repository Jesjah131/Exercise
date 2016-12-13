package com.example.jegge.exercise;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmResults;


public class AddNewWorkout extends AppCompatActivity {
    private TextView resultText;
public int nextID;
    private ListView lv;
    private ArrayAdapter<String> listAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_workout);

        addStandardExercises();

        populateListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                String value = (String)adapter.getItemAtPosition(position);

            }
        });

        Button saveWorkout = (Button) findViewById(R.id.btnSaveWorkout);
        saveWorkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showInputDialog();
            }

        });
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

    //Populera listview med övningar - "exercises"
    public void populateListView(){
        lv = (ListView) findViewById(R.id.listViewExercises);

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Exercise> ex = realm.where(Exercise.class).findAll();
        ex.sort("name");

        String[] planets = new String[] { ex.get(0).getName() + " - " + ex.get(0).getDescription(), ex.get(1).getName() + " - " + ex.get(1).getDescription(), ex.get(2).getName() + " - " + ex.get(2).getDescription() };
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, planetList);

        lv.setAdapter(listAdapter);
        lv.setChoiceMode(lv.CHOICE_MODE_MULTIPLE);
        lv.setItemsCanFocus(false);


    }



    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(AddNewWorkout.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddNewWorkout.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Avbryt",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void saveNewWorkout(){
        lv = (ListView) findViewById(R.id.listViewExercises);

        SparseBooleanArray checked = lv.getCheckedItemPositions();

    }

}
