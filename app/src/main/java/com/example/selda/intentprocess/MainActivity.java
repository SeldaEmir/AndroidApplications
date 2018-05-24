package com.example.selda.intentprocess;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    String strArr[] = {"Call", "Location", "WebPage", "Email", "Calendar", "SMS"};
    int callRequest = 1;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strArr);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                String str = listView.getItemAtPosition(pos).toString();
                int position = Arrays.asList(strArr).indexOf(listView.getItemAtPosition(pos).toString());
                if (position == 0) {
                    startCallIntent();
                } else if (position == 1) {

                    showMapIntent();
                } else if (position == 2) {
                    webPageIntent();
                } else if (position == 3) {
                    eMailIntent();
                } else if (position == 4) {
                    calendarIntent();
                } else if (position == 5) {
                    smsIntent();
                }

            }
        });


    }

    private void startCallIntent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Start Calling");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.open_call_dialog, null);
        editText = (EditText) view.findViewById(R.id.editTextDialogPhone);
        Button button = (Button) view.findViewById(R.id.buttonDialogCall);
        Button buttonDirectCall = (Button) view.findViewById(R.id.buttonDialogDirectCall);
        builder.setView(view);
        builder.create().show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    Uri number = Uri.parse("tel:" + editText.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(intent);
                }
            }
        });
        buttonDirectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    directCall();


                }
            }
        });


    }

    private void directCall() {
        Uri number = Uri.parse("tel:" + editText.getText().toString());
        Intent intent = new Intent(Intent.ACTION_CALL, number);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, callRequest);

            return;
        } else {
            startActivity(intent);
        }
    }


    private void showMapIntent() {
        Uri uriLocationLatLong2 = Uri.parse("geo:41.016032, 28.955939?q=fatih");
        Intent intent = new Intent(Intent.ACTION_VIEW, uriLocationLatLong2);
        startActivity(intent);
    }

    private void webPageIntent() {
        Uri uriWeb = Uri.parse("https://developers.google.com/maps/documentation/urls/android-intents");
        Intent intent = new Intent(Intent.ACTION_VIEW, uriWeb);
        startActivity(intent);
    }

    private void eMailIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"seldaemirr@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Content");
        startActivity(intent);

    }

    private void calendarIntent() {
        Intent intent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        Calendar calendarBeg = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calendarBeg);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calendarEnd);
        intent.putExtra(CalendarContract.Events.TITLE, "Birthday of Sister");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Istanbul");
        startActivity(intent);
    }


    private void smsIntent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Send SMS");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.open_call_dialog, null);
        editText = (EditText) view.findViewById(R.id.editTextDialogPhone);
        final EditText editTextSms = (EditText) view.findViewById(R.id.editTextDialogSmsContent);
        Button button = (Button) view.findViewById(R.id.buttonDialogCall);
        Button buttonDirectCall = (Button) view.findViewById(R.id.buttonDialogDirectCall);
        buttonDirectCall.setVisibility(View.INVISIBLE);
        editTextSms.setVisibility(View.VISIBLE);
        button.setText("Send Sms");
        builder.setView(view);
        builder.create().show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smsNumber = editText.getText().toString();
                String smsText = editTextSms.getText().toString();

                Uri uri = Uri.parse("smsto:" + smsNumber);
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", smsText);
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearchItem);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //finishAffinity();
                directCall();
                Toast.makeText(this, String.valueOf(grantResults[0]), Toast.LENGTH_SHORT).show();
            }
        }


    }
}

