package com.momole.de.projektmomole;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

import com.momole.de.projektmomole.database.MomoleDAO;
import com.momole.de.projektmomole.database.model.Momole;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AusgabeActivity extends AppCompatActivity implements OnClickListener {

    //UI References
    private EditText fromDateEtxt;
    private EditText toDateEtxt;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    private Button ButtonSuche;

    private ListView listview;
    private MomoleAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausgabe2);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();
        setDateTimeField();

        ButtonSuche.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                listAdapter = new MomoleAdapter();
                listview.setAdapter(listAdapter);

            }

        });

    }

    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.etxt_fromdate);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.etxt_todate);
        toDateEtxt.setInputType(InputType.TYPE_NULL);

        ButtonSuche = (Button) findViewById(R.id.suche);

        listview = (ListView) findViewById(R.id.listeAusgabe);

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
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }
    }



    class MomoleAdapter extends BaseAdapter{

        public List<Momole> momole;

        public MomoleAdapter() {
            momole = MomoleDAO.getInstance(getContext().getAllMomoleAfter());
        }


        @Override
        public int getCount() {
            return momole.size();
        }

        @Override
        public Momole getItem(int position) {
            return momole.get(position);
        }

        @Override
        public long getItemId(int position) {
            return momole.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null)
                convertView = layoutInflater.inflate(R.layout.row_ausgabe, null);

            TextView date = (TextView) convertView.findViewById(R.id.rowMOMOLEdate);
            TextView time = (TextView) convertView.findViewById(R.id.rowMOMOLEtime);
            TextView lebensmittel = (TextView) convertView.findViewById(R.id.rowMOMOLElebensmittel);
            TextView beschwerden = (TextView) convertView.findViewById(R.id.rowMOMOLEbeschwerde);
            TextView allergengruppe = (TextView) convertView.findViewById(R.id.rowMOMOLEallergen);

            Momole momole = getItem(position);

            date.setText(String.valueOf(momole.getDate()));
            time.setText(String.valueOf(momole.getTime()));
            lebensmittel.setText(String.valueOf(momole.getFood()));
            beschwerden.setText(String.valueOf(momole.getComp()));
            allergengruppe.setText(String.valueOf(momole.getAllgr()));

            return convertView;
        }

        @Override
        public void notifyDataSetChanged() {
            momole = MomoleDAO.getInstance(getContext()).getAllPaymentsAfter();
            super.notifyDataSetChanged();
        }
    }



}
