/*
    Cole Howell, Manoj Bompada, Justin Le
    PrintActivity.java
    ITCS 4180
 */

package com.example.ticketreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PrintActivity extends AppCompatActivity {

    private TextView name,source,dest,departure,returns;
    private Button fin;
    ArrayList<Ticket> tktlist;
    public static String TKTOJT = "ticket obj";
    public static String TICKETLIST = "ticketlist";
    public static String TICKETKEY = "ticketkey";
    Ticket tkt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        name = (TextView) findViewById(R.id.name);
        source = (TextView) findViewById(R.id.src);
        dest = (TextView) findViewById(R.id.dest);
        departure = (TextView) findViewById(R.id.dep);
        returns = (TextView) findViewById(R.id.ret);
        fin = (Button) findViewById(R.id.finish);

        if(getIntent().getExtras() !=null){
            tkt = (Ticket) getIntent().getExtras().getSerializable(CreateActivity.TICKET);
            name.setText("Name: "+ tkt.getName() );
            source.setText("Source: "+ tkt.getSource());
            dest.setText("Destination: "+tkt.getDestination());
            departure.setText("Departure: "+tkt.getDeparture());
            returns.setText("Return: " + tkt.getReturn());
        }

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrintActivity.this, MainActivity.class);
                intent.putExtra(TICKETLIST, MainActivity.tlist);
                intent.putExtra(TICKETKEY,TKTOJT);
                finish();
                startActivity(intent);
            }
        });
    }
}
