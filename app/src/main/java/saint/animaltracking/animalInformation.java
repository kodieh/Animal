package saint.animaltracking;

/**
 * Created by Kodie on 3/30/2016.
 */
public class animalInformation
{
    int weight;
    String shedDate,ate, id;
    //default constructor
    public animalInformation()
    {

    }
    //constructors
    public animalInformation(String id, int weight, String shedDate, String ate)
    {
        this.id = id;
        this.weight = weight;
        this.shedDate = shedDate;
        this.ate = ate;
    }

    //setters
    public void setId(String id)
    {
        this.id = id;
    }
    public void setWeight(int weight)
    {
        this.weight = weight;
    }
    public void setShedDate(String shedDate)
    {
        this.shedDate = shedDate;
    }
    public void setAte(String ate)
    {
        this.ate = ate;
    }

    //getters
    public String getId()
    {
        return this.id;
    }
    public int getWeight()
    {
        return this.weight;
    }
    public String getShedDate()
    {
        return this.shedDate;
    }
    public String getAte()
    {
        return this.getAte();
    }
}
