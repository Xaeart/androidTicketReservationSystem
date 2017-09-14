/*
    Cole Howell, Manoj Bompada, Justin Le
    MainActivity.java
    ITCS 4180
 */

package com.example.ticketreservationsystem;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static LinkedList<Ticket> tlist;
    Ticket tkt;
    public static String TKTLIST_KEY = "ticket list";
    public static final int REQ_CODE = 100;
    public static ArrayList<Ticket> tktlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ticket Reservation");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        tlist = new LinkedList<Ticket>();
        Button createButton = (Button) findViewById(R.id.createButton);
        Button editButton = (Button) findViewById(R.id.editButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        Button viewButton = (Button) findViewById(R.id.viewButton);
        Button finishButton = (Button) findViewById(R.id.finishButton);
        EditText phoneNumber = (EditText) findViewById(R.id.phoneno);
        phoneNumber.setKeyListener(null);

        if (getIntent().getExtras() != null) {

            if ((getIntent().getExtras().containsKey(DeleteActivity.DELLIST)) && (getIntent().getExtras().containsKey(DeleteActivity.DELNAME))) {
                String denm = getIntent().getExtras().getString(DeleteActivity.DELNAME);
                tktlist = (ArrayList<Ticket>) getIntent().getExtras().getSerializable(DeleteActivity.DELLIST);
                tlist.addAll(tktlist);

                for (Ticket x : MainActivity.tlist) {
                    if(x.getName().equals(denm)) {
                        tlist.remove(x);
                        break;
                    }
                }
            } else if (getIntent().getExtras().containsKey(PrintActivity.TICKETLIST)) {
                tktlist = (ArrayList<Ticket>) getIntent().getExtras().getSerializable(PrintActivity.TICKETLIST);
                for (Ticket x : tktlist) {
                    String name = x.getName();
                    MainActivity.tlist.add(x);
                }
            } else if (getIntent().getExtras().containsKey(ViewActivity.VIEWTICKETLIST)) {
                tktlist = (ArrayList<Ticket>) getIntent().getExtras().getSerializable(ViewActivity.VIEWTICKETLIST);
            }
        }

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tlist.isEmpty()){
                    Toast.makeText(MainActivity.this, "There are no tickets to edit", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra(TKTLIST_KEY, tlist);
                    finish();
                    startActivity(intent);
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tlist.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No tickets in the list", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                    intent.putExtra(TKTLIST_KEY, tlist);
                    startActivity(intent);
                }
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tlist.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No tickets in the list", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                    intent.putExtra(TKTLIST_KEY, tlist);
                    startActivity(intent);
                }
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();

            }
        });

        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:9999999999"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intentcall);
            }
        });
    }
}