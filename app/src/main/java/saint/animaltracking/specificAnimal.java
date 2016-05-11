package saint.animaltracking;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.TwoStatePreference;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import saint.animaltracking.helper.AnimalInfoAdapter;
import saint.animaltracking.helper.DatabaseHelper;


/**
 * Created by Kodie on 5/2/2016.
 */
public class specificAnimal extends AppCompatActivity {
    String id;
    private SQLiteOpenHelper AT;
    TextView morph, sex;
    animal anim;
    Button update, FC, delete;
    Context context;
    Button mButton;
    EditText morphField;
    Spinner sexSpinner;
    String morphup, sexup;
    private List<animalInformation> animalInfo;
    private DatabaseHelper db;
    private ListView lv;
    AnimalInfoAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spec);
        db = new DatabaseHelper(this.getApplicationContext());
        lv = (ListView) findViewById(R.id.lstText);
        Intent intent = getIntent();
        context = this;
        anim = intent.getParcelableExtra("animal");
        animalInfo = db.animalInfo(Integer.parseInt(anim.getId()));
        sex = (TextView) findViewById(R.id.specificSex);
        morph = (TextView) findViewById(R.id.specificMorph);
        sex.setText(anim.getSex());
        morph.setText(anim.getMorph());
        update = (Button) findViewById(R.id.update);
        FC = (Button) findViewById(R.id.FC);
        delete = (Button) findViewById(R.id.delete);

        adapter = new AnimalInfoAdapter(this, R.layout.list_item, animalInfo);

        lv.setAdapter(adapter);

        update.setOnClickListener(updateListener);
        FC.setOnClickListener(FCListener);
        delete.setOnClickListener(deleteListener);
    }
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
    public void onBackPressed() {

        Intent intent = new Intent(this, selectAnimal.class);
        startActivity(intent);

        super.onBackPressed();
    }
}
