package saint.animaltracking.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import saint.animaltracking.R;
import saint.animaltracking.animal;

/**
 * Created by Kodie on 4/26/2016.
 */
public class AnimalAdapter extends ArrayAdapter<animal>
{
    int resource;
    public AnimalAdapter(Context context, int resource, List<animal> animal)
    {
        super(context, resource, animal);
       this.resource = resource;
    }

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
