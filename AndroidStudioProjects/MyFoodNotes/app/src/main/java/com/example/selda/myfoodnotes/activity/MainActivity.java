package com.example.selda.myfoodnotes.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.selda.myfoodnotes.R;
import com.example.selda.myfoodnotes.adapter.PlacesAdapter;
import com.example.selda.myfoodnotes.model.CameraOpen;
import com.example.selda.myfoodnotes.model.PlacesModel;
import com.example.selda.myfoodnotes.model.ProductModel;

public class MainActivity extends AppCompatActivity {
    ListView listViewPlaces;
    DataBaseHandler db = new DataBaseHandler(this);
    List<PlacesModel> placesModelList;
    CameraOpen cameraOpen=new CameraOpen(this);
    private static final int MEDIA_TYPE_IMAGE = 10;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;//1888 dene
    private static final String IMAGE_DIRECTORY_NAME="NeredeNeYedim";
    private Uri fileUri;
    static String image="";
    static  File mediaFile;
    PlacesAdapter placesAdapter;
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


        listViewPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        listViewPlaces.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, final long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Updates");
                CharSequence charSequence[] = {"Add your food", "Update", "Delete", "View"};
                builder.setItems(charSequence, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            addProduct(placesModelList.get(pos).getPlaces_id());

                        } else if (i == 1) {
                            showUpdateDiolog(String.valueOf(placesModelList.get(pos).getPlaces_id()));

                        } else if (i == 2) {
                            db.deletePlaces(String.valueOf(placesModelList.get(pos).getPlaces_id()));
                            showPlacesList();
                        } else if (i == 3) {
                            Intent ıntent=new Intent(MainActivity.this,ProductActivity.class);
                            ıntent.putExtra("id",String.valueOf(placesModelList.get(pos).getPlaces_id()));
                            startActivity(ıntent);
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
    public String getDate(){
        String date = null;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        date=dateFormat.format(new java.util.Date());

        return date;
    }

    private void addProduct(final int placeId) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("What you eat?");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.add_product_diolog, null);
        builder.setView(view);
        final EditText editTextProName=(EditText)view.findViewById(R.id.editTextAddProdcutName);
        final EditText editTextProPrice=(EditText)view.findViewById(R.id.editTextAddProductPrice);
        final CheckBox checkBox=(CheckBox)view.findViewById(R.id.checkBoxAddProductLike);
        Button buttonSave=(Button)view.findViewById(R.id.buttonSaveProduct);
        Button buttonAddImage=(Button)view.findViewById(R.id.buttonAddProductImage);

       final AlertDialog alertDialog  = builder.create();
       alertDialog.show();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sCheck;
                if (checkBox.isChecked() == true)
                    sCheck = "yes";
                else
                    sCheck = "no";

                db.addProducts(new ProductModel(placeId,getDate(),
                        "",editTextProName.getText().toString(),
                        editTextProPrice.getText().toString(),sCheck));

                alertDialog.dismiss();
            }
        });



    }
    String dateFromMilli(long milliSecond){
        
        
        return "";
    }
    long milliDateFromStrDate(String date){

        long milliSecondDate = 0;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return milliSecondDate;   
    }

    private void showUpdateDiolog(final String id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Update Place Page");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.update_place_diolog, null);
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

                db.updatePlaces(new PlacesModel("", editTextName.getText().toString(), "", "", sCheck),id);
                showPlacesList();
                Toast.makeText(MainActivity.this, "Place updated", Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();

            }
        });


    }
    private void showAddDiolog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Place Page");
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


        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(cameraIntent, CAMERA_REQUEST);*/

                if (!isDeviceSupportCamera()) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! Your device doesn't support camera",
                            Toast.LENGTH_LONG).show();
                    // will close the app if the device does't have camera
                    finish();
                }


                image = "image" + ".jpg";

                captureImage();


            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sCheck;
                if (checkBox.isChecked() == true)
                    sCheck = "yes";
                else
                    sCheck = "no";

                db.addPlaces(new PlacesModel("", editTextName.getText().toString(), "", "", sCheck));
                showPlacesList();
                Toast.makeText(MainActivity.this, "New Place Added", Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();

            }
        });


    }

    private void showPlacesList() {
        placesModelList=new ArrayList<>();
        placesModelList=db.getAllPlaces();
        placesAdapter = new PlacesAdapter(MainActivity.this,placesModelList );
        listViewPlaces.setAdapter(placesAdapter);
        //Toast.makeText(this, db.getAllPlaces().get(db.getCountPlaces()-1).getPlaces_like_dislike(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItemSearch=menu.findItem(R.id.menuSearchButton);
        SearchView searchView=(SearchView)menuItemSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                placesAdapter.filter(s);
                return false;
            }
        });
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


    private static File getOutputMediaFile(int type) {


        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name


        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + image);


        }

        else {
            return null;
        }

        return mediaFile;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image


        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                Toast.makeText(getApplicationContext(), "Picture Added", Toast.LENGTH_SHORT).show();



            }

            else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private void fotoBoyutKucult() {
        // TODO Auto-generated method stub
        FileOutputStream outfile;
        Bitmap bitmap = null;
        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 8;

        try {
            bitmap= BitmapFactory.decodeFile(mediaFile.getAbsolutePath());
            outfile=new FileOutputStream(mediaFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 65, outfile);
            outfile.flush();
            outfile.close();
            //bitmap.recycle();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    private void captureImage() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);


        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);


    }




}


