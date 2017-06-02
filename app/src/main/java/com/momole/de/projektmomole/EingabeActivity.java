package com.momole.de.projektmomole;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.preference.PreferenceManager;
import android.content.Intent;
import android.widget.Toast;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import com.momole.de.projektmomole.Database.model.Momole;
import com.momole.de.projektmomole.Database.MomoleDAO;
import com.momole.de.projektmomole.Database.DatabaseHelper;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class EingabeActivity extends Activity {

    Button b_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe2);
        findViewById(R.id.b_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String leb = ((TextView) findViewById(R.id.Lebensmittel)).getText().toString();
                String besch = ((TextView) findViewById(R.id.Beschwerden)).getText().toString();
                if (besch.length() > 0){
                try {
                    Momole momole = new Momole();
                    momole.setComp(besch);
                    momole.setFood(leb);
                    momole.setTime(System.currentTimeMillis());
                    MomoleDAO.getInstance(EingabeActivity.this).addMomole(momole);
                    Toast.makeText(EingabeActivity.this, T.set.speichern_message, Toast.LENGTH_SHORT).show();
                    finish()
                    finish();
                }
            }
        }
    }
}






/*
                    Button btnTime, btnDate;
                    TextView tvTime, tvDate;

                    TimePickerDialog timePickerDialog;
                    DatePickerDialog datePickerDialog;

                    Calendar calender = Calendar.getInstance();

                    @Override
                    protected void onCreate (Bundle savedInstanceState){
                        super.onCreate(savedInstanceState);
                        setContentView(R.layout.activity_eingabe2);

                        btnTime = (Button) findViewById(R.id.button);
                        btnTime.setOnClickListener(this);
                        btnDate = (Button) findViewById(R.id.button2);
                        btnDate.setOnClickListener(this);

                        tvTime = (TextView) findViewById(R.id.textView3);
                        tvDate = (TextView) findViewById(R.id.textView5);
                    }

                    @Override
                    public void onClick (View v){

                        calender = Calendar.getInstance();

                        switch (v.getId()) {

                            case R.id.button: {

                                timePickerDialog = new TimePickerDialog(EingabeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                        Calendar timeCalendar = Calendar.getInstance();
                                        timeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        timeCalendar.set(Calendar.MINUTE, minute);

                                        String timestring = DateUtils.formatDateTime(EingabeActivity.this, timeCalendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
                                        tvTime.setText("Uhrzeit: " + timestring);


                                    }
                                }, calender.get(calender.HOUR_OF_DAY), calender.get(calender.MINUTE), android.text.format.DateFormat.is24HourFormat(EingabeActivity.this));


                                timePickerDialog.show();

                                break;
                            }
                            case R.id.button2: {

                                datePickerDialog = new DatePickerDialog(EingabeActivity.this, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                        Calendar dateCalendar = Calendar.getInstance();
                                        dateCalendar.set(Calendar.YEAR, year);
                                        dateCalendar.set(calender.MONTH, month);
                                        dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                        String dateString = DateUtils.formatDateTime(EingabeActivity.this, dateCalendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE);
                                        tvDate.setText("Datum: " + dateString);

                                    }
                                }, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));

                                datePickerDialog.show();
                                break;*/