package saint.animaltracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Kodieh on 5/8/2016.
 */
public class FeedClean extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedclean);
        Intent intent = getIntent();
        animal anim = intent.getParcelableExtra("animal");

    }
}
