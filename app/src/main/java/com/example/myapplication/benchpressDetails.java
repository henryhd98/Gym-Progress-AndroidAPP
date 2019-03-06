package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class benchpressDetails extends AppCompatActivity {

    /*
    Definition of table columns
     */

    public static final String KEY_WEIGHT = "weight";

    //The name and column index of each column in your database.
    //These should be descriptive.
    public static final String KEY_DATE =
            "todays_date";
    public static final String KEY_benchReps =
            "bench_reps";

    public static final String KEY_BENCHWEIGHT = "bench_weight";
    private Context context;

    // Database open/upgrade helper
    private ModuleDBOpenHelper moduleDBOpenHelper;

    public benchpressDetails(Context context) {

        this.context = context;
        moduleDBOpenHelper = new ModuleDBOpenHelper(context, ModuleDBOpenHelper.DATABASE_NAME, null,
                ModuleDBOpenHelper.DATABASE_VERSION);

        //populating the database

        if (getAll().length == 0) {
            this.addRow("100kg", 112218, 10, 100);

        }
    }

    public void addRow(String userWeight, int date, int reps, float weight) {
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();

        // Assign values for each row.
        newValues.put(KEY_WEIGHT, userWeight);
        newValues.put(KEY_DATE, date);
        newValues.put(KEY_benchReps, reps);
        newValues.put(KEY_BENCHWEIGHT, weight);


        // Insert the row into your table
        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        db.insert(ModuleDBOpenHelper.DATABASE_TABLE, null, newValues);
    }


    public String[] getAll() {

        ArrayList<String> outputArray = new ArrayList<String>();
        String[] result_columns = new String[]{
                KEY_WEIGHT, KEY_DATE, KEY_benchReps, KEY_BENCHWEIGHT};

        String userWeight;
        int date;
        int reps;
        float weight;
        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        //
        boolean result = cursor.moveToFirst();
        while (result) {
            userWeight = cursor.getString(cursor.getColumnIndex(KEY_WEIGHT));
            date = cursor.getInt(cursor.getColumnIndex(KEY_DATE));
            reps = cursor.getInt(cursor.getColumnIndex(KEY_benchReps));
            weight = cursor.getInt(cursor.getColumnIndex(KEY_BENCHWEIGHT));

            outputArray.add(userWeight + " " + context.getString(R.string.cost_str_1) + " " + date + context.getString(R.string.cost_str_2) + reps + " " + weight);
            result = cursor.moveToNext();

        }
        return outputArray.toArray(new String[outputArray.size()]);
    }

    /*
     * This is a helper class that takes a lot of the hassle out of using databases. Use as is and complete the following as required:
     *     - DATABASE_TABLE
     *     - DATABASE_CREATE
     */
    private static class ModuleDBOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase.db";
        private static final String DATABASE_TABLE = "Weights";
        private static final int DATABASE_VERSION = 1;

        // SQL Statement to create a new database.
        private static final String DATABASE_CREATE = "create table " +
                DATABASE_TABLE + " (" + KEY_WEIGHT +
                " integer primary key autoincrement, " +
                KEY_DATE + " text not null, " +
                KEY_benchReps + " int , " + KEY_BENCHWEIGHT + " float);";


        public ModuleDBOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Called when no database exists in disk and the helper class needs
        // to create a new one.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        // Called when there is a database version mismatch meaning that
        // the version of the database on disk needs to be upgraded to
        // the current version.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            // Log the version upgrade.
            Log.w("TaskDBAdapter", "Upgrading from version " +
                    oldVersion + " to " +
                    newVersion + ", which will destroy all old data");

            // Upgrade the existing database to conform to the new
            // version. Multiple previous versions can be handled by
            // comparing oldVersion and newVersion values.

            // The simplest case is to drop the old table and create a new one.
            //    db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
            // Create a new one.
            onCreate(db);
        }


    }
}
