package com.example.jegge.exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class AddExercise extends AppCompatActivity {

    EditText nameOfTheExercies;
    EditText descriptionOfTheExercies;

    String exerciesName;
    String exerciesDescription;

    Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        nameOfTheExercies = (EditText) findViewById(R.id.nameOfTheExerciesEditText);
        descriptionOfTheExercies = (EditText) findViewById(R.id.editTextDescriptionOfTheExercies);

        Button goBackButton = (Button) findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(AddExercise.this, ManageWorkoutsAndExercises.class);
                startActivity(intent);
                finish();

            }

        });

        Button exerciesCreatedButton = (Button) findViewById(R.id.exerciesCreatedButton);
        exerciesCreatedButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                try {
                    exerciesName = nameOfTheExercies.getText().toString();
                    exerciesDescription = descriptionOfTheExercies.getText().toString();

                    nameOfTheExercies.setText("");
                    descriptionOfTheExercies.setText("");

                    Exercise exerciesToBeAdded = new Exercise();
                    exerciesToBeAdded.setName(exerciesName);
                    exerciesToBeAdded.setDescription(exerciesDescription);
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(exerciesToBeAdded);
                    realm.commitTransaction();

                    Toast.makeText(AddExercise.this, "Övning inlagd i databas", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(AddExercise.this, "Något gick fel...", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}

//
//    Button testButton = (Button) findViewById(R.id.test);
//testButton.setOnClickListener(new View.OnClickListener(){
//
//@Override
//public void onClick(View view){
//
//        // Build the query looking at all users:
//        RealmQuery<Exercise> query = realm.where(Exercise.class);
//
//        // Add query conditions:
//        query.equalTo("name", "Test");
//        query.or().equalTo("name", "Test");
//
//        // Execute the query:
//        RealmResults<Exercise> result1 = query.findAll();
//
//        // Or alternatively do the same all at once (the "Fluent interface"):
//        RealmResults<Exercise> result2 = realm.where(Exercise.class)
//        .equalTo("name", "Test")
//        .or()
//        .equalTo("name", "Test")
//        .findAll();
//        }
//
//        });