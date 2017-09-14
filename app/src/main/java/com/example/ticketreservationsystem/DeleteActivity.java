/*
    Cole Howell, Manoj Bompada, Justin Le
    DeleteActivty.java
    ITCS 4180
 */

package com.example.ticketreservationsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;


public class DeleteActivity extends AppCompatActivity {
    ArrayList<String> namelist = new ArrayList<String>();
    ArrayList<Ticket> tktlst = new ArrayList<Ticket>();
    private EditText edtname,src,dest,edtdepdate,edtdeptime,edtretdate,edtrettime;
    private RadioGroup trptype;
    private RadioButton oneway, twoway;
    private Button selecbtn,cancelbtn,del;
    private String delname;
    public static String DELNAME="delname";
    public static String DELLIST = "ticketlist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        edtname = (EditText) findViewById(R.id.namefield);
        src = (EditText) findViewById(R.id.source);
        dest = (EditText) findViewById(R.id.destination);
        edtdepdate = (EditText) findViewById(R.id.departure);
        edtdeptime = (EditText) findViewById(R.id.deptime);
        edtretdate = (EditText) findViewById(R.id.returndate);
        edtrettime = (EditText) findViewById(R.id.rettime);
        trptype = (RadioGroup) findViewById(R.id.triptypegroup);
        selecbtn = (Button) findViewById(R.id.selecttkt);
        cancelbtn = (Button) findViewById(R.id.cancel);
        del = (Button) findViewById(R.id.delete);

        if (getIntent().getExtras() != null) {
            tktlst = (ArrayList<Ticket>) getIntent().getExtras().getSerializable(MainActivity.TKTLIST_KEY);
            for (Ticket x : tktlst) {
                String name = x.getName().toString();
                namelist.add(name);
            }
        }
        else{
            Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }
        final CharSequence[] charSequenceItems = namelist.toArray(new CharSequence[namelist.size()]);

        AlertDialog.Builder namebuilder = new AlertDialog.Builder(this);
        namebuilder.setTitle("Select a Ticket")
                .setItems(charSequenceItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delname = charSequenceItems[which].toString();
                        for(Ticket x: MainActivity.tlist){
                            if(x.getName().equals(delname)){
                                edtname.setText(x.getName());
                                edtname.setKeyListener(null);
                                src.setText(x.getSource());
                                dest.setText(x.getDestination());
                                String dep[] = x.getDeparture().split(",");
                                edtdepdate.setText(dep[0]);
                                edtdepdate.setKeyListener(null);
                                edtdeptime.setText(dep[1]);
                                edtdeptime.setKeyListener(null);
                                String ret[] = x.getReturn().split(",");
                                edtretdate.setText(ret[0]);
                                edtretdate.setKeyListener(null);
                                edtrettime.setText(ret[1]);
                                edtrettime.setKeyListener(null);
                                if(x.isRound()){
                                    trptype.check(R.id.round);

                                    edtretdate.setVisibility(View.VISIBLE);
                                    edtrettime.setVisibility(View.VISIBLE);
                                }
                                else{
                                    trptype.check((R.id.oneway));
                                }

                                trptype.setClickable(false);
                                selecbtn.setKeyListener(null);
                                cancelbtn.setKeyListener(null);

                            }

                        }
                    }
                });

        final AlertDialog namealert = namebuilder.create();
        selecbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namealert.show();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteActivity.this,MainActivity.class);
                intent.putExtra(DELNAME, delname);
                intent.putExtra(DELLIST, MainActivity.tlist);
                finish();
                startActivity(intent);

            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                intent.putExtra(DELLIST, MainActivity.tlist);
                startActivity(intent);
            }
        });
    }
}