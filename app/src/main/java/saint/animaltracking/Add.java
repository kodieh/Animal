package saint.animaltracking;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.UUID;

import saint.animaltracking.helper.DatabaseHelper;


/**
 * Created by Kodie on 3/28/2016.
 */
public class Add extends AppCompatActivity
{
    Button mButton;
    EditText morphField;
    Spinner sexSpinner;
    String morph, sex, id;
    animal animalx;
    DatabaseHelper db;
    long x;
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

    public void writeToDb()
    {
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        id = uniqueID();
        morphField = (EditText) findViewById(R.id.EditTextMorph);
        morph = morphField.getText().toString();
        sexSpinner = (Spinner) findViewById(R.id.SpinnerSex);
        sex = sexSpinner.getSelectedItem().toString();
        morphField.setText("");
        animalx.setId(id);
        animalx.setMorph(morph);
        animalx.setSex(sex);

        x = db.createAnimal(animalx);
}

    public String uniqueID()
    {
        String uniqueID = UUID.randomUUID().toString();

        return uniqueID;
    }
}
