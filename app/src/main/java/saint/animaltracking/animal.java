package saint.animaltracking;

import android.os.*;

/**
 * Created by Kodie on 3/30/2016.
 */
public class animal implements Parcelable
{
    String _morph;
    String _sex;
    String _id;
    //default constructor
    public animal(){

    }
    /*
    Construction of the object
     */
    public animal(String morph, String sex, String id)
    {
        _id = id;
        _morph = morph;
        _sex = sex;
    }

    //setters
    public void setId(String id)
    {
        _id = id;
    }

    public void setMorph(String morph)
    {
        _morph = morph;
    }
    public void setSex(String sex)
    {
        _sex = sex;
    }

    //getters
    public String getId()
    {
        return _id;
    }

    public String getMorph()
    {
        return _morph;
    }

    public String getSex()
    {
        return _sex;
    }

    //parceling
    public animal(Parcel in)
    {
        String[]data = new String[3];

        in.readStringArray(data);
        this._id = data[0];
        this._morph = data[1];
        this._sex = data[2];
    }
    /*
    This next bit I don't get why it's necessary
    however I cannot pass objects between activities
    without it.
     */
    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags)
    {
        dest.writeStringArray(new String[]{this._id,this._sex,this._morph});
    }

    public static final Parcelable.Creator<animal> CREATOR = new Parcelable.Creator<animal>()
    {
        @Override
        public animal createFromParcel(Parcel source)
        {
            return new animal(source);
        }

        @Override
        public animal[] newArray(int size) {
            return new animal[size];
        }
    };
}
