package com.example.jegge.exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;

public class SessionActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<String> workoutArrayList;
    ArrayAdapter<CharSequence> adapter;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        Button goToSessionStarted = (Button) findViewById(R.id.btnStartSession);
        goToSessionStarted.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(SessionActivity.this, SessionStartedActivity.class);
                startActivity(intent);
            }

        });

        Realm realm = Realm.getDefaultInstance();
        spinner = (Spinner)findViewById(R.id.spinner);

        RealmHelper helper = new RealmHelper(realm);
        workoutArrayList=helper.retrieveWorkouts();

        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,workoutArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

                  Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selected",Toast.LENGTH_LONG).show();

              }

              @Override
              public void onNothingSelected(AdapterView<?> parent){


              }
        });

    }
}
