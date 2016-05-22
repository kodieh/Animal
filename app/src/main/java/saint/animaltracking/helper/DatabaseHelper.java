package saint.animaltracking.helper;


import android.database.sqlite.*;
import android.content.*;
import android.database.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
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
    //SQLite helper
    private SQLiteOpenHelper AT;

    //Chained constructor.
    public DatabaseHelper(Context context)
    {
        AT = new DatabaseOpenHelper(context);
        AT.getReadableDatabase();
        boolean x = checkDataBase(context);

    }

    //Default constructor
    class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        //Chained constructor
        DatabaseOpenHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DB_VERSION);
        }

        //OnCreate of app, create tables.
        @Override
        public void onCreate(SQLiteDatabase db)
        {
                db.execSQL(CREATE_TABLE_ANIMAL);
                db.execSQL(CREATE_TABLE_INFO);
        }

        /*
        OnUpgrade, as far as I am aware, is used to make changes to the database upon further
        releases. At this time, I do not have a use for this; thus it is here but unimplemented.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
        }
    }

    /*
     * Creating an animal
     */
    public void createAnimal(animal animal)
    {
        //Open a writable database.
        SQLiteDatabase db = AT.getWritableDatabase();

        /*
        Create and load the values into a ContentValues
        The ID is auto-incremented in the database.
         */
        ContentValues values = new ContentValues();
        values.put(ANIM_MORPH, animal.getMorph());
        values.put(ANIM_SEX, animal.getSex());

        //Insert the animal into the database.
        db.insert(TABLE_ANIMAL, null, values);
        db.close();
    }


    /*
     * Create Info for an animal.
     */
    public void createInfo(animalInformation animalInfo)
    {
       //Get a writable database
       SQLiteDatabase db = AT.getWritableDatabase();

       //Create and load the values into a ContentValues
       ContentValues values = new ContentValues();
       String id = animalInfo.getId();
       String weight = animalInfo.getWeight();
       String shed = animalInfo.getShedDate();
       String ate = animalInfo.getAte();
       values.put(KEY_ID, id);
       values.put(INFO_WEIGHT,weight);
       values.put(INFO_SHED, shed);
       values.put(INFO_ATE, ate);

       //Insert the info into the table.
       db.insert(TABLE_INFO, null, values);
    }

    /*
     * Get a single animal
     */
    public animal getAnimal(String animalID)
    {
        //Get a writable database
        SQLiteDatabase db = AT.getReadableDatabase();

        //Create the animal object
        animal animal = new animal();

        //Create the query statement
        String selectQuery = "SELECT * FROM " + TABLE_ANIMAL + " WHERE "
                + KEY_ID + " = " + animalID;

        //Log any issues
        Log.e(LOG, selectQuery);

        //Create a cursor to walk through the result.
        Cursor c = db.rawQuery(selectQuery, null);

        //If the result is not null, start from the top
        if (c != null)
        {
            c.moveToFirst();
        }

        //Set the animal object according to what the query returns
        animal.setId(c.getString(c.getColumnIndex(KEY_ID)));
        animal.setMorph(c.getString(c.getColumnIndex(ANIM_MORPH)));
        animal.setSex(c.getString(c.getColumnIndex(ANIM_SEX)));

        //returns the animal object
        return animal;
    }

    /*
    gets all animals in the db
     */
    public List<animal> getAllAnimal()
    {
        //Get a readable database
        SQLiteDatabase db = AT.getReadableDatabase();

        //Creates the list to load the animals into
        List<animal> animals = new ArrayList<animal>();

        //Query for getting all animals.
        String selectQuery = "SELECT * FROM " + TABLE_ANIMAL;

        //Logging for errors
        Log.e(LOG, selectQuery);

        //Create a cursor to walk through the results
        Cursor c = db.rawQuery(selectQuery, null);

        //loop through all rows, add to list
        if (c.moveToFirst())
        {
            do
            {
                /*
                Create the animal object
                set the qualities of the object
                add the object to the list
                repeat
                 */
                animal animal = new animal();
                animal.setId(c.getString(c.getColumnIndex(KEY_ID)));
                animal.setMorph(c.getString(c.getColumnIndex(ANIM_SEX)));
                animal.setSex(c.getString(c.getColumnIndex(ANIM_MORPH)));
                animals.add(animal);
            }
            while (c.moveToNext());
        }

        //Return the list to the calling method
        return animals;
    }

    /*
       Deletes a specific animal from DB based on ID
     */
    public void deleteAnimal(int animalID)
    {
        //Get a writable database
        SQLiteDatabase db = AT.getWritableDatabase();

        /*
        When deleting delete all information of the specific animal as well as the
        animal itself.
        */
        db.delete(TABLE_INFO, KEY_ID + " = " + animalID, null);
        db.delete(TABLE_ANIMAL, KEY_ID + " = " + animalID, null);
    }

    /*
    Gets all animalInfo based on ID
     */
    public List<animalInformation> getAnimalInfo(int animalID)
    {
        //Get a readable database
        SQLiteDatabase db = AT.getReadableDatabase();

        //Query to get all information for an animal
        String myQuery = "SELECT * FROM " + TABLE_INFO + " WHERE "
                + KEY_ID + " = " + animalID;

        //Create a list to store all the information for an animal
        List<animalInformation> animalInfo = new ArrayList<animalInformation>();

        //Create a cursor to walk through the results
        Cursor c = db.rawQuery(myQuery, null);

        if (c.moveToFirst())
        {
            do
            {
                /*
                Get the information
                store it in the object
                add it to the list
                 */
                animalInformation animal = new animalInformation();
                animal.setId(c.getString(c.getColumnIndex(KEY_ID)));
                animal.setWeight(c.getString(c.getColumnIndex(INFO_WEIGHT)));
                animal.setShedDate(c.getString(c.getColumnIndex(INFO_SHED)));
                animal.setAte(c.getString(c.getColumnIndex(INFO_ATE)));

                //add to the list
                animalInfo.add(animal);
            }
            while (c.moveToNext());
        }
        //return the list to the calling method
        return animalInfo;
    }

    /*
    Given an animal object
    Override the object in the database
    By updating the corresponding ID
     */
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

    /*
    Ensure the DB is actually there.
     */
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

