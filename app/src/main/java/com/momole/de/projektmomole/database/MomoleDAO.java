package com.momole.de.projektmomole.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.momole.de.projektmomole.database.model.Momole;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by manji on 10.05.2017.
 */

public class MomoleDAO {

    // columns of the Momole table
    public static final String TBL = "Eingabe";
    public static final String TBL_ID = "id";
    public static final String TBL_DATE = "date";
    public static final String TBL_FOOD = "foot";
    public static final String TBL_COMPLAINT = "comp";
    public static final String TBL_ALLERGYGROUP = "allgr";

    //sql statement of the Momole table
    public static final String CREATE_TBL = "CREATE TBL " + TBL + "("
            + TBL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TBL_DATE + " INTEGER NOT NULL, "
            + TBL_FOOD + " INTEGER NOT NULL, "
            + TBL_COMPLAINT + " INTEGER NOT NULL, "
            + TBL_ALLERGYGROUP + " TEXT "
            + ");";

    private static MomoleDAO instance;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public static MomoleDAO getInstance(Context context){
        if (instance == null){
            instance = new MomoleDAO(context);
        }
        return instance;
    }

    private MomoleDAO(Context context){
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void onCreate(SQLiteDatabase database){
        database.execSQL(CREATE_TBL);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){

    }

    public Momole getmomole(long id) {
        open();
        Cursor cursor = database.query(TBL, //Table
                null, //null returns all columns / fields
                TBL_ID + "=?", //Selection (WHERE [field]=?)
                new String[]{String.valueOf(id)}, //Selection arguments (selection by id)
                null, //GroupBy (GROUPY BY [field], e. g. in case of sum([field]))
                null, //Having, Selection on Group By fields (HAVING [field]=1)
                null, //Limit, limits the selection, e. g. 10 for 10 entries
                null); //CancelationSignal
        if (cursor.moveToFirst()) { //if data is available
            return readFromCursor(cursor); //read the data
        }
        cursor.close();
        close();
        return null;
    }

    public List<Momole> getAllMomoleAfter(long date1, long date2) {
        open();
        Cursor cursor = database.query(TBL, //Table
                new String[] {TBL_ID, TBL_FOOD, TBL_COMPLAINT, TBL_ALLERGYGROUP}, //Fields, null would also return all columns / fields
                TBL_DATE + "<=" + date1 + "AND" + TBL_DATE + ">=" + date2,//Selection, can't do >= with selection arguments
                null, //Selection arguments (replaces ? in Selection)
                null, //GroupBy (GROUPY BY [field], e. g. in case of sum([field]))
                null, //Having, Selection on Group By fields (HAVING [field]=1)
                null, //Limit, limits the selection, e. g. 10 for 10 entries
                TBL_DATE + " ASC"); //Order by timestamp, ascending
        List<Momole> momole = new LinkedList<>();
        if (cursor.moveToFirst()) { // read in the the resu1lt row by row, if data available
            while (!cursor.isAfterLast()) {
                momole.add(readFromCursor(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        close();
        return momole;
    }

    public List<Momole> getAllMomole() {
        open();

        Cursor cursor = database.query(TBL, //Table
                new String[] {TBL_ID, TBL_DATE, TBL_FOOD, TBL_COMPLAINT, TBL_ALLERGYGROUP}, //Fields, null would also return all columns / fields
                null, //Selection (WHERE [field]=?)
                null, //Selection arguments (replaces ? in Selection)
                null, //GroupBy (GROUPY BY [field], e. g. in case of sum([field]))
                null, //Having, Selection on Group By fields (HAVING [field]=1)
                null, //Limit, limits the selection, e. g. 10 for 10 entries
                null); //CancelationSignal

        List<Momole> momole = new LinkedList<>();
        if (cursor.moveToFirst()) { // read in the the result row by row, if data available
            while (!cursor.isAfterLast()) {
                momole.add(readFromCursor(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        close();
        return momole;
    }

    public long addMomole(Momole momole) {
        open();
        long ret = database.insert(TBL, null, prepareValues(momole));
        if (ret > 0) {
            momole.setId(ret);
        }
        close();
        return ret;
    }

    public int updateMomole(Momole momole) {
        open();
        int ret = database.update(TBL, //Table
                prepareValues(momole), //Values
                TBL_ID + "=?", //Selection (what data to update)
                new String[]{String.valueOf(momole.getId())}); // selection by id
        close();
        return ret;
    }

    public int deleteMomole(Momole momole) {
        open();
        int ret = database.delete(TBL,
                TBL_ID + "=?", //Selection (what data to delete)
                new String[]{String.valueOf(momole.getId())}); // selection by id
        close();
        return ret;
    }

    private ContentValues prepareValues(Momole momole) {
        ContentValues contentValues = new ContentValues();

        if (momole.getId() > 0)
            contentValues.put(TBL_ID, momole.getId());

        contentValues.put(TBL_ALLERGYGROUP, momole.getAllgr());
        contentValues.put(TBL_COMPLAINT, momole.getComp());
        contentValues.put(TBL_FOOD, momole.getFood());
        contentValues.put(TBL_DATE, momole.getDate());

        return contentValues;
    }

    private Momole readFromCursor(Cursor cursor) {
        Momole momole = new Momole();

        int index = cursor.getColumnIndex(TBL_ID);
        momole.setId(cursor.getLong(index));

        index = cursor.getColumnIndex(TBL_DATE);
        momole.setDate(cursor.getLong(index));

        index = cursor.getColumnIndex(TBL_FOOD);
        momole.setFood(cursor.getString(index));

        index = cursor.getColumnIndex(TBL_COMPLAINT);
        momole.setComp(cursor.getString(index));

        index = cursor.getColumnIndex(TBL_ALLERGYGROUP);
        momole.setAllgr(cursor.getString(index));

        return momole;
    }
}