package com.momole.de.projektmomole;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

import com.momole.de.projektmomole.database.MomoleDAO;
import com.momole.de.projektmomole.database.model.Momole;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class AusgabeActivity extends AppCompatActivity implements OnClickListener {

    private ListView listview;
    private MomoleAdapter listAdapter;
    }

    //UI References
    private EditText fromDateEtxt;
    private EditText toDateEtxt;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausgabe2);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();
        setDateTimeField();
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        if (view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if (view == toDateEtxt) {
            toDatePickerDialog.show();
        }

        private void findViewsById() {



        private class MomoleAdapter extends BaseAdapter {
            private List<Momole> momole;

            private MomoleAdapter() {
                momole = MomoleDAO.getInstance(AusgabeActivity.this).getAllMomole();
            }

            @Override
            public int getCount() { //gibt die Anzahl der verfügbaren Elemente zurück
                return momole.size();
            }

            @Override
            public Momole getItem(int position) { //gibt das Element an Stelle position zurück
                return momole.get(position);
            }

            @Override
            public long getItemId(int position) { //gibt die ID des Elements an Stelle position zurück
                return getItem(position).getId();
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = layoutInflater.inflate(R.layout.momole_list_view, null);
                }

                TextView allgr = (TextView) convertView.findViewById(R.id.etxt_fromdate);
                TextView comp = (TextView) convertView.findViewById(R.id.etxt_todate);

                Momole item = getItem(position);

                allgr.setText(item.getAllgr());
                comp.setText(item.getComp());

                return convertView;
            }
        }

    listview = (ListView) findViewById(R.id.listeAusgabe);
    listview.setAdapter(new MomoleAdapter());
    }

    }
}