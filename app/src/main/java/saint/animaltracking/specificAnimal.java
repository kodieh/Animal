package saint.animaltracking;

import android.app.AlertDialog;
import android.content.*;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import java.util.List;
import saint.animaltracking.helper.AnimalInfoAdapter;
import saint.animaltracking.helper.DatabaseHelper;


/**
 * Created by Kodie on 5/2/2016.
 */
public class specificAnimal extends AppCompatActivity {
    /*
    Initialize ALL the variables.
     */
    private SQLiteOpenHelper AT;
    TextView morph, sex;
    animal anim;
    Button update, FC, delete, mButton;
    Context context;
    EditText morphField;
    Spinner sexSpinner;
    String morphup, sexup;
    private List<animalInformation> animalInfo;
    private DatabaseHelper db;
    private ListView lv;
    AnimalInfoAdapter adapter;

    /*
    On create:
        open the db
        set the necessary object arrays
        push the object arrays to the list
        push the list to the adapter
        set the listview on the adapter
        ????
        show all the entries for the specific animal.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        //Get the saved instance and set the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spec);

        //Instantiate the DatabaseHelper variable
        db = new DatabaseHelper(this.getApplicationContext());
        //Instantiate the ListView variable, setting the layout
        lv = (ListView) findViewById(R.id.lstText);

        //Retrieve the intent from the previous activity;
        Intent intent = getIntent();

        //Globalize the context
        context = this;

        //Instantiate the animal variable from the intent from the previous activity
        anim = intent.getParcelableExtra("animal");

        //Get the list of all animal info from the database
        animalInfo = db.getAnimalInfo(Integer.parseInt(anim.getId()));

        //Set the layout and the text of the TextViews for Sex and Morph
        sex = (TextView) findViewById(R.id.specificSex);
        morph = (TextView) findViewById(R.id.specificMorph);
        sex.setText(anim.getSex());
        morph.setText(anim.getMorph());

        //Assign the variables to the id's of the buttons
        update = (Button) findViewById(R.id.update);
        FC = (Button) findViewById(R.id.FC);
        delete = (Button) findViewById(R.id.delete);

        //Assign the adapter with the animalInfo list
        adapter = new AnimalInfoAdapter(this, R.layout.list_item, animalInfo);
        lv.setAdapter(adapter);

        //Set the onClickListeners for when a button is pressed.
        update.setOnClickListener(updateListener);
        FC.setOnClickListener(FCListener);
        delete.setOnClickListener(deleteListener);
    }
    /*
    the following View.OnClickListener's wait for the user
    to click the corresponding button and then it acts upon
    which id (of the button) is picked.
     */
    View.OnClickListener updateListener = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            setContentView(R.layout.add);
            TextView add = (TextView) findViewById(R.id.TextViewTitle);
            add.setText("Update an animal");
            mButton = (Button) findViewById(R.id.ButtonSendFeedback);
            mButton.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            writeToDb();
                        }
                    }
            );

        }
    };
    View.OnClickListener FCListener = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent(context, FeedClean.class);
            intent.putExtra("animal", anim);
            startActivity(intent);
        }
    };
    View.OnClickListener deleteListener = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Are you sure you want to delete this animal?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                            db.deleteAnimal(Integer.parseInt(anim.getId()));
                            Intent intent = new Intent(getApplicationContext(), selectAnimal.class);
                            startActivity(intent);
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    };
    /*
    for feeding and cleaning, this opens the database
    and mashes everything together to create the new
    fed and clean entry.
     */
    public void writeToDb()
    {
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        morphField = (EditText) findViewById(R.id.EditTextMorph);
        morphup = morphField.getText().toString();
        sexSpinner = (Spinner) findViewById(R.id.SpinnerSex);
        sexup = sexSpinner.getSelectedItem().toString();
        morphField.setText("");
        anim.setMorph(morphup);
        anim.setSex(sexup);

        db.updateAnimal(anim);
        Intent intent = new Intent(getApplicationContext(), specificAnimal.class);
        intent.putExtra("animal", anim);
        startActivity(intent);

    }
    /*
    if deleted, go back to the list of animals.
     */
    public void onBackPressed() {

        Intent intent = new Intent(this, selectAnimal.class);
        startActivity(intent);

        super.onBackPressed();
    }
}
