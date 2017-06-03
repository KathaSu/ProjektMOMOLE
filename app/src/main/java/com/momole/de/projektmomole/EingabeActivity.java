package com.momole.de.projektmomole;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.icu.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;

import com.momole.de.projektmomole.database.MomoleDAO;
import com.momole.de.projektmomole.database.model.Momole;




public class EingabeActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText TextLebensmittel, TextAllergengruppe, TextBeschwerde, TextDate, TextTime;

    private TimePickerDialog TextTimePickerDialog;
    private DatePickerDialog TextDatePickerDialog;

    private SimpleDateFormat dateFormatterDate;
    private SimpleDateFormat dateFormatterTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe2);

        dateFormatterDate = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
        dateFormatterTime = new SimpleDateFormat("HH:mm", Locale.GERMANY);

        findViewsById();
        setDateTimeField();

        findViewById(R.id.btn_speichern).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lebensmittel = ((TextView) findViewById(R.id.lebensmittel_eintragen)).getText().toString();
                String beschwerde = ((TextView) findViewById(R.id.beschwerden_eintragen)).getText().toString();
                String allergengruppe = ((TextView) findViewById(R.id.allergengruppe_eintragen)).getText().toString();
                String date = ((TextView) findViewById(R.id.datum_eintragen)).getText().toString();
                String time = ((TextView) findViewById(R.id.zeit_eintragen)).getText().toString();

                if (date.length() > 0) {
                    try {
                        Momole Momole = new Momole();
                        Momole.setFood(lebensmittel);
                        Momole.setComp(beschwerde);
                        Momole.setAllgr(allergengruppe);
                        Momole.setDate(date);
                        Momole.setTime(time);
                        MomoleDAO.getInstance(EingabeActivity.this).addMomole(Momole);
                        Toast.makeText(EingabeActivity.this, "Eintrag gespeichert", Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (NumberFormatException e) {
                        Toast.makeText(EingabeActivity.this, "Datum fehlt", Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(EingabeActivity.this, "Datum fehlt", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void findViewsById() {
        TextLebensmittel = (EditText) findViewById(R.id.lebensmittel_eintragen);

        TextAllergengruppe = (EditText) findViewById(R.id.allergengruppe_eintragen);

        TextBeschwerde = (EditText) findViewById(R.id.beschwerden_eintragen);

        TextDate = (EditText) findViewById(R.id.datum_eintragen);
        TextDate.setInputType(InputType.TYPE_NULL);

        TextTime = (EditText) findViewById(R.id.zeit_eintragen);
        TextTime.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        TextDate.setOnClickListener(this);
        TextTime.setOnClickListener(this);

        Calendar newCalender = Calendar.getInstance();

        TextDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                TextDate.setText(dateFormatterDate.format(newDate.getTime()));
            }

        },newCalender.get(Calendar.YEAR), newCalender.get(Calendar.MONTH), newCalender.get(Calendar.DAY_OF_MONTH));

        TextTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar newTime = Calendar.getInstance();
                newTime.set(hourOfDay, minute);
                TextTime.setText(dateFormatterTime.format(newTime.getTime()));
            }

        },newCalender.get(Calendar.HOUR_OF_DAY), newCalender.get(Calendar.MINUTE), true);

    }

    @Override
    public void onClick(View view) {
        if (view == TextDate)  {
            TextDatePickerDialog.show();
        }
        else if (view == TextTime) {
            TextTimePickerDialog.show();
        }
    }
}