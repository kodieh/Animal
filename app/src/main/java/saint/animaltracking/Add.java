package saint.animaltracking;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import saint.animaltracking.helper.DatabaseHelper;


/**
 * Created by Kodie on 3/28/2016.
 */
public class Add extends AppCompatActivity
{
    /*
    Initialization of necessary variables.
     */
    Button mButton;
    EditText morphField;
    Spinner sexSpinner;
    String morph, sex;
    animal animalx;
    /*
    On create of the class, create the necessary
    objects and wait for a click of the button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Set context and assign layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        //create the animal object to assign to
        animalx  = new animal();
        //Assign the button XML
        mButton = (Button) findViewById(R.id.ButtonSendFeedback);
        //On click, do this
        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        writeToDb();
                    }
                }
        );
    }

    /*
    Once the button is clicked, do the
    DB work method.
     */
    public void writeToDb()
    {
        //Create a DatabaseHelper to do the DB work
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());

        //Assign the fields to variables
        morphField = (EditText) findViewById(R.id.EditTextMorph);
        sexSpinner = (Spinner) findViewById(R.id.SpinnerSex);

        //get the values from the variables into primitive types for the object
        morph = morphField.getText().toString();
        sex = sexSpinner.getSelectedItem().toString();

        //Reset the field to blank to demonstrate that the entry has been taken.
        morphField.setText("");

        //Assign the primitive types to the object
        animalx.setMorph(morph.toString());
        animalx.setSex(sex.toString());

        //pass the object to the DatabaseHelper to create the animal
        db.createAnimal(animalx);

        /*
        Create a pop up that asks "Add another animal?"
        On yes, stay on screen
        On no, return to main menu
         */
    }
}

