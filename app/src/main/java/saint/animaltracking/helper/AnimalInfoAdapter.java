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
import saint.animaltracking.animalInformation;
/**
 * Created by Kodieh on 5/10/2016.
 */
public class AnimalInfoAdapter extends ArrayAdapter<animalInformation>
{
    int resource;
    public AnimalInfoAdapter(Context context, int resource, List<animalInformation> animalInfo)
    {
        super(context, resource, animalInfo);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        animalInformation animalInfo = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_animalinfo, parent, false);
        }

        TextView tvWeight = (TextView) convertView.findViewById(R.id.tvWeight);
        TextView tvAte = (TextView) convertView.findViewById(R.id.tvAte);
        TextView tvShed = (TextView) convertView.findViewById(R.id.tvShed);

        tvShed.setText(animalInfo.getShedDate());
        tvWeight.setText(animalInfo.getWeight());
        tvAte.setText(animalInfo.getAte());
        return convertView;
    }
}
