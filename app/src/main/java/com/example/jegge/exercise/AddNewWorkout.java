package com.example.jegge.exercise;

import android.app.AlertDialog;
import android.app.Dialog;
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
import io.realm.RealmList;
import io.realm.RealmResults;


public class AddNewWorkout extends AppCompatActivity {
    private TextView resultText;
public int nextIDw;
    public int nextIDe;
    private ListView lv;
    private ArrayAdapter<String> listAdapter ;
    public final ArrayList<String> checkedItems = new ArrayList<String>();
    EditText editTextInputDialog;

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
                Realm realm = Realm.getDefaultInstance();
                RealmResults<Workout> wo = realm.where(Workout.class).findAll();

                String value = (String)adapter.getItemAtPosition(position);
                String string = listAdapter.getItem(position);
                String subbad = value.substring(0,1);
                checkedItems.add(subbad);
                //Toast.makeText(getBaseContext(),wo.get(20).getName().toString(),Toast.LENGTH_LONG).show();
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

        RealmResults<Exercise> results = realm.where(Exercise.class).between("eid", 1, 10).findAll();
        if(results == null) {
            realm.beginTransaction();

            //Fungerar som auto-increment
            //nextID = (int) (realm.where(Exercise.class).max("eid").intValue() + 1);

            Exercise exercise1 = realm.createObject(Exercise.class, 1);
            exercise1.setName("Bänkpress");
            exercise1.setDescription("Standard bröstövning");

            Exercise exercise2 = realm.createObject(Exercise.class, 2);
            exercise2.setName("Marklyft");
            exercise2.setDescription("Standard ryggövning");

            Exercise exercise3 = realm.createObject(Exercise.class, 3);
            exercise3.setName("Knäböj");
            exercise3.setDescription("Standard benövning");

            Exercise exercise4 = realm.createObject(Exercise.class, 4);
            exercise4.setName("Militärpress");
            exercise4.setDescription("Skivstång ovanför huvudet");

            Exercise exercise5 = realm.createObject(Exercise.class, 5);
            exercise5.setName("Bicepcurl");
            exercise5.setDescription("Biceps");

            Exercise exercise6 = realm.createObject(Exercise.class, 6);
            exercise6.setName("Dips");
            exercise6.setDescription("Triceps, axlar, bröst");

            Exercise exercise7 = realm.createObject(Exercise.class, 7);
            exercise7.setName("Chins");
            exercise7.setDescription("Häng i räcke, dra upp hela din vikt");

            Exercise exercise8 = realm.createObject(Exercise.class, 8);
            exercise8.setName("Flyes");
            exercise8.setDescription("Hantlar utåt sidan");

            Exercise exercise9 = realm.createObject(Exercise.class, 9);
            exercise9.setName("Cable-crossover");
            exercise9.setDescription("Ståendes i cable-maskin");

            Exercise exercise10 = realm.createObject(Exercise.class, 10);
            exercise10.setName("Axelpress");
            exercise10.setDescription("Pressa upp hantlarna med armbågarna utåt sidan från axlarna uppåt till raka armar");

            realm.commitTransaction();
        }
    }

    //Populera listview med övningar - "exercises"
    public void populateListView(){
        lv = (ListView) findViewById(R.id.listViewExercises);

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Exercise> ex = realm.where(Exercise.class).findAll();

        ex.sort("name");

        String[] planets = new String[] { ex.get(0).getEid() + " - " + ex.get(0).getName() + " - " + ex.get(0).getDescription(), ex.get(1).getEid() + " - " + ex.get(1).getName() + " - " + ex.get(1).getDescription(), ex.get(2).getEid() + " - " +  ex.get(2).getName() + " - " + ex.get(2).getDescription(),
                ex.get(4).getEid() + " - " + ex.get(4).getName() + " - " + ex.get(4).getDescription(), ex.get(5).getEid() + " - " + ex.get(5).getName() + " - " + ex.get(5).getDescription(), ex.get(6).getEid() + " - " + ex.get(6).getName() + " - " + ex.get(6).getDescription(),
                ex.get(7).getEid() + " - " + ex.get(7).getName() + " - " + ex.get(7).getDescription(), ex.get(8).getEid() + " - " + ex.get(8).getName() + " - " + ex.get(8).getDescription(), ex.get(9).getEid() + " - " + ex.get(9).getName() + " - " + ex.get(9).getDescription() };
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
        final EditText editText = (EditText) promptView.findViewById(R.id.editTextInputDialog);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Realm realm = Realm.getDefaultInstance();

                        realm.executeTransaction(new Realm.Transaction() {

                            @Override
                            public void execute(Realm realm) {
                                nextIDw = (int) (realm.where(Workout.class).max("wid").intValue() + 1);
                                Workout w = realm.createObject(Workout.class, nextIDw);

                                String name = editText.getText().toString();
                                w.setName(name);
                                RealmList<Exercise> exercisesList = new RealmList<Exercise>();

                                for (final String v : checkedItems) {
                                    int foo = Integer.parseInt(v);
                                    RealmResults<Exercise> ex = realm.where(Exercise.class).equalTo("eid", foo).findAll();
                                    exercisesList.addAll(ex.subList(0, ex.size()));
                                }

                                w.setExercises(exercisesList);
                            }
                        });


                            Intent intent = new Intent(AddNewWorkout.this, ManageWorkout.class);
                            startActivity(intent);

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



}
