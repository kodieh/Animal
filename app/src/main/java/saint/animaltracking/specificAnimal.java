package saint.animaltracking;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Kodie on 5/2/2016.
 */
public class specificAnimal extends AppCompatActivity
{
    String id;
    private SQLiteOpenHelper AT;

    public void onCreate(Bundle savedInstanceState)
    {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific);
        animal anim = intent.getParcelableExtra("animal");
        /*TextView morph = (TextView)findViewById(R.id.animalMorph);
        TextView sex = (TextView)findViewById(R.id.animalSex);
        if(morph == null)
        {
            System.out.println("nulled");
        }
        if(sex == null)
        {
            System.out.println("nulled");
        }
        morph.setText(anim.getMorph());
        sex.setText(anim.getSex());*/
        id = anim.getId();
    }

    public void Update(View view, animal anim)
    {
        Intent intent = new Intent(this, Update.class);
        intent.putExtra("animal", anim);
        startActivity(intent);
    }

    public void FeedClean(View view, animal anim)
    {
        Intent intent = new Intent(this, FeedClean.class);
        intent.putExtra("animal", anim);
        startActivity(intent);
    }

    public void Delete(View view, animal anim) {
        /*
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        dropAnimal();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void dropAnimal()
    {
        SQLiteDatabase db = AT.getWritableDatabase();
        String myQuery = "DELETE FROM animal AND information WHERE ID =" + id;
        db.rawQuery(myQuery, null);
    }*/
    }
}

