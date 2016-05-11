package saint.animaltracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



/**
 * Created by Kodie on 3/28/2016.
 */
public class Main extends Activity
{
    /*
    on create, set the layout.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

    }

    /**Called when the user clicks the Select button */
    public void selectAnimal(View view)
    {
        Intent intent = new Intent(this, selectAnimal.class);
        startActivity(intent);
    }
    /**Called when the user clicks the ScanQR button */
    public void scanQR(View view)
    {
        Intent intent = new Intent(this, scanQR.class);
        startActivity(intent);
    }
    /**Called when the user clicks the Add button */
    public void Add(View view)
    {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent);
    }
}
