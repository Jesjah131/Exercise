package com.example.jegge.exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageWorkoutsAndExercises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_workouts_and_exercises);


        Button goBackToMainActivity = (Button) findViewById(R.id.goBackButton);
        goBackToMainActivity.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view){
            Intent intent = new Intent(ManageWorkoutsAndExercises.this, MainActivity.class);
            startActivity(intent);
        }

        });

        Button addWorkout = (Button) findViewById(R.id.addWorkout);
        addWorkout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(ManageWorkoutsAndExercises.this, AddNewWorkout.class);
                startActivity(intent);
            }

        });
    }





}
