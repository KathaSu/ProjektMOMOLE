package com.momole.de.projektmomole;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import static android.text.format.DateFormat.is24HourFormat;

public class EingabeActivity extends AppCompatActivity implements View.OnClickListener{



    Button btnTime, btnDate;
    TextView tvTime, tvDate;

    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;

    Calendar calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe2);

        btnTime = (Button) findViewById(R.id.btn_zeiteingabe);
        btnTime.setOnClickListener(this);
        btnDate = (Button) findViewById(R.id.btn_datumeingabe);
        btnDate.setOnClickListener(this);

        tvTime = (TextView) findViewById(R.id.zeitausgabe);
        tvDate = (TextView) findViewById(R.id.datumsausgabe);

        findViewById(R.id.btn_speichern).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lebensmittel = ((TextView) findViewById(R.id.lebensmittel)).getText().toString();
                String beschwerde = ((TextView) findViewById(R.id.beschwerden)).getText().toString();
                String date = ((TextView) findViewById(R.id.datumsausgabe)).getText().toString();
                String time = ((TextView) findViewById(R.id.zeitausgabe)).getText().toString();

                if (date.length() > 0) {
                    try {
                        Momole payment = new Payment();
                        payment.setAmount(paid);
                        payment.setCategory(cat);
                        payment.setTime(System.currentTimeMillis());
                        PaymentDAO.getInstance(PaymentActivity.this).addPayment(payment);
                        Toast.makeText(PaymentActivity.this, R.string.save_payment_message, Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (NumberFormatException e) {
                        Toast.makeText(PaymentActivity.this, R.string.amount_missing, Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(PaymentActivity.this,
                            R.string.amount_missing, Toast.LENGTH_LONG).show();

                }
            }
        }




    }

    @Override
    public void onClick(View v) {

        calender = Calendar.getInstance();

        switch (v.getId()){

            case R.id.btn_zeiteingabe: {

                timePickerDialog = new TimePickerDialog(EingabeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar timeCalendar = Calendar.getInstance();
                        timeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        timeCalendar.set(Calendar.MINUTE, minute);

                        String timestring = hourOfDay + ":" + minute;
                        tvTime.setText(timestring);
                    }
                },calender.get(calender.HOUR_OF_DAY), calender.get(calender.MINUTE), is24HourFormat(this));

                timePickerDialog.show();
                break;
            }

            case R.id.btn_datumeingabe: {

                datePickerDialog = new DatePickerDialog(EingabeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Calendar dateCalendar = Calendar.getInstance();
                        dateCalendar.set(Calendar.YEAR, year);
                        dateCalendar.set(calender.MONTH, month);
                        dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String dateString = dayOfMonth + "-" + month + "-" + year;
                        tvDate.setText(dateString);

                    }
                }, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
                break;
            }
        }
    }

}

