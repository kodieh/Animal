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
    String morph, sex, id;
    animal animalx;
    DatabaseHelper db;
    long x;
    /*
    On create of the class, create the necessary
    objects and wait for a click of the button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        animalx  = new animal();
        mButton = (Button) findViewById(R.id.ButtonSendFeedback);
        mButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        writeToDb();
                    }
                }
        );
    }
    /*
    Once the button is clicked, do the
    DB work method.
     */
    public void writeToDb() {
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        morphField = (EditText) findViewById(R.id.EditTextMorph);
        morph = morphField.getText().toString();
        sexSpinner = (Spinner) findViewById(R.id.SpinnerSex);
        sex = sexSpinner.getSelectedItem().toString();
        morphField.setText("");
        animalx.setMorph(morph.toString());
        animalx.setSex(sex.toString());

        x = db.createAnimal(animalx);
    }
}

