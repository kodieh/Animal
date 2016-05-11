package saint.animaltracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import saint.animaltracking.helper.DatabaseHelper;

/**
 * Created by Kodieh on 5/8/2016.
 */
public class FeedClean extends AppCompatActivity
{
    animalInformation animInfo = new animalInformation();
    EditText shed, weight, ate;
    animal anim;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedclean);
        Intent intent = getIntent();
        anim = intent.getParcelableExtra("animal");
        shed = (EditText) findViewById(R.id.editText);
        weight = (EditText) findViewById(R.id.editText2);
        ate = (EditText) findViewById(R.id.editText3);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                writeToDb();
            }
        });
    }

    public void writeToDb()
    {
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        animInfo.setShedDate(shed.getText().toString());
        animInfo.setWeight(weight.getText().toString());
        animInfo.setAte(ate.getText().toString());
        animInfo.setId(anim.getId());

        db.createInfo(animInfo);

    }
}
