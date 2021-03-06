package com.example.selda.customlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName,editTextAge,editTextHomeTown;
    RadioGroup radioGroupGender;
    Spinner spinnerTeams;
    CheckBox checkBoxGraduated;
    ListView listView;
    String stringSelectedTeam,gender,graduated;


    String snackMessage="";
    String strArrTeams[]={"FENERBAHÇE","cincon","karşı yaka","göztepe"};
    List<PersonModel> personModelList;
    TextView textViewAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextAge=(EditText)findViewById(R.id.editTextAge);
        editTextHomeTown=(EditText)findViewById(R.id.editTextHomeTown);
        radioGroupGender=(RadioGroup)findViewById(R.id.radioGroupGender);
        spinnerTeams=(Spinner)findViewById(R.id.spinnerTeams);
        checkBoxGraduated=(CheckBox)findViewById(R.id.checkBoxGraduated);
        listView=(ListView)findViewById(R.id.listViewPersons);
        personModelList=new ArrayList<>();

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater layoutInflater= LayoutInflater.from(this);
        View view=layoutInflater.inflate(R.layout.action_layout,null);
        textViewAction=(TextView)view.findViewById(R.id.textViewActionBar);
        Button button=(Button)view.findViewById(R.id.buttonActionNextPage);
        actionBar.setCustomView(view);
        actionBar.setDisplayShowCustomEnabled(true);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ıntent=new Intent(MainActivity.this,FilterListActivity.class);
                ıntent.putExtra("teamList",strArrTeams);
                ıntent.putExtra("myArrList", (Serializable) personModelList);
                startActivity(ıntent);


            }
        });
        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,strArrTeams);
        spinnerTeams.setAdapter(arrayAdapter);

        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                stringSelectedTeam=spinnerTeams.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPerson();
                Snackbar.make(view, snackMessage, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {


                final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                final AlertDialog alertDialog=builder.create();
                builder.setTitle(personModelList.get(i).name)
                        .setMessage(" kişisini silmek istiyormusunuz")
                        .setPositiveButton("evet", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                personModelList.remove(i);
                                Toast.makeText(MainActivity.this, "silindi", Toast.LENGTH_SHORT).show();
                                showList();
                            }
                        })
                        .setNegativeButton("hayır", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return true;
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
            if(checkBoxGraduated.isChecked()==true)
                graduated="yes";
            else
                graduated="no";
            personModelList.add(new PersonModel(gender,editTextName.getText().toString(),editTextAge.getText().toString(),
                stringSelectedTeam,editTextHomeTown.getText().toString(),graduated));
            textViewAction.setText(String.valueOf(personModelList.size()));
            showList();
            snackMessage="kişi eklendi";

        }
        else
            snackMessage="cinsiyeti ve ismi belirt";
    }

    private void showList() {
        CustomAdapter customAdapter=new CustomAdapter(MainActivity.this,personModelList);
        listView.setAdapter(customAdapter);
    }


}
