package saint.animaltracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import java.util.List;

import saint.animaltracking.helper.AnimalAdapter;
import saint.animaltracking.helper.DatabaseHelper;

import static android.location.Location.convert;

/**
 * Created by Kodie on 3/28/2016.
 */
public class selectAnimal extends AppCompatActivity
{
    /*
    Initialize necessary variables.
     */
    private ListView lv;
    private List<animal> animal;
    private DatabaseHelper db;
    AnimalAdapter adapter;

    /*
    On create, open the DB, get all animals,
    throw all animals into the array and then
    push that to the Adapter which then
    displays it properly in the ListView
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this.getApplicationContext());
        setContentView(R.layout.main);
        animal = db.getAllAnimal();
        lv = (ListView) findViewById(R.id.lstText);

        adapter = new AnimalAdapter(this, R.layout.list_item, animal);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick(view, position);
            }

            public void onClick(View v, int position)
            {
                animal anim = (animal) lv.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), specificAnimal.class);
                intent.putExtra("animal", anim);
                startActivity(intent);
            }
        });
    }
    /*
    When the back button is pressed from this screen
    it returns to the main page.
     */
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, Main.class);
        startActivity(intent);

        super.onBackPressed();
    }

}
