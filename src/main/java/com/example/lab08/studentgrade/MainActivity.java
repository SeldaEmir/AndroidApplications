package com.example.lab08.studentgrade;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName,editTextStudentNumber,editTextGrade;
    RadioGroup radioGroupGender;


    ListView listView;
    String stringSelectedTeam,gender,graduated;
    Spinner spinnerLessons;

    String snackMessage="";
    String strArrTeams[]={"Math101","Phys101","Engr101","Bio101"};
    List<PersonModel> personModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextStudentNumber=(EditText)findViewById(R.id.editTextStudentNumber);
        editTextGrade=(EditText)findViewById(R.id.editTextGrade);
        radioGroupGender=(RadioGroup)findViewById(R.id.radioGroupGender);
        spinnerLessons=(Spinner)findViewById(R.id.spinnerLessons);

        listView=(ListView)findViewById(R.id.listViewPersons);
        personModelList=new ArrayList<>();
        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,strArrTeams);
        spinnerLessons.setAdapter(arrayAdapter);
        spinnerLessons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                stringSelectedTeam=spinnerLessons.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPerson();Snackbar.make(view, snackMessage, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void addPerson() {
        if(radioGroupGender.getCheckedRadioButtonId()!=-1&&!editTextName.getText().toString().equals("")) {
            if (radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonFemale) {
                gender = "female";
            } else if (radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale) {
                gender = "male";
            }

            personModelList.add(new PersonModel(gender,editTextName.getText().toString(),editTextStudentNumber.getText().toString(),editTextGrade.getText().toString(),stringSelectedTeam));
            showList();
            snackMessage="Person added";
        }
        else
            snackMessage="Please provide name and gender.";
    }

    private void showList() {
        CustomAdapter customAdapter=new CustomAdapter(MainActivity.this,personModelList);
        listView.setAdapter(customAdapter);
    }


}