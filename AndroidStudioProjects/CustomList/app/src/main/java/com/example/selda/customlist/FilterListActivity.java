package com.example.selda.customlist;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class FilterListActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Spinner spinner;
    TextView textView;
    ListView listView;
    List<PersonModel>filterPersonList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_list);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroupGenderFilter);
        spinner=(Spinner)findViewById(R.id.spinnerShowPercentage);
        textView=(TextView)findViewById(R.id.textViewInFo);
        listView=(ListView)findViewById(R.id.listViewFilterList);
        final String []arrStrTeams=getIntent().getExtras().getStringArray("teamList");

        ArrayAdapter arrayAdapter=new ArrayAdapter(FilterListActivity.this,android.R.layout.simple_spinner_item,arrStrTeams);
        spinner.setAdapter(arrayAdapter);

         List<PersonModel>personModels=new ArrayList<>();

        personModels=  (ArrayList<PersonModel>)getIntent().getSerializableExtra("myArrList");
        final List<PersonModel> finalPersonModels = personModels;


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int sayac=0;
                for(int j=0;j<finalPersonModels.size();j++){
                    if(finalPersonModels.get(j).getFunTeam().equals(arrStrTeams[i])){
                        sayac++;
                    }
                }
                textView.setText(arrStrTeams[i]+"m takımının taraftar yüzdesi = "+ String.valueOf(sayac*100/finalPersonModels.size()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                filterPersonList=new ArrayList<PersonModel>();
                if (i==R.id.radioButtonListFemale){

                   for(int count=0;count< finalPersonModels.size();count++){
                       if(finalPersonModels.get(count).getGender().equals("female")){

                           filterPersonList.add(finalPersonModels.get(count));

                       }
                   }
                    showFilterList();
                }
                else
                {

                    for(int count=0;count< finalPersonModels.size();count++){
                        if(finalPersonModels.get(count).getGender().equals("male")){

                            filterPersonList.add(finalPersonModels.get(count));

                        }
                    }
                    showFilterList();
                }
            }
        });


    }

    private void showFilterList() {
        CustomAdapter customAdapter=new CustomAdapter(FilterListActivity.this,filterPersonList);
        listView.setAdapter(customAdapter);

    }
}
