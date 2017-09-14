/*
    Cole Howell, Manoj Bompada, Justin Le
    CreateActivity.java
    ITCS 4180
 */

package com.example.ticketreservationsystem;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity {
    private Button print;
    private EditText edtname, edtsource, edtdest, edtdepdate, edtdeptime, edtretdate, edtrettime;
    private RadioGroup rgtrip;
    private RadioButton rb1, rb2;
    public static CharSequence[] cities = {"Albany, NY","Atlanta, GA","Boston, MA","Charlotte, NC","Chicago, IL","Greenville, SC",
            "Houston, TX","Las Vegas, NV","Los Angeles, CA","Miami, FL","Myrtle Beach, SC","New York, NY",
            "Portland, OR","Raleigh, NC","San Jose, CA","Washington, DC"};
    final Calendar c = Calendar.getInstance();
    final static String TICKET = "Ticket";
    Date datedep, datereturn, timedep, timereturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        edtname = (EditText) findViewById(R.id.namefield);
        edtsource = (EditText) findViewById(R.id.source);
        edtdest = (EditText) findViewById(R.id.destination);
        edtdepdate = (EditText) findViewById(R.id.departure);
        edtdeptime = (EditText) findViewById(R.id.deptime);
        edtretdate = (EditText) findViewById(R.id.returndate);
        edtrettime = (EditText) findViewById(R.id.rettime);
        rgtrip = (RadioGroup) findViewById(R.id.triptypegroup);
        print = (Button) findViewById(R.id.summary);
        rb2 = (RadioButton) findViewById(R.id.round);
        edtsource.setKeyListener(null);
        edtdest.setKeyListener(null);
        edtdepdate.setKeyListener(null);
        edtdeptime.setKeyListener(null);
        edtretdate.setKeyListener(null);
        edtrettime.setKeyListener(null);

        rgtrip.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.round == checkedId) {
                    edtretdate.setVisibility(View.VISIBLE);
                    edtrettime.setVisibility(View.VISIBLE);
                    edtdepdate.setText("");
                    edtdeptime.setText("");
                    edtretdate.setText("");
                    edtrettime.setText("");
                } else {
                    edtretdate.setVisibility(View.INVISIBLE);
                    edtrettime.setVisibility(View.INVISIBLE);
                    edtdepdate.setText("");
                    edtdeptime.setText("");
                    edtretdate.setText("");
                    edtrettime.setText("");
                }
            }
        });

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Source")
                .setItems(cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtsource.setText(cities[which]);
                    }
                })
                .setCancelable(false);

        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Destination")
                .setItems(cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(edtsource.getText().toString().equals(cities[which].toString())){
                            Toast.makeText(CreateActivity.this,"Please select correct destination",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            edtdest.setText(cities[which]);
                        }

                    }
                })
                .setCancelable(false);

        final AlertDialog alert1 = builder1.create();
        final AlertDialog alert2 = builder2.create();
        edtsource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert1.show();
            }
        });
        edtdest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert2.show();
            }
        });

        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtname.getText().toString();
                String source = edtsource.getText().toString();
                String dest = edtdest.getText().toString();
                String deptdate = edtdepdate.getText().toString();
                String depttime = edtdeptime.getText().toString();
                String dept = deptdate+", "+depttime;
                String retdate = edtretdate.getText().toString();
                String rettime = edtrettime.getText().toString();
                String ret = retdate+", "+rettime;
                boolean roundtrip = false;
                if(rgtrip.getCheckedRadioButtonId() == R.id.round){
                    roundtrip = true;
                }

                if(name.isEmpty()|| source.isEmpty() || dest.isEmpty()|| deptdate.isEmpty() || depttime.isEmpty())
                {
                    Toast.makeText(CreateActivity.this,"Please complete the fields",Toast.LENGTH_SHORT).show();
                }
                else{

                Ticket ticket = new Ticket(name,source,dest,dept,ret,roundtrip);
                MainActivity.tlist.add(ticket);
                Intent intent = new Intent(CreateActivity.this, PrintActivity.class);
                intent.putExtra(TICKET,ticket);
                finish();
                startActivity(intent);
            }
            }
        });

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet( DatePicker view, int year, int monthOfYear, int dayOfMonth ) {
            c.set( Calendar.YEAR, year );
            c.set( Calendar.MONTH, monthOfYear );
            c.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            datedep = c.getTime();
            setCurrentDateOnView();
        }
    };

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            timedep = c.getTime();
            setCurrentDateOnView();
        }
    };

    public void datetimeOnClick(View view){
        if(view.getId() == R.id.departure){
            new DatePickerDialog( CreateActivity.this, date,
                    c.get( Calendar.YEAR ), c.get( Calendar.MONTH ), c.get( Calendar.DAY_OF_MONTH ) ).show();}
        else if (view.getId() == R.id.deptime){
            new TimePickerDialog(CreateActivity.this, time,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),false).show();
        }
    }

    public void setCurrentDateOnView(){
        String dateFormat = "MM-dd-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat( dateFormat, Locale.US );

        edtdepdate.setText(sdf.format(c.getTime()));

        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat( timeFormat, Locale.US );
        edtdeptime.setText( stf.format( c.getTime() ) );
    }

    //    ----------------------
    DatePickerDialog.OnDateSetListener dateret = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet( DatePicker view, int year, int monthOfYear, int dayOfMonth ) {
            c.set( Calendar.YEAR, year );
            c.set( Calendar.MONTH, monthOfYear );
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            datereturn = c.getTime();

            if(datedep.before(datereturn)){
                setCurrentDateOnViewret();
            }else{
                Toast.makeText(CreateActivity.this, "Please select a proper return date", Toast.LENGTH_SHORT).show();
            }
        }
    };

    TimePickerDialog.OnTimeSetListener timeret = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            timereturn = c.getTime();
            setCurrentDateOnViewret();

        }
    };

    public void retdatetimeOnClick(View view){
        if(view.getId() == R.id.returndate){
            new DatePickerDialog( CreateActivity.this, dateret,
                    c.get( Calendar.YEAR ), c.get( Calendar.MONTH ), c.get( Calendar.DAY_OF_MONTH ) ).show();}
        else if (view.getId() == R.id.rettime){
            new TimePickerDialog(CreateActivity.this, timeret,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),false).show();
        }
    }

    public void setCurrentDateOnViewret(){
        String dateFormat = "MM-dd-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat( dateFormat, Locale.US );

        edtretdate.setText(sdf.format(c.getTime()));

        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat( timeFormat, Locale.US );
        edtrettime.setText( stf.format( c.getTime() ) );
    }
}
