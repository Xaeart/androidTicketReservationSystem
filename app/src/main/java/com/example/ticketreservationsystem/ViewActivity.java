/*
    Cole Howell, Manoj Bompada, Justin Le
    ViewActivity.java
    ITCS 4180
 */

package com.example.ticketreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ArrayList<String> namelist = new ArrayList<String>();
    ArrayList<Ticket> tktlst = new ArrayList<Ticket>();
    private EditText edtname, src, dest, edtdepdate, edtdeptime, edtretdate, edtrettime;
    private RadioGroup trptype;
    private RadioButton oneway, twoway;
    private Button selecbtn, cancelbtn, del, finishbtn;
    private String delname;
    private ImageView gotofirst, gotolast, goprev, gonext;
    public static String VIEWTICKETLIST = "ticketlist";
    public static String VIEWTICKETKEY = "view ticketkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        edtname = (EditText) findViewById(R.id.namefield);
        src = (EditText) findViewById(R.id.source);
        dest = (EditText) findViewById(R.id.destination);
        edtdepdate = (EditText) findViewById(R.id.departure);
        edtdeptime = (EditText) findViewById(R.id.deptime);
        edtretdate = (EditText) findViewById(R.id.returndate);
        edtrettime = (EditText) findViewById(R.id.rettime);
        trptype = (RadioGroup) findViewById(R.id.triptypegroup);
        finishbtn = (Button) findViewById(R.id.viewFinishButton);
        gotofirst = (ImageView) findViewById(R.id.goToFirstTicket);
        gotolast = (ImageView) findViewById(R.id.goToLastTicket);
        goprev = (ImageView) findViewById(R.id.goToPreviousTicket);
        gonext = (ImageView) findViewById(R.id.goToNextTicket);

        if (getIntent().getExtras() != null) {
            tktlst = (ArrayList<Ticket>) getIntent().getExtras().getSerializable(MainActivity.TKTLIST_KEY);
            for (Ticket x : tktlst) {
//            for (Ticket x : MainActivity.tlist) {
                String name = x.getName().toString();
                namelist.add(name);
                Log.d("demo", "Name in del: " + name);
            }
        } else {
            Log.d("demo", "list is empty");
            Intent intent = new Intent(ViewActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }

        final CharSequence[] charSequenceItems = namelist.toArray(new CharSequence[namelist.size()]);

        //        for (Ticket x : tktlst) {

//            if(x.getName().equals(delname)){
        edtname.setText(tktlst.get(0).getName());
        edtname.setKeyListener(null);
        src.setText(tktlst.get(0).getSource());
        dest.setText(tktlst.get(0).getDestination());
        String dep[] = tktlst.get(0).getDeparture().split(",");
        edtdepdate.setText(dep[0]);
        edtdepdate.setKeyListener(null);
        edtdeptime.setText(dep[1]);
        edtdeptime.setKeyListener(null);
        String ret[] = tktlst.get(0).getReturn().split(",");
        edtretdate.setText(ret[0]);
        edtretdate.setKeyListener(null);
        edtrettime.setText(ret[1]);
        edtrettime.setKeyListener(null);
        if (tktlst.get(0).isRound()) {
            trptype.check(R.id.round);

            edtretdate.setVisibility(View.VISIBLE);
            edtrettime.setVisibility(View.VISIBLE);
        } else {
            trptype.check((R.id.oneway));
        }

        trptype.setClickable(false);

        gotofirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtname.setText(tktlst.get(0).getName());
                edtname.setKeyListener(null);
                src.setText(tktlst.get(0).getSource());
                dest.setText(tktlst.get(0).getDestination());
                String dep[] = tktlst.get(0).getDeparture().split(",");
                edtdepdate.setText(dep[0]);
                edtdepdate.setKeyListener(null);
                edtdeptime.setText(dep[1]);
                edtdeptime.setKeyListener(null);
                String ret[] = tktlst.get(0).getReturn().split(",");
                edtretdate.setText(ret[0]);
                edtretdate.setKeyListener(null);
                edtrettime.setText(ret[1]);
                edtrettime.setKeyListener(null);
                if (tktlst.get(0).isRound()) {
                    trptype.check(R.id.round);

                    edtretdate.setVisibility(View.VISIBLE);
                    edtrettime.setVisibility(View.VISIBLE);
                } else {
                    trptype.check((R.id.oneway));
                }

                trptype.setClickable(false);

            }
        });

        gotolast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtname.setText(tktlst.get(tktlst.size() - 1).getName());
                edtname.setKeyListener(null);
                src.setText(tktlst.get(tktlst.size() - 1).getSource());
                dest.setText(tktlst.get(tktlst.size() - 1).getDestination());
                String dep[] = tktlst.get(tktlst.size() - 1).getDeparture().split(",");
                edtdepdate.setText(dep[0]);
                edtdepdate.setKeyListener(null);
                edtdeptime.setText(dep[1]);
                edtdeptime.setKeyListener(null);
                String ret[] = tktlst.get(tktlst.size() - 1).getReturn().split(",");
                edtretdate.setText(ret[0]);
                edtretdate.setKeyListener(null);
                edtrettime.setText(ret[1]);
                edtrettime.setKeyListener(null);
                if (tktlst.get(tktlst.size() - 1).isRound()) {
                    trptype.check(R.id.round);

                    edtretdate.setVisibility(View.VISIBLE);
                    edtrettime.setVisibility(View.VISIBLE);
                } else {
                    trptype.check((R.id.oneway));
                }

                trptype.setClickable(false);

            }
        });



        gonext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtname.getText().toString();
                Log.d("demo", "name after click next: "+name);
                int index, i, next = 0;
                for (i = 0; i < MainActivity.tlist.size(); i++) {
                    if (MainActivity.tlist.get(i).getName().equals(name)) {
                        index = i;
                        Log.d("demo", "index i in gonext: "+i);
                        Log.d("demo", "name after click next for loop: "+name+" MainActivity.tlist.get(i).getName()"+MainActivity.tlist.get(i).getName());
                        next = i+1;
                    }
                }

                if (next  > (MainActivity.tlist.size() - 1)) {

                    Log.d("demo", "i+1 :" +(i+1)+" (MainActivity.tlist.size() - 1)"+(MainActivity.tlist.size() - 1));

                    Toast.makeText(ViewActivity.this, "this is the last ticket", Toast.LENGTH_SHORT).show();
                } else {
//                    next = i + 1;

                    edtname.setText(MainActivity.tlist.get(next).getName());
                    edtname.setKeyListener(null);
                    src.setText(MainActivity.tlist.get(next).getSource());
                    dest.setText(MainActivity.tlist.get(next).getDestination());
                    String dep[] = MainActivity.tlist.get(next).getDeparture().split(",");
                    edtdepdate.setText(dep[0]);
                    edtdepdate.setKeyListener(null);
                    edtdeptime.setText(dep[1]);
                    edtdeptime.setKeyListener(null);
                    String ret[] = MainActivity.tlist.get(next).getReturn().split(",");
                    edtretdate.setText(ret[0]);
                    edtretdate.setKeyListener(null);
                    edtrettime.setText(ret[1]);
                    edtrettime.setKeyListener(null);
                    if (MainActivity.tlist.get(next).isRound()) {
                        trptype.check(R.id.round);

                        edtretdate.setVisibility(View.VISIBLE);
                        edtrettime.setVisibility(View.VISIBLE);
                    } else {
                        trptype.check((R.id.oneway));
                    }

                    trptype.setClickable(false);


                }
            }

        });


        goprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtname.getText().toString();
                int index, i, prev = 0;
                for (i = 0; i < MainActivity.tlist.size(); i++) {
                    if (MainActivity.tlist.get(i).getName().equals(name)) {
                        index = i;
                        prev = i - 1;
                    }
                }

                if (prev < 0) {
                    Toast.makeText(ViewActivity.this, "this is the first ticket", Toast.LENGTH_SHORT).show();

                } else {
//                    prev = i - 1;
                    edtname.setText(MainActivity.tlist.get(prev).getName());
                    edtname.setKeyListener(null);
                    src.setText(MainActivity.tlist.get(prev).getSource());
                    dest.setText(MainActivity.tlist.get(prev).getDestination());
                    String dep[] = MainActivity.tlist.get(prev).getDeparture().split(",");
                    edtdepdate.setText(dep[0]);
                    edtdepdate.setKeyListener(null);
                    edtdeptime.setText(dep[1]);
                    edtdeptime.setKeyListener(null);
                    String ret[] = MainActivity.tlist.get(prev).getReturn().split(",");
                    edtretdate.setText(ret[0]);
                    edtretdate.setKeyListener(null);
                    edtrettime.setText(ret[1]);
                    edtrettime.setKeyListener(null);
                    if (MainActivity.tlist.get(prev).isRound()) {
                        trptype.check(R.id.round);

                        edtretdate.setVisibility(View.VISIBLE);
                        edtrettime.setVisibility(View.VISIBLE);
                    } else {
                        trptype.check((R.id.oneway));
                    }

                    trptype.setClickable(false);
                }


            }


        });


        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, MainActivity.class);
                intent.putExtra(VIEWTICKETLIST,MainActivity.tlist);
                intent.putExtra(VIEWTICKETKEY,"tktvalue");
                finish();
                startActivity(intent);
            }
        });

    }
}