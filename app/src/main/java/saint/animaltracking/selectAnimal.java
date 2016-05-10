package saint.animaltracking;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import saint.animaltracking.helper.AnimalAdapter;
import saint.animaltracking.helper.DatabaseHelper;

import static android.location.Location.convert;

/**
 * Created by Kodie on 3/28/2016.
 */
public class selectAnimal extends AppCompatActivity
{
    private ListView lv;
    private List<animal> animal;
    private DatabaseHelper db;
    AnimalAdapter adapter;
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
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                animal anim = (animal) lv.getItemAtPosition(position);
//                Intent intent = new Intent(getApplicationContext(), specificAnimal.class);
//                intent.putExtra("animal", anim);
//                startActivity(intent);
//            }
//        });
    }

}
