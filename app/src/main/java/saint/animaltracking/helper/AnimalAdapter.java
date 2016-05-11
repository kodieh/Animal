package saint.animaltracking.helper;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.List;
import saint.animaltracking.*;

/**
 * Created by Kodie
 */
public class AnimalAdapter extends ArrayAdapter<animal>
{
    /*
    Adapter to set the context and play intermediary to
    the ListView for an animal.
     */
    int resource;
    public AnimalAdapter(Context context, int resource, List<animal> animal)
    {
        super(context, resource, animal);
       this.resource = resource;
    }
    /*
    View resource to set the TextViews of the
    animal adapter.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //get the data item for this position
        animal animal = getItem(position);
        //Check if an existing view is being reused,
        //otherwise inflate the view
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_animal, parent, false);
        }
        //Lookup view for data population
        TextView tvMorph = (TextView) convertView.findViewById(R.id.tvMorph);
        TextView tvSex = (TextView) convertView.findViewById(R.id.tvSex);

        //Populate the data into the template view using the data object
        tvMorph.setText(animal.getMorph());
        tvSex.setText(animal.getSex());

        return convertView;

    }
}
