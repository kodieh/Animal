package saint.animaltracking.helper;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;

import org.xml.sax.DTDHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import saint.animaltracking.animal;
import saint.animaltracking.animalInformation;

/**
 * Created by Kodie on 3/30/2016.
 */

public class DatabaseHelper
{
    //Logcat tag
    private static final String LOG = "DatabaseHelper";
    //DB Version
    private static final int DB_VERSION = 1;
    //DB Name
    private static final String DATABASE_NAME = "AT";
    //Table Names
    private static final String TABLE_ANIMAL = "animal";
    private static final String TABLE_INFO = "information";

    //common column names
    private static final String KEY_ID = "id";

    //Animal column names
    private static final String ANIM_MORPH = "morph";
    private static final String ANIM_SEX = "sex";

    //Information column names
    private static final String INFO_WEIGHT = "weight";
    private static final String INFO_SHED = "shed";
    private static final String INFO_ATE = "ate";

    //Create statements
    //Animal table create statement
    private static final String CREATE_TABLE_ANIMAL = "CREATE TABLE "
    + TABLE_ANIMAL + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ANIM_MORPH
    + " TEXT , " + ANIM_SEX + " TEXT " + ")";

    private static final String CREATE_TABLE_INFO = "CREATE TABLE "
            + TABLE_INFO + "(" + KEY_ID + " STRING, " + INFO_WEIGHT
            + " INTEGER," + INFO_SHED + " DATETIME," + INFO_ATE + " TEXT" + ")";

    private SQLiteOpenHelper AT;
    public DatabaseHelper(Context context)
    {
        AT = new DatabaseOpenHelper(context);
        AT.getReadableDatabase();
        boolean x = checkDataBase(context);

    }
    class DatabaseOpenHelper extends SQLiteOpenHelper {
        DatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
                System.out.println("234");
                db.execSQL(CREATE_TABLE_ANIMAL);


                db.execSQL(CREATE_TABLE_INFO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //i dont understand this enough to make it do something right now.
        }
    }
    /*
     * Creating an animal
     */

        public long createAnimal(animal animal) {

            SQLiteDatabase db = AT.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ANIM_MORPH, animal.getMorph());
            values.put(ANIM_SEX, animal.getSex());
            //values.put(KEY_ID, animal.getId());

            System.out.println("123");
            //insert row
            long animal_id = db.insert(TABLE_ANIMAL, null, values);
            db.close();
            return animal_id;
        }


    /*
     * Create Info for an animal.
     */
    public void createInfo(animalInformation animalInfo) {
        String id, weight, shed, ate;
        SQLiteDatabase db = AT.getWritableDatabase();
        ContentValues values = new ContentValues();
        id = animalInfo.getId();
        weight = animalInfo.getWeight();
        shed = animalInfo.getShedDate();
        ate = animalInfo.getAte();
        values.put(KEY_ID, id);
        values.put(INFO_WEIGHT,weight);
        values.put(INFO_SHED, shed);
        values.put(INFO_ATE, ate);

       db.insert(TABLE_INFO, null, values);
    }

    /*
     * Get a single animal
     */

    public animal getAnimal(String animalID) {
        SQLiteDatabase db = AT.getReadableDatabase();
        animal animal = new animal();
        String selectQuery = "SELECT * FROM " + TABLE_ANIMAL + " WHERE "
                + KEY_ID + " = " + animalID;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
        }

        animal.setId(c.getString(c.getColumnIndex(KEY_ID)));
        animal.setMorph(c.getString(c.getColumnIndex(ANIM_MORPH)));
        animal.setSex(c.getString(c.getColumnIndex(ANIM_SEX)));

        //returns an array in the form of ID, Morph, Sex;
        return animal;
    }
    /*
    gets all animals in the db
     */

    public List<animal> getAllAnimal() {
        SQLiteDatabase db = AT.getReadableDatabase();
        //creates the array to the size of the rows in the table
        List<animal> animals = new ArrayList<animal>();

        String selectQuery = "SELECT * FROM " + TABLE_ANIMAL;
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        //loop through all rows, add to array
        if (c.moveToFirst()) {
            do {
                animal animal = new animal();
                animal.setId(c.getString(c.getColumnIndex(KEY_ID)));
                animal.setMorph(c.getString(c.getColumnIndex(ANIM_SEX)));
                animal.setSex(c.getString(c.getColumnIndex(ANIM_MORPH)));

                //add to list
                animals.add(animal);
            } while (c.moveToNext());
        }
        return animals;
    }
    /*
       Deletes a specific animal from DB based on ID
     */

    public void deleteAnimal(int animalID)
    {
        SQLiteDatabase db = AT.getReadableDatabase();
        db.delete(TABLE_INFO, KEY_ID + " = " + animalID, null);
        db.delete(TABLE_ANIMAL, KEY_ID + " = " + animalID, null);
        /*
        String myQuery = "DELETE FROM " + TABLE_ANIMAL + " WHERE "
                    + KEY_ID + " = " + animalID;
        String myotherQuery = "DELETE FROM " + TABLE_INFO + " WHERE "
                + KEY_ID + " = '" + animalID;
        db.rawQuery(myotherQuery,null);
        db.rawQuery(myQuery,null);
    */}

    /*
    Gets all animalInfo based on ID
     */
    public List<animalInformation> animalInfo(int animalID) {
        SQLiteDatabase db = AT.getReadableDatabase();
        String myQuery = "SELECT * FROM " + TABLE_INFO + " WHERE "
                + KEY_ID + " = " + animalID;

        List<animalInformation> animalInfo = new ArrayList<animalInformation>();

        Cursor c = db.rawQuery(myQuery, null);

            if (c.moveToFirst())
            {
                do
                {
                    animalInformation animal = new animalInformation();
                    animal.setId(c.getString(c.getColumnIndex(KEY_ID)));
                    animal.setWeight(c.getString(c.getColumnIndex(INFO_WEIGHT)));
                    animal.setShedDate(c.getString(c.getColumnIndex(INFO_SHED)));
                    animal.setAte(c.getString(c.getColumnIndex(INFO_WEIGHT)));

                    //add to the list
                    animalInfo.add(animal);
                }
                while (c.moveToNext());
            }
        return animalInfo;
    }

    public void updateAnimal(animal anim)
    {
        SQLiteDatabase db = AT.getReadableDatabase();
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put(ANIM_MORPH, anim.getMorph());
        dataToInsert.put(ANIM_SEX, anim.getSex());
        String where = KEY_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(anim.getId())};
        db.update(TABLE_ANIMAL, dataToInsert, where, whereArgs);
    }

    private boolean checkDataBase(Context context) {
        SQLiteDatabase checkDB = null;
        String DB_FULL_PATH = context.getDatabasePath(DATABASE_NAME).toString();
        System.out.println(DB_FULL_PATH +"xxx");
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_FULL_PATH, null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }
        return checkDB != null;
    }
}

