package saint.animaltracking;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Kodie on 5/2/2016.
 */
public class specificAnimal extends AppCompatActivity {
    String id;
    private SQLiteOpenHelper AT;
    TextView morph, sex;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spec);
        Intent intent = getIntent();

        animal anim = intent.getParcelableExtra("animal");
        sex = (TextView) findViewById(R.id.specificSex);
        morph = (TextView) findViewById(R.id.specificMorph);
        anim.toString();
        sex.setText(anim.getSex());
        morph.setText(anim.getMorph());

    }
}
