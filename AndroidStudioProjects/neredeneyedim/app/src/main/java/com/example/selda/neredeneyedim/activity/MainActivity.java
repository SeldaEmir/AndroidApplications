package com.example.selda.neredeneyedim.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.example.selda.neredeneyedim.R;
import com.example.selda.neredeneyedim.adapter.PlacesAdapter;
import com.example.selda.neredeneyedim.model.PlacesModel;

public class MainActivity extends AppCompatActivity {
    ListView listViewPlaces;
    DataBaseHandler db = new DataBaseHandler(this);
    List<PlacesModel> placesModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listViewPlaces = (ListView) findViewById(R.id.listViewPlaces);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDiolog();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
        showPlacesList();


        listViewPlaces.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, final long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Guncellemeler");
                CharSequence charSequence[] = {"Yediklerini Ekle", "güncelle", "sil", "görüntüle"};
                builder.setItems(charSequence, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {

                        } else if (i == 1) {
                            showUpdateDiolog(String.valueOf(placesModelList.get(pos).getPlaces_id()));

                        } else if (i == 2) {
                            db.deletePlaces(String.valueOf(placesModelList.get(pos).getPlaces_id()));
                            showPlacesList();
                        } else if (i == 3) {

                        }
                        else
                            Toast.makeText(MainActivity.this, "Yakında Açılacaktır", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    private void showUpdateDiolog(final String id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Mekan Güncelleme Sayfası");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.add_places_diolog, null);
        final EditText editTextName = (EditText) view.findViewById(R.id.editTextUpdatePlacesName);
        EditText editTextLoc = (EditText) view.findViewById(R.id.editTextUpdatePlacesLoc);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBoxUpdateLike);
        Button buttonImage = (Button) view.findViewById(R.id.buttonUpdateImage);
        Button buttonSave = (Button) view.findViewById(R.id.buttonUpdatePlaces);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

            editTextName.setText(db.getPlace(id).getPlaces_name());
      if(db.getPlace(id).getPlaces_like_dislike().equals("yes"))
          checkBox.setChecked(true);
      else
          checkBox.setChecked(false);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sCheck;
                if (checkBox.isChecked() == true)
                    sCheck = "yes";
                else
                    sCheck = "no";
                Toast.makeText(MainActivity.this, sCheck, Toast.LENGTH_SHORT).show();
                db.updatePlaces(new PlacesModel("", editTextName.getText().toString(), "", "", sCheck),id);
                showPlacesList();
                Toast.makeText(MainActivity.this, "Yeni Mekan Eklendi", Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();

            }
        });


    }
    private void showAddDiolog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Mekan Ekleme Sayfası");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.add_places_diolog, null);
        final EditText editTextName = (EditText) view.findViewById(R.id.editTextPlacesName);
        EditText editTextLoc = (EditText) view.findViewById(R.id.editTextPlacesLoc);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBoxLike);
        Button buttonImage = (Button) view.findViewById(R.id.buttonAddImage);
        Button buttonSave = (Button) view.findViewById(R.id.buttonSavePlaces);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sCheck;
                if (checkBox.isChecked() == true)
                    sCheck = "yes";
                else
                    sCheck = "no";
                Toast.makeText(MainActivity.this, sCheck, Toast.LENGTH_SHORT).show();
                db.addPlaces(new PlacesModel("", editTextName.getText().toString(), "", "", sCheck));
                showPlacesList();
                Toast.makeText(MainActivity.this, "Yeni Mekan Eklendi", Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();

            }
        });


    }

    private void showPlacesList() {
        placesModelList=new ArrayList<>();
        placesModelList=db.getAllPlaces();
        PlacesAdapter placesAdapter = new PlacesAdapter(MainActivity.this,placesModelList );
        listViewPlaces.setAdapter(placesAdapter);
        //Toast.makeText(this, db.getAllPlaces().get(db.getCountPlaces()-1).getPlaces_like_dislike(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

