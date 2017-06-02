package com.momole.de.projektmomole.Database;

/**
 * Created by manji on 02.06.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "projektmomole.db";
        private static final int DATABASE_VERSION = 1;
        private static DatabaseHelper instance;

        private DatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory){
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

        public static DatabaseHelper getInstance(Context context){
            if (instance == null){
                instance = new DatabaseHelper(context, null);
            }
            return instance;
        }

        @Override
        public void onCreate(SQLiteDatabase database){
            MomoleDAO.getInstance(null).onCreate(database);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
            MomoleDAO.getInstance(null).onUpgrade(database, oldVersion, newVersion);
        }
    }

