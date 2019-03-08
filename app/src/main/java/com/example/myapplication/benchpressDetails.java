package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class benchpressDetails extends SQLiteOpenHelper {


    private static final String TAG ="BPDB";

    /*
    Definition of table columns
     */
    private static final String TABLE_NAME = "user_progress";
    private static final String KEY_DATE = "date";
    private static final String KEY_WEIGHT = "weight";





    public benchpressDetails(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override

    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_DATE +" DATETIME NOT NULL," + KEY_WEIGHT + "double NOT NULL)";
        db.execSQL(createTable);
    }

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

    public boolean addRow(double userWeight, String date/*, int reps, float weight*/) {

        // Insert the row into your table
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new row of values to insert.
        ContentValues contentValues = new ContentValues();

        // Assign values for each row.
        contentValues.put(KEY_WEIGHT, userWeight);
        Log.d(TAG, "addRow : ADDING " + userWeight + "to" + KEY_WEIGHT);
          contentValues.put(KEY_DATE, date);
      Log.d(TAG, "addRow : ADDING " + date + "to" + KEY_DATE);
     /*  contentValues.put(KEY_benchReps, reps);
        Log.d(TAG, "addRow : ADDING " + reps + "to" + KEY_benchReps);
        contentValues.put(KEY_BENCHWEIGHT, weight);
        Log.d(TAG, "addRow : ADDING " + weight + "to" + KEY_BENCHWEIGHT);

*/
        long result =db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if(result==-1) {
            return false;
        }else{
            return true;
        }
    }


  /*  public String[] getAll() {

        ArrayList<String> outputArray = new ArrayList<String>();
        String[] result_columns = new String[]{
                KEY_WEIGHT, KEY_DATE, KEY_benchReps, KEY_BENCHWEIGHT};

        String userWeight;
        String date;
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
            date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
            reps = cursor.getInt(cursor.getColumnIndex(KEY_benchReps));
            weight = cursor.getInt(cursor.getColumnIndex(KEY_BENCHWEIGHT));

            outputArray.add(userWeight + " " + context.getString(R.string.cost_str_1) + " " + date + context.getString(R.string.cost_str_2) + reps + " " + weight);
            result = cursor.moveToNext();

        }
        return outputArray.toArray(new String[outputArray.size()]);
    }
    */


  public Cursor getData(){
      SQLiteDatabase db =this.getWritableDatabase();
      String query ="SELECT * FROM " +  KEY_WEIGHT + "," + KEY_DATE ;

      Cursor data =db.rawQuery(query,null);
      return data;
  }

    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + KEY_DATE + " FROM " + TABLE_NAME +
                " WHERE " + KEY_WEIGHT + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /*
     * This is a helper class that takes a lot of the hassle out of using databases. Use as is and complete the following as required:
     *     - DATABASE_TABLE
     *     - DATABASE_CREATE
     */





        /**
         * Updates the name field
         * @param newName
         * @param id
         * @param oldName
         */
        public void updateName(String newName, int id, String oldName){
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "UPDATE " + TABLE_NAME + " SET " + KEY_WEIGHT +
                    " = '" + newName + "' WHERE " + KEY_DATE + " = '" + id + "'" +
                    " AND " + KEY_WEIGHT + " = '" + oldName + "'";
            Log.d(TAG, "updateName: query: " + query);
            Log.d(TAG, "updateName: Setting name to " + newName);
            db.execSQL(query);
        }






    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + KEY_DATE + " = '" + id + "'" +
                " AND " + KEY_WEIGHT + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }


}